package com.turkcell.repository;

import com.turkcell.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository <User ,Long> {

}
