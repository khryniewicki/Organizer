package com.khryniewicki.organizer.registration_login_logout.services;

import com.khryniewicki.organizer.main_content.model.User;
import com.khryniewicki.organizer.main_content.model.repositories.UserRepository;
import com.khryniewicki.organizer.registration_login_logout.DTO.UserDTO;
import com.khryniewicki.organizer.registration_login_logout.validator.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
@Slf4j
@RequiredArgsConstructor
@Service
public class LoggingUserService implements IUserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public User registerNewUserAccount(UserDTO accountDto)
            throws IOException {
        log.info("registerNewUserAccount");

        if (emailExist(accountDto.getEmail())) {
            throw new IOException(
                    "There is an account with that email adress: "
                            + accountDto.getEmail());
        }

        return saveUser(accountDto);

    }

    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    private User saveUser( UserDTO userDTO){

        User added_user = new User();

        added_user.setName(userDTO.getFirstName());
        added_user.setSurname(userDTO.getSecondName());
        added_user.setEmail(userDTO.getEmail());
        added_user.setPassword(userDTO.getPassword());
        added_user.setRole(userDTO.getRoles());
        added_user.setNick(userDTO.getNick());

        userRepository.save(added_user);

        log.info("User : "+added_user.getEmail()+" dodany.");
        return added_user;
    }

    public User findByName(String name){
        return userRepository.findByName(name);
    }
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

}
