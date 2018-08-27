package com.technomak.employeetracker;

import java.util.TimeZone;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author JaY
 *
 */
@SpringBootApplication
@EnableSwagger2
@Configuration
@EnableAutoConfiguration
public class EmployeeTrackerApplication extends WebMvcConfigurerAdapter {
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeTrackerApplication.class);

	public static void main(String[] args) {
		try {
			SpringApplication.run(EmployeeTrackerApplication.class, args);
		} catch (Exception e) {
			LOG.error("Exception:", e);
		}
	}

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.technomak.employeetracker.controller"))              
          .paths(PathSelectors.any())                          
          .build();                                           
    }

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/documentation/v2/api-docs", "/v2/api-docs");
		registry.addRedirectViewController("/documentation/configuration/ui", "/configuration/ui");
		registry.addRedirectViewController("/documentation/configuration/security", "/configuration/security");
		registry.addRedirectViewController("/documentation/swagger-resources", "/swagger-resources");
		registry.addRedirectViewController("/documentation", "/documentation/swagger-ui.html");
		registry.addRedirectViewController("/documentation/", "/documentation/swagger-ui.html");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/documentation/**").addResourceLocations("classpath:/META-INF/resources/");
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
		return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
	}
}
