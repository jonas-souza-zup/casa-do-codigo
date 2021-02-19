package zup.jonas.souza.casadocodigo.validation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.Repository;
import org.springframework.util.StringUtils;
import zup.jonas.souza.casadocodigo.validation.annotation.Unique;

import javax.el.MethodNotFoundException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class UniqueConstraint implements ConstraintValidator<Unique, String> {
    @Autowired
    private ApplicationContext context;

    private Repository<?, ?> repository;

    private String fieldName;

    @Override
    public void initialize(Unique constraintAnnotation) {
        fieldName = constraintAnnotation.name();
        repository = context.getBean(constraintAnnotation.repository());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        var methodName = getMethodName(this.fieldName);
        var method = BeanUtils.findMethod(repository.getClass(), methodName, String.class);
        if (method != null) {
            try {
                var result = method.invoke(repository, value);
                if (result == null && method.getReturnType().isAssignableFrom(List.class)) return true;
                if (result instanceof List) {
                    return ((List<?>) result).isEmpty();
                }
                return false;
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        throw new MethodNotFoundException("Method " + methodName + " not Found");
    }

    private String getMethodName(String fieldName) {
        return "findBy" + StringUtils.capitalize(fieldName);
    }
}
