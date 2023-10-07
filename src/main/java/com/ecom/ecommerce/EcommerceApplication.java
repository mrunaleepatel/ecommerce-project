package com.ecom.ecommerce;

import com.ecom.ecommerce.configuration.JpaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Import(JpaConfig.class)
@EntityScan(basePackages = "com.ecom.ecommerce")
@EnableJpaRepositories(basePackages = "com.ecom.ecommerce.repository")
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
