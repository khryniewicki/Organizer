package com.khryniewicki.organizer.main_content.model.repositories;

import com.khryniewicki.organizer.main_content.model.Project;
import com.khryniewicki.organizer.main_content.model.Sprint;
import com.khryniewicki.organizer.main_content.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    Optional<List<Task>> findAllBySprint(Sprint sprint);
    Optional<List<Task>> findAllByProjectId(Long id);
}
