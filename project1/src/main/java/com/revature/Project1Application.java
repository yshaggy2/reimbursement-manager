package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.revature.model") //This tells Spring Boot to look in the model package for DB entities
@ComponentScan("com.revature") //This tells Spring Boot to look for beans in the entire com.rev package
@EnableJpaRepositories("com.revature.DAO") //This allows us to use JpaRepository in our DAOS
public class Project1Application {
//	TODO: IMPLEMENT SERVICE LAYER
//	TODO: IMPLEMENT CONTROLLER LAYER

	public static void main(String[] args) {
		SpringApplication.run(Project1Application.class, args);
	}

}


/* 		USERS:
Create an account (create new User – default role should be employee) ✅

Create a new Reimbursement ✅(I think)

See all reimbursement tickets (only their own) ✅

See only their pending reimbursement tickets ✅

[Some other functionality of your choice]

*/



/*		MANAGERS:
See all reimbursements ✅

See all pending reimbursements ✅

Resolve a reimbursement (update status from PENDING to Approved or Denied) ✅


See all Users ✅

Delete a User ✅


OPTIONAL: Update an employee’s role to manager ✅
*/


/*
todo: login + permissions logic
Validation User Stories (Do Login Last!!)

Users who are not logged in to the application can ONLY:

Attempt to log in.

Users should not be able to access the other user stories before logging in.



Optional User Stories (Only try these after completing the stories above)

Logging of the Service layer with logback.

Test Suites for the Service layer with JUnit

Log out
* */
