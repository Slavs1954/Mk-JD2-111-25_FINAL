package by.it_academy.jd2.dto;

import by.it_academy.jd2.dto.enums.TimeUnit;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Schedule {
    long startTime;
    long stopTime;
    long interval;
    TimeUnit timeUnit;
}
