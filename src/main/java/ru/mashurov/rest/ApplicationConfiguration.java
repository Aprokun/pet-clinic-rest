package ru.mashurov.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public ObjectMapper objectMapper() {

		return JsonMapper.builder()
				.findAndAddModules()
				.build();
	}
}
