package com.revature.HelloSpring;

import com.revature.models.Owner;
import com.revature.models.Pet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
		//Create an ApplicationContext Object based off our applicationContext.xml file
		//This object will serve as our Spring IoC container, and manage our beans + dep. injection

		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContextAnnotationDriven.xml");
		//we can now use our ApplicationContext object to do a bunch of stuff with beans
		String[] beanNames = ac.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
		//Ask for an Owner Bean
		Owner o = ac.getBean(Owner.class);
		System.out.println(o);	//note the default values, we  didn't set any properties

		Pet p = ac.getBean(Pet.class);
		System.out.println(p);
		//Pet class has Owner object that is dependency injected
		//Instantiated for us by Bean wiring
		p.setName("Koko");
		p.setAge(2);
		p.setSpecies("German Spitz");
		p.getOwner().setName("Luis");
		p.getOwner().setAge(25);
		System.out.println(p);

		System.out.println("---------Bean Scopes---------");
		//Let's try to get a new Pet Bean and print it out
		Pet p2 = ac.getBean(Pet.class);
		System.out.println(p2);

		//Beans by default are SINGLETONS (they're singleton scoped)
		//This means there can only be one instance at a time, unless we change it.

		//From here, we set Pet to be prototyped scoped, but we left Owner as default singleton
		//Every time we ask for a Pet, we'll get a new instance, with the same Owner instance

	}

}
