package org.geektimes.projects.user.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidAnnotationValidator.class)
public @interface PhoneValid {

  boolean chineseFormat() default true;

  String regexFormat() default "";

  String message() default "格式错误";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
