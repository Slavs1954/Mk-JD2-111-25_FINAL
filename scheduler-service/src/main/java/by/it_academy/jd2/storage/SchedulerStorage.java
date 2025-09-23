package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.Operation;
import by.it_academy.jd2.dto.Page;
import by.it_academy.jd2.dto.Schedule;
import by.it_academy.jd2.dto.ScheduledOperation;
import by.it_academy.jd2.storage.api.ISchedulerStorage;
import by.it_academy.jd2.storage.entity.ScheduledOperationEntity;
import by.it_academy.jd2.storage.repository.ScheduledOperationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class SchedulerStorage implements ISchedulerStorage {
    private final ScheduledOperationRepository scheduledOperationRepository;

    @Override
    public void add(ScheduledOperation scheduledOperation) {

       ScheduledOperationEntity entity = ScheduledOperationEntity.builder()
                .uuid(scheduledOperation.getUuid())
                .dtCreate(scheduledOperation.getDtCreate())
                .dtUpdate(scheduledOperation.getDtUpdate())
                .startTime(scheduledOperation.getSchedule().getStartTime())
                .stopTime(scheduledOperation.getSchedule().getStopTime())
                .repeatInterval(scheduledOperation.getSchedule().getInterval())
                .timeUnit(scheduledOperation.getSchedule().getTimeUnit())
                .accountUuid(scheduledOperation.getOperation().getAccount())
                .description(scheduledOperation.getOperation().getDescription())
                .value(scheduledOperation.getOperation().getValue())
                .currencyUuid(scheduledOperation.getOperation().getCurrency())
                .categoryUuid(scheduledOperation.getOperation().getCategory())
                .build();

        scheduledOperationRepository.save(entity);
    }

    @Override
    public void update(UUID uuid, long dtUpdate, ScheduledOperation scheduledOperation) {

        if (scheduledOperationRepository.findById(uuid).isEmpty()) {
            throw new RuntimeException("Not found");
        }
        ScheduledOperationEntity entity = ScheduledOperationEntity.builder()
                .uuid(uuid)
                .dtCreate(scheduledOperation.getDtCreate())
                .dtUpdate(dtUpdate)
                .startTime(scheduledOperation.getSchedule().getStartTime())
                .stopTime(scheduledOperation.getSchedule().getStopTime())
                .repeatInterval(scheduledOperation.getSchedule().getInterval())
                .timeUnit(scheduledOperation.getSchedule().getTimeUnit())
                .accountUuid(scheduledOperation.getOperation().getAccount())
                .description(scheduledOperation.getOperation().getDescription())
                .value(scheduledOperation.getOperation().getValue())
                .currencyUuid(scheduledOperation.getOperation().getCurrency())
                .categoryUuid(scheduledOperation.getOperation().getCategory())
                .build();

        scheduledOperationRepository.save(entity);
    }

    @Override
    public Page getPage(int page, int size) {
        org.springframework.data.domain.Page<ScheduledOperationEntity> entityPage = scheduledOperationRepository
                .findAll(PageRequest.of(page, size));

        List<ScheduledOperation> content = new ArrayList<>();

        for (ScheduledOperationEntity entity : entityPage.getContent()) {
            content.add(ScheduledOperation.builder()
                    .uuid(entity.getUuid())
                    .dtCreate(entity.getDtCreate())
                    .dtUpdate(entity.getDtUpdate())
                    .schedule(
                            Schedule.builder()
                                    .startTime(entity.getStartTime())
                                    .stopTime(entity.getStopTime() != null ? entity.getStopTime() : 0L) // default to 0 if null
                                    .interval(entity.getRepeatInterval())
                                    .timeUnit(entity.getTimeUnit())
                                    .build()
                    )
                    .operation(
                            Operation.builder()
                                    .account(entity.getAccountUuid())
                                    .description(entity.getDescription())
                                    .value(entity.getValue())
                                    .currency(entity.getCurrencyUuid())
                                    .category(entity.getCategoryUuid())
                                    .build()
                    )
                    .build());
        }
        return Page.builder()
                .number(entityPage.getNumber())
                .size(entityPage.getSize())
                .totalPages(entityPage.getTotalPages())
                .totalElements(entityPage.getTotalElements())
                .first(entityPage.isFirst())
                .numberOfElements(entityPage.getNumberOfElements())
                .last(entityPage.isLast())
                .content(content)
                .build();
    }
}
