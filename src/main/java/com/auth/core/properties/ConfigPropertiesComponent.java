package com.auth.core.properties;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class ConfigPropertiesComponent {

	@Value("${filter.exclusion.urls}")
	private String exclusionUrls;

}
