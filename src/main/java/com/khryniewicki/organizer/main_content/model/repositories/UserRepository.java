package com.khryniewicki.organizer.main_content.model.repositories;

import com.khryniewicki.organizer.main_content.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
