package zup.jonas.souza.casadocodigo.validation.constraints;

import zup.jonas.souza.casadocodigo.validation.annotation.Exists;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsConstraint implements ConstraintValidator<Exists, Object> {
    @PersistenceContext
    private EntityManager manager;

    private String field;

    private Class<?> modelClass;

    @Override
    public void initialize(Exists constraintAnnotation) {
        field = constraintAnnotation.field();
        modelClass = constraintAnnotation.modelClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null) return true;
        return !createQuery(value).getResultList().isEmpty();
    }

    private String getTableName() {
        return modelClass.getSimpleName();
    }

    private Query createQuery(Object value) {
        return manager.createQuery("from " + getTableName() + " t where t." + field + " = '" + value + "'");
    }

}
