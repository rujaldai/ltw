package com.mcve.ltw.repository;

import com.mcve.ltw.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * If we use {@link JpaRepository} here, it will work.
 */
public interface UserRepository extends CrudRepository<Users, Long> {
}
