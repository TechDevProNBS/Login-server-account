package com.nationwide.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nationwide.mapping.Mapping;
import com.nationwide.service.PasswordSecurity;
import com.nationwide.util.Hashing;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class AccountConfig {

	@Bean
	public Hashing hashing() {
		return new Hashing();
	}
	
	@Bean
	public PasswordSecurity passwordSecurity() {
		return new PasswordSecurity();
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public Mapping mapping() {
		return new Mapping();
	}

	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }

}