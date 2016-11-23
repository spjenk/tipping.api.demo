package com.litereaction.controller;

import com.litereaction.model.Round;
import com.litereaction.repository.RoundRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoundControllerTests {

	private static final String BASE_URL = "/rounds/";

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private RoundRepository roundRepository;

	@Before
	public void setup() {
		this.roundRepository.deleteAllInBatch();
		this.roundRepository.save(new Round("Round 1", "First Round", true));
		this.roundRepository.save(new Round("Round 2", "Second Round", true));
	}

	@Test
	public void searchRoundsTest() throws Exception {

		ResponseEntity<String> response = this.restTemplate.getForEntity(BASE_URL, String.class);
		assertNotNull(response.getBody());
		assertThat(response.getBody(), containsString("Round 1"));
		assertThat(response.getBody(), containsString("Round 2"));
	}

    @Test
    public void createRoundTest() throws Exception {

        String name = "Create";
        String description = "Create Round Test";
        boolean active = true;
        Round round = new Round(name, description, active);

        ResponseEntity<Round> response =
                this.restTemplate.postForEntity(BASE_URL, round, Round.class);

        assertThat(response.getStatusCode() , equalTo(HttpStatus.CREATED));
        assertNotNull(response.getBody());

        Round result = response.getBody();
        assertThat(result.getName(), equalTo(name));
        assertThat(result.getDescription(), equalTo(description));
        assertThat(result.isActive(), equalTo(active));
    }

    @Test
    public void ReadRoundTest() throws Exception {

        String name = "Read";
        String description = "Read Round Test";
        boolean active = true;

        Round round = this.roundRepository.save(new Round(name, description, active));

        String url = BASE_URL + round.getId();

        ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class);
        assertNotNull(response.getBody());
        assertThat(response.getBody(), containsString("\"name\":\"Read\""));
        assertThat(response.getBody(), containsString("\"description\":\"Read Round Test\""));
    }
}
