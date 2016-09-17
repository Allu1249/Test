package com.sample.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = "com.sample")
public class MainConfig {

	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(getMessageConverters());
		return restTemplate;
	}

	@Bean
	public static MappingJacksonHttpMessageConverter getMappingJacksonHttpMessageConverter() {
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();

		MediaType jsonMediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
		MediaType htmlMediaType = new MediaType("text", "html", Charset.forName("UTF-8"));

		supportedMediaTypes.add(jsonMediaType);
		supportedMediaTypes.add(htmlMediaType);

		MappingJacksonHttpMessageConverter jacksonConverter = new MappingJacksonHttpMessageConverter();
		jacksonConverter.setSupportedMediaTypes(supportedMediaTypes);
		return jacksonConverter;

	}

	@Bean
	public List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(getMappingJacksonHttpMessageConverter());
		return messageConverters;
	}

}
