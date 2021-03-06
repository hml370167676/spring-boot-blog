package com.hml.blog.repository;

import com.hml.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * The interface User repositiry.
 *
 * @author minglu
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
