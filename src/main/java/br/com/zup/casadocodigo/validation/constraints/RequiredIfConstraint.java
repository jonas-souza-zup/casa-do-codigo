package br.com.zup.casadocodigo.validation.constraints;

import br.com.zup.casadocodigo.validation.annotation.RequiredIf;
import br.com.zup.casadocodigo.validation.interfaces.RequiredCondition;
import org.springframework.beans.BeanWrapperImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

public class RequiredIfConstraint implements ConstraintValidator<RequiredIf, Object> {
    @PersistenceContext
    private EntityManager manager;

    private RequiredCondition condition;

    private String field;

    private String message;

    @Override
    public void initialize(RequiredIf constraintAnnotation) {
        try {
            condition = constraintAnnotation.value().getDeclaredConstructor().newInstance();
            field = constraintAnnotation.field();
            message = constraintAnnotation.message();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        var bean = new BeanWrapperImpl(value);
        if (bean.getPropertyValue(field) != null) return true;
        var isValid = !condition.isRequired(manager, value);
        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(field).addConstraintViolation();
        }
        return isValid;
    }
}