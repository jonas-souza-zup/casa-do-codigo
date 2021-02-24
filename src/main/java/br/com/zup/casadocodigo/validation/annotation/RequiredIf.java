package br.com.zup.casadocodigo.validation.annotation;

import br.com.zup.casadocodigo.validation.interfaces.RequiredIfCondition;
import br.com.zup.casadocodigo.validation.constraints.RequiredIfConstraint;

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

    Class<? extends RequiredIfCondition> condition();

    String field();

    String message() default "obrigatório";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
