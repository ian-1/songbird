package com.portmoor.songbird;

import com.portmoor.songbird.models.User;
import com.portmoor.songbird.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.portmoor.songbird.models.Utils.Gender.*;
import static com.portmoor.songbird.models.Utils.GenderIdentity.CIS;

@SpringBootApplication
@EnableMongoRepositories("com.portmoor.songbird.repositories")
public class SongbirdApplication {

	public static void main(String[] args) {
		SpringApplication.run(SongbirdApplication.class, args);
	}

	private final String WHITE = "\u001B[0m";
	private final String GREEN = "\u001B[32m";
	private final String YELLOW = "\u001B[33m";
	private final String CYAN = "\u001B[34m";
	private final String TURQUOISE = "\u001B[36m";
	private final String GREY = "\u001B[90m";

	@Value("${ignoreBeans}")
	private List<String>  ignoreBeans;

	@Autowired
	RequestMappingHandlerMapping requestMappingHandlerMapping;

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			printBreakLine(60);
			printBeans(ctx);
			printBreakLine(60);
			printEndpoints();
			printBreakLine(60);
		};
	}

	// TODO: Remove repo stuff from application START
	@Autowired
	private UserRepository repository;

	@Bean
	public CommandLineRunner databaseExample() throws Exception {
		repository.deleteAll();

		repository.save(new User("AliceThePalice", FEMALE, false, 2001));
		repository.save(new User("BobDbobDbob", MALE, false, 1997));
		repository.save(new User("Zarahhhh", FEMALE, true, 2004));
		repository.save(new User("JanJan", OTHER, true, 1999));
		repository.save(new User("Bennnny", NONBINARY, false, 2001));
		repository.save(new User("BillBow", MALE, false, 2002));

		return args -> {
			System.out.println("Users found with findAll():");
			System.out.println("-------------------------------");
			for (User user : repository.findAll()) {
				System.out.println(user);
			}
			System.out.println();

			System.out.println("Customer found with findByFirstName('BobDbobDbob'):");
			System.out.println("--------------------------------");
			System.out.println(repository.findByUserName("BobDbobDbob"));

			System.out.println("Customers found with findByGender('FEMAIL'):");
			System.out.println("--------------------------------");
			for (User femaleUser : repository.findByGender(FEMALE)) {
				System.out.println(femaleUser);
			}

			System.out.println("Customers found with findByDateOFBirth(2001):");
			System.out.println("--------------------------------");
			for (User user : repository.findByYearOfBirth(2001)) {
				System.out.println(user);
			}
		};
	}
	// TODO: Remove repo stuff from application END

	private void printBreakLine(final Integer length) {
		System.out.println(YELLOW + "-".repeat(length));
		System.out.print(WHITE);
	}

	private void printBeans(ApplicationContext ctx) {
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		System.out.println(WHITE + "Beans (in ignore list):");
		for (String beanName : beanNames) {
			if (ignoreBeans.contains(beanName)) {
				System.out.println(GREY + "  " + beanName);
			}
		}
		System.out.println(WHITE + "Beans (not in ignore list):");
		for (String beanName : beanNames) {
			if (!ignoreBeans.contains(beanName)) {
				System.out.println(CYAN + "  " + beanName);
			}
		}
		System.out.print(WHITE);
	}

	@PostConstruct
	private void printEndpoints() {
		System.out.println("Endpoints:");
		requestMappingHandlerMapping.getHandlerMethods().forEach((k,v) -> {
			final String strippedK = k.toString().substring(1, k.toString().length() - 2);
			final String[] partsK = strippedK.split("\\[", 2);
			if (partsK[0].length() == 1) {
				System.out.println(GREY + " " + k + " : " + v);
			} else {
				final String[] partsV = v.toString().split("\\#", 2);
				System.out.println(YELLOW + "  " + partsK[0] + GREEN + partsK[1]);
				System.out.println(WHITE + "      ⇒ " + CYAN + partsV[0] + TURQUOISE + "#" + partsV[1]);
			}
		});
		System.out.print(WHITE);
	}
}
