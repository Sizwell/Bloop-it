package com.itech.sensitivewordsservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itech.sensitivewordsservice.internal.dto.WordRequest;
import com.itech.sensitivewordsservice.internal.repository.SensitiveWordsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@TestPropertySource(locations = "classpath:application-test.properties")
class SensitiveWordsServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	SensitiveWordsRepository sensitiveWordsRepository;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void shouldCreateWord() throws Exception {

	WordRequest wordRequest = getWordRequest();
	String productRequestString = objectMapper.writeValueAsString(wordRequest);

	mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v2/sensitiveWords/word")
					.contentType(MediaType.APPLICATION_JSON)
					.content(productRequestString))
			.andExpect(status().isOk());

		Assertions.assertEquals(1, sensitiveWordsRepository.findAll().size());
	}

	private WordRequest getWordRequest()
	{
		return WordRequest.builder()
				.word("Slash")
				.build();
	}

}
