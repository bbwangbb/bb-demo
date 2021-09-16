package cn.mb.webdemo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotNullIfTrueValidator.class)
@Documented
public @interface NotNullIfTrue {
    boolean result() default false;

    String message() default "该值不能为空";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
