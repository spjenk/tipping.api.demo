package com.litereaction.controller;

import com.litereaction.model.Event;
import com.litereaction.model.Round;
import com.litereaction.repository.EventRepository;
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventControllerTest {

    private static final String BASE_URL = "/events/";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EventRepository eventRepository;

    @Autowired RoundRepository roundRepository;

    @Before
    public void setup() {
        this.eventRepository.deleteAllInBatch();

        Round round = this.roundRepository.save(new Round("R1", "Round 1", true));

        this.eventRepository.save(new Event("E1", "Event 1", round));
        this.eventRepository.save(new Event("E2", "Event 2", round));
    }

    @Test
    public void searchRoundsTest() throws Exception {

        ResponseEntity<String> response = this.restTemplate.getForEntity(BASE_URL, String.class);
        assertNotNull(response.getBody());
        assertThat(response.getBody(), containsString("Event 1"));
        assertThat(response.getBody(), containsString("Event 2"));
        assertThat(response.getBody(), containsString("Round 1"));
    }

    @Test
    public void createRoundTest() throws Exception {

        Round round = this.roundRepository.save(new Round("R2", "Round 2", true));

        String name = "Create";
        String description = "Create Event Test";

        Event event = new Event(name, description, round);

        ResponseEntity<Event> response =
                this.restTemplate.postForEntity(BASE_URL, event, Event.class);

        assertThat(response.getStatusCode() , equalTo(HttpStatus.CREATED));
        assertNotNull(response.getBody());

        Event result = response.getBody();
        assertThat(result.getName(), equalTo(name));
        assertThat(result.getDescription(), equalTo(description));
        assertThat(result.getRound().getName(), equalTo("R2"));
    }

    @Test
    public void ReadRoundTest() throws Exception {

        String name = "Read";
        String description = "Read Event Test";
        Round round = this.roundRepository.save(new Round("R3", "Round 3", true));

        Event event = this.eventRepository.save(new Event(name, description, round));

        String url = BASE_URL + event.getId();

        ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class);
        assertNotNull(response.getBody());
        assertThat(response.getBody(), containsString("\"name\":\"Read\""));
        assertThat(response.getBody(), containsString("\"description\":\"Read Event Test\""));
    }

}
