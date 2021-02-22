package zup.jonas.souza.casadocodigo.validation.annotation;

import zup.jonas.souza.casadocodigo.validation.interfaces.RequiredCondition;
import zup.jonas.souza.casadocodigo.validation.constraints.RequiredIfConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RequiredIfConstraint.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredIf {

    Class<? extends RequiredCondition> value();

    String field();

    String message() default "obrigatório";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
