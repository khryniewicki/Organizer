package com.khryniewicki.organizer.main_content.model.repositories;

import com.khryniewicki.organizer.main_content.model.Href;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HrefRepository extends JpaRepository<Href,Long> {
}
