package by.it_academy.jd2.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ScheduledOperation {
    UUID uuid;
    long dtCreate;
    long dtUpdate;
    Schedule schedule;
    Operation operation;
}
