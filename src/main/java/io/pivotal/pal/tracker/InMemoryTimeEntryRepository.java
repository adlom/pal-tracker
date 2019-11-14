package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private ConcurrentHashMap<Long, TimeEntry> map = new ConcurrentHashMap<>();
    private AtomicLong seq = new AtomicLong(1L);

    public TimeEntry create(TimeEntry timeEntry) {
        long val = seq.get();
        System.out.println("val: "+val);
        TimeEntry createdTimeEntry = new TimeEntry(val, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        map.put(val, createdTimeEntry);

        System.out.println("seq.incrementAndGet();: "+seq.incrementAndGet());
        return createdTimeEntry;
    }

    public TimeEntry find(long id) {
        return map.get(id);
    }

    public List<TimeEntry> list() {
        return new ArrayList(map.values());
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry updatedTimeEntry = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        map.replace(id, updatedTimeEntry);
        return map.get(id);
    }

    public void delete(long id) {
        map.remove(id);
    }

}
