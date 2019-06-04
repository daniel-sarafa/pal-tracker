package io.pivotal.pal.tracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.pivotal.pal.tracker.TimeEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping(value = "/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry createdEntry = timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity(createdEntry, HttpStatus.CREATED);
    }

    @GetMapping(value = "/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable(value="id") long timeEntryId) {
        TimeEntry entryFound = timeEntryRepository.find(timeEntryId);
        if(entryFound != null){
            return new ResponseEntity(entryFound, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping(value = "/time-entries/{id}")
    public ResponseEntity update(@PathVariable(value="id") long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry updatedEntry = timeEntryRepository.update(timeEntryId, expected);
        if(updatedEntry != null){
            return new ResponseEntity(updatedEntry, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/time-entries/{id}")
    public ResponseEntity delete(@PathVariable(value="id") long timeEntryId) {
        return new ResponseEntity(timeEntryRepository.delete(timeEntryId), HttpStatus.NO_CONTENT);
    }
}
