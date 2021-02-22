package zup.jonas.souza.casadocodigo.validation.annotation;

import zup.jonas.souza.casadocodigo.validation.RequiredCondition;
import zup.jonas.souza.casadocodigo.validation.constraints.RequiredIfConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RequiredIfConstraint.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredIf {

    Class<? extends RequiredCondition> value();

    String message() default "obrigatório";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
