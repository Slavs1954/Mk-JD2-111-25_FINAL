package by.it_academy.jd2.storage.api;

import by.it_academy.jd2.dto.Page;
import by.it_academy.jd2.dto.ScheduledOperation;

import java.util.UUID;

public interface ISchedulerStorage {
    void add(ScheduledOperation scheduledOperation);
    void update(UUID uuid, long dtUpdate, ScheduledOperation scheduledOperation);
    Page getPage(int page, int size);
}
