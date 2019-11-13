package io.pivotal.pal.tracker;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class TimeEntryRepository {
    private ConcurrentHashMap<Long, TimeEntry> map = new ConcurrentHashMap<>();
    private AtomicLong seq = new AtomicLong(1L);


    public TimeEntry create(TimeEntry any) {
        long val = seq.get();
        System.out.println("val: "+val);
        TimeEntry createdTimeEntry = new TimeEntry(val, any.getProjectId(), any.getUserId(), any.getDate(), any.getHours());
        map.put(val, createdTimeEntry);

        System.out.println("seq.incrementAndGet();: "+seq.incrementAndGet());
        return createdTimeEntry;

    }

    public TimeEntry find(long timeEntryId) {
        return map.get(timeEntryId);
    }

    public List<TimeEntry> list() {
        List<TimeEntry> list = new ArrayList(map.values());
        return list;
    }

    public TimeEntry update(long eq, TimeEntry any) {
        TimeEntry updatedTimeEntry = new TimeEntry(eq, any.getProjectId(), any.getUserId(), any.getDate(), any.getHours());
        map.replace(eq, updatedTimeEntry);
        return map.get(eq);

    }

    public TimeEntry delete(long timeEntryId) {
        return map.remove(timeEntryId);
    }
}
