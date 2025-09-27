package by.it_academy.jd2.dto.annotaions;

import by.it_academy.jd2.dto.enums.Type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditPoint {
    Type type();
}
