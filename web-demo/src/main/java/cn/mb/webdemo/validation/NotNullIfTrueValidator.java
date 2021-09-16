package cn.mb.webdemo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNullIfTrueValidator implements ConstraintValidator<NotNullIfTrue, Boolean> {
    @Override
    public boolean isValid(Boolean aBoolean, ConstraintValidatorContext constraintValidatorContext) {
        return aBoolean;
    }
}
