package com.khryniewicki.organizer.main_content.model.repositories;

import com.khryniewicki.organizer.main_content.model.Href;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HrefRepository extends JpaRepository<Href,Long> {
   Optional<Href> findByUsername (String name);
    Optional<Href> findByIdProject (Long idProject);

}
