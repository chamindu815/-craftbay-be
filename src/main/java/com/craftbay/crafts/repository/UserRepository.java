package com.craftbay.crafts.repository;

import com.craftbay.crafts.dto.login.LoginDto;
import com.craftbay.crafts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    User findByEmail(String email);

    User findByUserId(String userId);


}
