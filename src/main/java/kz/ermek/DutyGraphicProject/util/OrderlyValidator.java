package kz.ermek.DutyGraphicProject.util;

import kz.ermek.DutyGraphicProject.models.Orderly;
import kz.ermek.DutyGraphicProject.services.OrderlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class OrderlyValidator implements Validator {
    private final OrderlyService orderlyService;

    @Autowired
    public OrderlyValidator(OrderlyService orderlyService) {
        this.orderlyService = orderlyService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Orderly.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        int countOfOrderly = 2;

        Orderly orderly = (Orderly) target;


    }
}
