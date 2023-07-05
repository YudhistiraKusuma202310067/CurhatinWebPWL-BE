package com.pwl.curhatin.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends CrudRepository<User, Integer>{

    @Query(value = "SELECT a.* FROM user a WHERE a.email = :email AND a.password = :password", nativeQuery = true)
    public Iterable<User> findUserAuth(@Param("email") String email, @Param("password") String password);

    @Query(value = "SELECT a.* FROM user a WHERE a.email = :email", nativeQuery = true)
    public Iterable<User> findEmail(@Param("email") String email);

    @Query(value = "SELECT a.* FROM user a WHERE a.username = :username", nativeQuery = true)
    public Iterable<User> findUsername(@Param("username") String username);
}
