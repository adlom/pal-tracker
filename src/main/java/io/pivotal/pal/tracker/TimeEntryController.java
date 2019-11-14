package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @RequestMapping(value = "/time-entries", method = POST)
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {

        System.out.println("@RequestBody TimeEntry :"+timeEntryToCreate.toString());
        ResponseEntity createdEntity = new ResponseEntity(timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
        System.out.println("ResponseEntity create :"+createdEntity.getBody().toString());

        return createdEntity;
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long timeEntryId) {
        TimeEntry te = timeEntryRepository.find(timeEntryId);
        if(te != null)
            return  new ResponseEntity(te, HttpStatus.OK);
        else  return new ResponseEntity(te, HttpStatus.NOT_FOUND);


    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable("id") long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry te = timeEntryRepository.update(timeEntryId, expected);
        if(te != null)
            return new ResponseEntity(te, HttpStatus.OK);
        else return new ResponseEntity(te, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable("id") long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity( HttpStatus.NO_CONTENT);
    }
}
