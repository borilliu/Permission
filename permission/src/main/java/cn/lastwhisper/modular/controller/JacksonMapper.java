package cn.lastwhisper.modular.controller;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component("jacksonMapper")  
public class JacksonMapper extends ObjectMapper {
	private static final long serialVersionUID = 1L;

	public JacksonMapper() {
		this.setSerializationInclusion(Include.NON_NULL);
		this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	}
}
