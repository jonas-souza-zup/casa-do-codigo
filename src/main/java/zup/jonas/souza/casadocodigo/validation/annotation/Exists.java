package zup.jonas.souza.casadocodigo.validation.annotation;

import zup.jonas.souza.casadocodigo.validation.constraints.ExistsConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = ExistsConstraint.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Exists {
    String field();

    Class<?> modelClass();

    String message() default "não existe";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
