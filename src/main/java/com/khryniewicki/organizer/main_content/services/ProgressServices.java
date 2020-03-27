package com.khryniewicki.organizer.main_content.services;

import com.khryniewicki.organizer.main_content.model.Progress;
import com.khryniewicki.organizer.main_content.model.repositories.ProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class ProgressServices {
    private final ProgressRepository progressRepository;

    public List<Progress> findAllProgress(){
        boolean empty = progressRepository.findAll().isEmpty();
        LinkedHashMap<Integer,Progress> linkedMap=new LinkedHashMap<>();
        if (empty){
             linkedMap.put(1,new Progress("Backlog"));
             linkedMap.put(2, new Progress("Gotowe do pracy"));
             linkedMap.put(3,new Progress( "W toku"));
             linkedMap.put(4,new Progress("Testowanie"));
             linkedMap.put(5,new Progress("Zakończone"));
            for (Map.Entry<Integer, Progress> integerProgressEntry : linkedMap.entrySet()) {
                System.out.println(integerProgressEntry.getValue());
                progressRepository.save(integerProgressEntry.getValue()); };
        }
        return progressRepository.findAll();
    }


}
