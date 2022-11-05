package ru.mashurov.rest.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.mashurov.rest.model.pojo.UserJ;
import ru.mashurov.rest.services.ClinicService;
import ru.mashurov.rest.services.UserService;

import static ru.mashurov.rest.utils.ErrorMessages.CLINIC_NOT_EXIST;
import static ru.mashurov.rest.utils.ErrorMessages.USER_NOT_REGISTERED;

@Component
@AllArgsConstructor
public class CheckHelper {

    private final UserService userService;
    private final ClinicService clinicService;

    public boolean checkClinic(final Long id) {

        if (!clinicService.existById(id)) {

            //TODO 404
            throw new RuntimeException(CLINIC_NOT_EXIST);
        }

        return true;
    }

    public UserJ checkUser(final Long id) {

        if (!userService.existById(id)) {

            //TODO 404
            throw new RuntimeException(USER_NOT_REGISTERED);
        }

        return userService.findById(id);
    }
}
