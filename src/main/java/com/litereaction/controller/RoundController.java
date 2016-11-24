package com.litereaction.controller;

import com.litereaction.model.Round;
import com.litereaction.repository.RoundRepository;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest API controller for /rounds
 * SCRUD operations
 */
@RestController
@RequestMapping(value = "/rounds")
@CrossOrigin(origins = "*")
public class RoundController {

    private Logger log = Logger.getLogger(RoundController.class);

    @Autowired
    RoundRepository roundRepository;


    /**
     * Search
     *
     * HTTP Get on /rounds/
     *
     * @return List of Rounds
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation("Get all Rounds")
    public List<Round> search() {
        return roundRepository.findAll();
    }

    /**
     * Create
     *
     * HTTP Post on /rounds/
     *
     * @param round Round item to create
     * @return newly created Round
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation("Create a new round definition")
    public ResponseEntity<Round> create(@RequestBody Round round) {

        try {
            Round result = roundRepository.save(round);
        } catch (Exception e) {
            return new ResponseEntity<Round>(round, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Round>(round, HttpStatus.CREATED);
    }

    /**
     * Read
     *
     * HTTP Get on /rounds/{id}
     *
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation("Read an existing round")
    public ResponseEntity<Round> read(@PathVariable long id) {
        Round result = roundRepository.findOne(id);
        return new ResponseEntity<Round>(result, HttpStatus.OK);
    }


    /**
     * Update
     *
     * HTTP Put on /rounds/{id}
     *
     * @param round Round to update
     * @return updated round
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation("Update an existing round")
    public ResponseEntity<Round> update(@PathVariable long id, @RequestBody Round round) {

        Round result = roundRepository.save(round);
        return new ResponseEntity<Round>(result, HttpStatus.OK);
    }


    /**
     * Delete
     *
     * HTTP Delete on /rounds/{{id}}
     *
     * @param id Id of the round to delete
     * @return Response Entity success or failure
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation("Delete an existing round")
    public ResponseEntity delete(long id) {
        try {
            roundRepository.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
