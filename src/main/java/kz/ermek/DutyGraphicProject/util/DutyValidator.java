package kz.ermek.DutyGraphicProject.util;

import kz.ermek.DutyGraphicProject.models.Duty;
import kz.ermek.DutyGraphicProject.services.DutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DutyValidator implements Validator {
    private final DutyService dutyService;

    @Autowired
    public DutyValidator(DutyService dutyService) {
        this.dutyService = dutyService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Duty.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Duty duty = (Duty) target;

        if (dutyService.getDutyByName(duty.getDutyName()).isPresent()) {
            errors.rejectValue("duty", "", "Duty with this name already exist");
        }
    }
}
