package by.it_academy.jd2.dto.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StructuredErrorResponse {
    private String logref;
    private List<ErrorResponse> errors;
}
