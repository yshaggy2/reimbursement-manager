package com.revature.DAO;

import com.revature.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* By extending Jpa Repository, we get access to various DAO methods that we don't need to write

JpaRepository takes two generics:
- The Java model we intend to perform DB operations with
-The data type of the Primary Key of that Model

 */
@Repository     //1 of the 4 stereotype annotations. Registers this Interface as a Bean
public interface UserDAO extends JpaRepository<User, Integer> {
        /* I want to be able to find a USer by their username
        since JPARepository doesn't have that method, we must make our own
        Spring Data is smart enough to implement hits method for us. We just need to define the abstract*/
    public User findByUsername(String username);

    //The method must be named findByFieldNextfield, otherwise it won't work
    //example: findByUsername, findByUsernameAndPassword
    //This is referred to as a PROPERTY EXPRESSION
}
