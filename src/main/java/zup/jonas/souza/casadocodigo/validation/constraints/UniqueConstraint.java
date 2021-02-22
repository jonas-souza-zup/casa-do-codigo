package zup.jonas.souza.casadocodigo.validation.constraints;

import zup.jonas.souza.casadocodigo.validation.annotation.Unique;

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
        return createQuery(value).getResultList().isEmpty();
    }

    private String getTableName() {
        return modelClass.getSimpleName();
    }

    private Query createQuery(Object value) {
        return manager.createQuery("select t from " + getTableName() + " t where t." + field + " = '" + value + "'");
    }
}
