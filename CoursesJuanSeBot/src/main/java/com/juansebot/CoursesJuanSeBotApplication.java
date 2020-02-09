package com.juansebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication //(exclude={DataSourceAutoConfiguration.class})
@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class})
public class CoursesJuanSeBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursesJuanSeBotApplication.class, args);
	}

}
