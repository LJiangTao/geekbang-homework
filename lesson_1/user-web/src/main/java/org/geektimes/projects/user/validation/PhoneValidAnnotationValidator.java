package org.geektimes.projects.user.validation;

import org.geektimes.projects.user.domain.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class PhoneValidAnnotationValidator implements ConstraintValidator<PhoneValid, User> {

  private static final String CHINESE = "^(1)(\\d{10})$";
  private boolean chineseFormat;
  private String regex;

  public void initialize(PhoneValid annotation) {
    this.chineseFormat = annotation.chineseFormat();
    this.regex = annotation.regexFormat();
  }

  @Override
  public boolean isValid(User value, ConstraintValidatorContext context) {
    String template = context.getDefaultConstraintMessageTemplate();
    return value
        .getPhone()
        .matches(Optional.of(chineseFormat).filter(d -> d).map(d -> CHINESE).orElse(regex));
  }
}
