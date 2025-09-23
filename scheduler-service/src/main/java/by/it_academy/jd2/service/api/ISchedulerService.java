package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.Page;
import by.it_academy.jd2.dto.ScheduledOperation;

import java.util.UUID;

public interface ISchedulerService {
    void add(ScheduledOperation scheduledOperation);
    Page getPage(int page, int size);
    void update(UUID uuid, long dtUpdate, ScheduledOperation scheduledOperation);
}
