package com.litereaction.controller;

import com.litereaction.model.Event;
import com.litereaction.repository.EventRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/events")
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation("Search for Events")
    public List<Event> search() {
        return eventRepository.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ApiOperation("Create new Event")
    public ResponseEntity<Event> create(@RequestBody Event event) {
        Event result = eventRepository.save(event);
        return new ResponseEntity<Event>(result, HttpStatus.CREATED);
    }


}
