package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.*;
import by.it_academy.jd2.dto.annotaions.AuditPoint;
import by.it_academy.jd2.dto.api.ReportParams;
import by.it_academy.jd2.dto.enums.ReportStatus;
import by.it_academy.jd2.dto.enums.ReportType;
import by.it_academy.jd2.dto.enums.Type;
import by.it_academy.jd2.service.api.IReportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static by.it_academy.jd2.dto.enums.ReportType.*;

@RestController
@AllArgsConstructor
@RequestMapping("/report")
public class ReportController {

    private final IReportService reportService;

    @PostMapping("/{type}")
    @AuditPoint(type = Type.REPORT)
    public ResponseEntity<Report> createReport(
            @PathVariable ReportType type,
            @RequestBody JsonNode body,
            ObjectMapper objectMapper) throws JsonProcessingException {

        ReportParams params = switch (type) {
            case BALANCE -> objectMapper.treeToValue(body, ReportParamBalance.class);
            case BY_DATE -> objectMapper.treeToValue(body, ReportParamByDate.class);
            case BY_CATEGORY -> objectMapper.treeToValue(body, ReportParamByCategory.class);
        };

        Report report = Report.builder()
                .id(UUID.randomUUID())
                .dtCreate(System.currentTimeMillis())
                .dtUpdate(System.currentTimeMillis())
                .status(ReportStatus.LOADED)
                .type(type)
                .params(params)
                .build();

        reportService.create(report);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{uuid}/export")
    @AuditPoint(type = Type.REPORT)
    public ResponseEntity<byte[]> getReport(@PathVariable UUID uuid) {
        Report report = reportService.get(uuid);
        if (report == null) {
            return ResponseEntity.notFound().build();
        }
        // TODO: make it return actual file
        byte[] fakeFile = {1,0,0,1};

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + report.getId() + ".xlsx\"")
                .header(HttpHeaders.CONTENT_TYPE,
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .body(fakeFile);
    }

    @GetMapping
    @AuditPoint(type = Type.REPORT)
    public ResponseEntity<Page> getAllReports(Optional<Integer> page, Optional<Integer> size) {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.getAll(page.orElse(0), size.orElse(20)));
    }

    @RequestMapping(path = "/{uuid}/export", method = RequestMethod.HEAD)
    @AuditPoint(type = Type.REPORT)
    public ResponseEntity<String> processReport(@PathVariable UUID uuid) {
        Report report = reportService.get(uuid);
        if (report == null) {
            return ResponseEntity.notFound().build();
        }

        else {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }



}
