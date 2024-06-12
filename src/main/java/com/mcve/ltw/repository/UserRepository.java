package com.mcve.ltw.repository;

import com.mcve.ltw.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long> {
}
