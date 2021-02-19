package zup.jonas.souza.casadocodigo.validation.annotation;

import zup.jonas.souza.casadocodigo.validation.UniqueValuesConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = UniqueValuesConstraint.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UniqueValues {

    String[] fields();

    Class<?> domainClass();

    String message() default "deve ser único";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
