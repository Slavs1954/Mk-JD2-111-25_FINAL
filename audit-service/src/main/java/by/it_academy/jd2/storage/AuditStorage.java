package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.Audit;
import by.it_academy.jd2.dto.Page;
import by.it_academy.jd2.dto.User;
import by.it_academy.jd2.storage.api.IAuditStorage;
import by.it_academy.jd2.storage.entity.AuditEntity;
import by.it_academy.jd2.storage.repository.AuditRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class AuditStorage implements IAuditStorage {
    private AuditRepository auditRepository;

    @Override
    public Page getPage(int page, int size) {
        org.springframework.data.domain.Page<AuditEntity> entityPage = auditRepository.findAll(PageRequest.of(page, size));
        List<Audit> content = new ArrayList<>();
        for (AuditEntity auditEntity : entityPage.getContent()) {
            content.add(Audit.builder()
                            .uuid(auditEntity.getUuid())
                            .dtCreate(auditEntity.getDtCreate())
                            .user(User.builder()
                                    .mail(auditEntity.getUserMail())
                                    .fio(auditEntity.getUserFio())
                                    .role(auditEntity.getUserRole())
                                    .uuid(auditEntity.getUserUuid())
                                    .build())
                            .text(auditEntity.getText())
                            .type(auditEntity.getType())
                            .id(auditEntity.getEntityId())
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

    @Override
    public Audit getAudit(UUID uuid) {
        AuditEntity auditEntity = auditRepository.findById(uuid).orElseThrow();
        return Audit.builder()
                .uuid(auditEntity.getUuid())
                .dtCreate(auditEntity.getDtCreate())
                .user(User.builder()
                        .mail(auditEntity.getUserMail())
                        .fio(auditEntity.getUserFio())
                        .role(auditEntity.getUserRole())
                        .uuid(auditEntity.getUserUuid())
                        .build())
                .text(auditEntity.getText())
                .type(auditEntity.getType())
                .id(auditEntity.getEntityId())
                .build();
    }

    @Override
    public void saveAudit(Audit audit) {
        AuditEntity auditEntity = AuditEntity.builder()
                .uuid(audit.getUuid())
                .dtCreate(audit.getDtCreate())
                .userMail(audit.getUser() != null ? audit.getUser().getMail() : null)
                .userFio(audit.getUser() != null ? audit.getUser().getFio() : null)
                .userRole(audit.getUser() != null ? audit.getUser().getRole() : null)
                .userUuid(audit.getUser() != null ? audit.getUser().getUuid() : null)
                .text(audit.getText())
                .type(audit.getType())
                .entityId(audit.getId())
                .build();
        auditRepository.save(auditEntity);

    }
}
