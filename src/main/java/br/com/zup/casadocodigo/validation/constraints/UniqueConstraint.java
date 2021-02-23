package br.com.zup.casadocodigo.validation.constraints;

import br.com.zup.casadocodigo.validation.annotation.Unique;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueConstraint implements ConstraintValidator<Unique, Object> {

    @PersistenceContext
    private EntityManager manager;

    private String field;

    private Class<?> modelClass;

    @Override
    public void initialize(Unique constraintAnnotation) {
        field = constraintAnnotation.field();
        modelClass = constraintAnnotation.modelClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) return true;
        return createQuery().setParameter("value", value).getResultList().isEmpty();
    }

    private String getTableName() {
        return modelClass.getSimpleName();
    }

    private Query createQuery() {
        return manager.createQuery("from " + getTableName() + " t where t." + field + " = :value");
    }
}
