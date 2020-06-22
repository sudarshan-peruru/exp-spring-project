package com.springproject.expproject;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class ExpProjectApplication {
	//comments
	public static void main(String[] args) {
		SpringApplication.run(ExpProjectApplication.class, args);
	}

	@Bean
	public LocaleResolver localRes() {
		AcceptHeaderLocaleResolver localeRes = new AcceptHeaderLocaleResolver();
		localeRes.setDefaultLocale(Locale.US);
		return localeRes;
	}
	
	@Bean
	public ResourceBundleMessageSource messageSrc() {
		ResourceBundleMessageSource messageSrc = new ResourceBundleMessageSource();
		messageSrc.setBasename("messages");
		return messageSrc;
	}
	
}
