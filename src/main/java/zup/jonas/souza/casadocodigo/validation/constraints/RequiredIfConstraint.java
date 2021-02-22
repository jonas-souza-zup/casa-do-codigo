package zup.jonas.souza.casadocodigo.validation.constraints;

import zup.jonas.souza.casadocodigo.controller.form.NovoClienteForm;
import zup.jonas.souza.casadocodigo.validation.RequiredCondition;
import zup.jonas.souza.casadocodigo.validation.annotation.RequiredIf;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

public class RequiredIfConstraint implements ConstraintValidator<RequiredIf, Object> {
    @PersistenceContext
    private EntityManager manager;

    private RequiredCondition condition;

    @Override
    public void initialize(RequiredIf constraintAnnotation) {
        try {
            condition = constraintAnnotation.value().getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
//        var unwrap = constraintValidatorContext.
        return condition.isRequired(manager, value);
    }
}
