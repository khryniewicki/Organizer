package com.khryniewicki.organizer.registration_login_logout.validator;

import com.khryniewicki.organizer.main_content.model.User;
import com.khryniewicki.organizer.registration_login_logout.DTO.UserDTO;

import java.io.IOException;

public interface IUserService {
    User registerNewUserAccount(UserDTO accountDto)
            throws IOException;
}
