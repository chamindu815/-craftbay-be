package com.craftbay.crafts;

import com.craftbay.crafts.security.AppProperties;
import com.craftbay.crafts.util.SpringApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableSwagger2
public class CraftsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CraftsApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	@Bean
	public BCryptPasswordEncoder bcryptPasswordEncoder()
	{
		return  new BCryptPasswordEncoder();
	}

	@Bean
	public SpringApplicationContext SpringApplicationContext()
	{
		return  new SpringApplicationContext();
	}

	@Bean
	public AppProperties AppProperties()
	{
		return  new AppProperties();
	}

}
