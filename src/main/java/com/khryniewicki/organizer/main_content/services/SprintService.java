package com.khryniewicki.organizer.main_content.services;

import com.khryniewicki.organizer.main_content.model.Sprint;
import com.khryniewicki.organizer.main_content.model.repositories.SprintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SprintService {

    private final SprintRepository sprintRepository;

    public List<Sprint> findAll(){
        return  sprintRepository.findAll();
    }
    public Sprint findById(Long id){
       return sprintRepository.findById(id).orElseThrow(()->new IllegalArgumentException("brak"));
    }
}
