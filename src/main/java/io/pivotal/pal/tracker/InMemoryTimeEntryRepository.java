package io.pivotal.pal.tracker;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

@Component
public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    Map<Long, TimeEntry> timeEntries = new HashMap<>();

    Long counter = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        if(timeEntry.getId() == 0L){
            timeEntry.setId(counter);
            counter++;
        }
        timeEntries.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(Long timeEntryId) {
        return timeEntries.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntries.values());
    }

    @Override
    public TimeEntry update(long timeEntryId, TimeEntry timeEntry) {
        TimeEntry toUpdate = timeEntries.get(timeEntryId);
        if(toUpdate == null){
            return null;
        }
        toUpdate.setProjectId(timeEntry.getProjectId());
        toUpdate.setDate(timeEntry.getDate());
        toUpdate.setHours(timeEntry.getHours());
        toUpdate.setUserId(timeEntry.getUserId());
        return toUpdate;
    }

    @Override
    public TimeEntry delete(long timeEntryId) {
        return timeEntries.remove(timeEntryId);
    }
}
