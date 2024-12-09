package com.portmoor.songbird;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
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
