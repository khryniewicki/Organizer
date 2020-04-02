package com.khryniewicki.organizer.main_content.services;

import com.khryniewicki.organizer.main_content.Utills.UtillClass;
import com.khryniewicki.organizer.main_content.model.Project;
import com.khryniewicki.organizer.main_content.model.User;
import com.khryniewicki.organizer.main_content.model.repositories.ProjectRepository;
import com.khryniewicki.organizer.main_content.model.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;


    public String getInitialLetters(User user) {
        String initials = user.getName().toUpperCase().substring(0, 1) + user.getSurname().toUpperCase().substring(0, 1);
        return initials;
    }

    public List<User> getAllUsersApartActiveUser() {
        List<User> all = userRepository.findAll();
        return all.stream()
                .filter(user -> !user.getIdUser().equals(UtillClass.getLoggedInUser().getIdUser()))
                .collect(Collectors.toList());

    }


    public List<User> getAllUsersToProject(Long id) {
        List<User> all = userRepository.findAll();
        Optional<Project> byId = projectRepository.findById(id);
        List<User> collect = new ArrayList<>();
        if (byId.isPresent()) {
            collect = all.stream()
                    .filter(user -> !user.getIdUser().equals(UtillClass.getLoggedInUser().getIdUser()))
                    .filter(user -> user.getProjects().stream().
                            anyMatch(e -> e.getId() == id))
                    .distinct()
                    .collect(Collectors.toList());

        }
        return collect;
    }

    public User findUserById(Long id) {
        Optional<User> UserbyId = userRepository.findById(id);
        if (UserbyId.isPresent()) {
            return UserbyId.get();
        } else return new User();
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
