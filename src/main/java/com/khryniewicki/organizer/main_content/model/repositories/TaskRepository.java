package com.khryniewicki.organizer.main_content.model.repositories;

import com.khryniewicki.organizer.main_content.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}
