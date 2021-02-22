package zup.jonas.souza.casadocodigo.validation.annotation;

import zup.jonas.souza.casadocodigo.validation.constraints.UniqueConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = UniqueConstraint.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Unique {
    String field();

    Class<?> modelClass();

    String message() default "deve ser único";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
