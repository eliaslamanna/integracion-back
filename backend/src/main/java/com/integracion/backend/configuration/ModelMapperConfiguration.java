/* Copyright 2020 the original author or authors. All rights reserved. */
package com.integracion.backend.configuration;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		final ModelMapper modelMapper = new ModelMapper();

		modelMapper.getConfiguration().setMatchingStrategy(STRICT);
		modelMapper.getConfiguration().setSkipNullEnabled(true);

		return modelMapper;
	}
}