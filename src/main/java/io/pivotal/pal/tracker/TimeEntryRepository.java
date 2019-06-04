package io.pivotal.pal.tracker;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public interface TimeEntryRepository {
    
    TimeEntry create(TimeEntry timeEntry);

    TimeEntry find(Long timeEntryId);

    List<TimeEntry> list();

    TimeEntry update(long eq, TimeEntry any);

    TimeEntry delete(long timeEntryId);
}
