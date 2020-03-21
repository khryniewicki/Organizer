package com.khryniewicki.organizer.main_content.services;

import com.khryniewicki.organizer.main_content.model.Progress;
import com.khryniewicki.organizer.main_content.model.repositories.ProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProgressServices {
    private final ProgressRepository progressRepository;

    public List<Progress> findAllProgress(){
        boolean empty = progressRepository.findAll().isEmpty();
        if (empty){
            List<Progress> progresses1 = Arrays.asList(
                    new Progress("Backlog"),
                    new Progress("Gotowe do pracy"),
                    new Progress("W toku"),
                    new Progress("Testowanie"),
                    new Progress("Zakończone"));
            for (Progress progress : progresses1) {
                progressRepository.save(progress);
            }
        }
        return progressRepository.findAll();
    }


}
