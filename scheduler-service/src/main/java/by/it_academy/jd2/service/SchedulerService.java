package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.Page;
import by.it_academy.jd2.dto.ScheduledOperation;
import by.it_academy.jd2.service.api.ISchedulerService;
import by.it_academy.jd2.storage.api.ISchedulerStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SchedulerService implements ISchedulerService {
    private final ISchedulerStorage schedulerStorage;

    @Override
    public void add(ScheduledOperation scheduledOperation) {

        scheduledOperation.setUuid(UUID.randomUUID());
        scheduledOperation.setDtCreate(Instant.now().toEpochMilli());
        scheduledOperation.setDtUpdate(Instant.now().toEpochMilli());

        schedulerStorage.add(scheduledOperation);

    }

    @Override
    public Page getPage(int page, int size) {
        return schedulerStorage.getPage(page, size);
    }

    @Override
    public void update(UUID uuid, long dtUpdate, ScheduledOperation scheduledOperation) {
        schedulerStorage.update(uuid, dtUpdate, scheduledOperation);

    }
}
