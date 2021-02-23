package br.com.zup.casadocodigo.validation.constraints;

import br.com.zup.casadocodigo.validation.annotation.FieldAlias;
import org.springframework.beans.BeanWrapperImpl;
import br.com.zup.casadocodigo.validation.annotation.UniqueValues;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class UniqueValuesConstraint implements ConstraintValidator<UniqueValues, Object> {
    @PersistenceContext
    private EntityManager manager;

    private List<String> fields;

    private Class<?> domainClass;

    private String message;

    @Override
    public void initialize(UniqueValues constraintAnnotation) {
        fields = Arrays.asList(constraintAnnotation.fields());
        domainClass = constraintAnnotation.domainClass();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o == null) return true;
        var isValid = createQuery(o).getResultList().isEmpty();
        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            for (var field : fields) {
                constraintValidatorContext
                        .buildConstraintViolationWithTemplate(message)
                        .addPropertyNode(field).addConstraintViolation();
            }
        }
        return isValid;
    }

    private String getTableName() {
        return domainClass.getSimpleName();
    }

    private Query createQuery(Object o) {
        var stringBuilder = new StringBuilder();
        var bean = new BeanWrapperImpl(o);
        stringBuilder.append("select t from ").append(getTableName()).append(" t where ");
        fields.forEach(field -> {
            var alias = field;
            try {
                var fieldAlias = o.getClass().getDeclaredField(field).getAnnotation(FieldAlias.class);
                if (fieldAlias != null) {
                    alias = fieldAlias.value();
                }
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            stringBuilder
                    .append(" and t.")
                    .append(alias)
                    .append(" = '")
                    .append(bean.getPropertyValue(field))
                    .append("'");

        });
        return manager.createQuery(stringBuilder.toString().replaceFirst("and", ""));
    }
}
