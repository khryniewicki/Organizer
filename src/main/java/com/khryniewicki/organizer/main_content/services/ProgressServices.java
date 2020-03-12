package com.khryniewicki.organizer.main_content.services;

import com.khryniewicki.organizer.main_content.model.Progress;
import com.khryniewicki.organizer.main_content.model.repositories.ProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProgressServices {
    private final ProgressRepository progressRepository;

    public List<Progress> findAllProgress(){
        return progressRepository.findAll();
    }
}
