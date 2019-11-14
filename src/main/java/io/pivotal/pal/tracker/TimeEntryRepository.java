package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {


    public TimeEntry create(TimeEntry any);
    public TimeEntry find(long timeEntryId);
    public abstract List<TimeEntry> list() ;
    public TimeEntry update(long eq, TimeEntry any) ;
    public void delete(long timeEntryId);

/*
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

    public abstract List<TimeEntry> list() ;

    public TimeEntry update(long eq, TimeEntry any) {
        TimeEntry updatedTimeEntry = new TimeEntry(eq, any.getProjectId(), any.getUserId(), any.getDate(), any.getHours());
        map.replace(eq, updatedTimeEntry);
        return map.get(eq);

    }

    public void delete(long timeEntryId) {
        map.remove(timeEntryId);
    }*/
}
