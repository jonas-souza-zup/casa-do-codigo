package zup.jonas.souza.casadocodigo.validation.annotation;

import org.springframework.data.repository.Repository;
import zup.jonas.souza.casadocodigo.validation.UniqueConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = UniqueConstraint.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Unique {
    String name();

    Class<? extends Repository<?, ?>> repository();

    String message() default "deve ser único";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
