package com.khryniewicki.organizer.registration_login_logout.services;

import com.khryniewicki.organizer.main_content.model.*;
import com.khryniewicki.organizer.main_content.model.repositories.TaskRepository;
import com.khryniewicki.organizer.main_content.model.repositories.UserRepository;
import com.khryniewicki.organizer.main_content.services.ProjectService;
import com.khryniewicki.organizer.main_content.services.TaskServices;
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
    private final ProjectService projectService;
    private final TaskServices taskServices;

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

    private User saveUser(UserDTO userDTO) {

        User newUser = new User();

        newUser.setName(userDTO.getFirstName());
        newUser.setSurname(userDTO.getSecondName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(userDTO.getPassword());
        newUser.setRole(userDTO.getRoles());
        newUser.setNick(userDTO.getNick());

        userRepository.save(newUser);

        log.info("User : " + newUser.getEmail() + " added.");
        addInitialProjectAndTask(newUser);
        return newUser;
    }


    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private void addInitialProjectAndTask(User newUser) {
        Project initialProject = projectService.addInitialProject(newUser);
        Task task = new Task("Nowe zapytanie", "Opis", initialProject, Priority.LOW, TypeOfStory.STORY,"Backlog");
        taskServices.saveTask(task);
    }

}
