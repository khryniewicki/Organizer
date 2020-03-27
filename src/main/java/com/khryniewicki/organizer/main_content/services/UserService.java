package com.khryniewicki.organizer.main_content.services;

import com.khryniewicki.organizer.main_content.model.User;
import com.khryniewicki.organizer.main_content.model.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String getInitialLetters(User user){
        String initials=user.getName().toUpperCase().substring(0,1)+user.getSurname().toUpperCase().substring(0,1);
        return initials;
    }

}
