package com.itech.sensitivewordsservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itech.sensitivewordsservice.internal.dto.WordRequest;
import com.itech.sensitivewordsservice.internal.dto.WordResponse;
import com.itech.sensitivewordsservice.internal.entity.SensitiveWords;
import com.itech.sensitivewordsservice.internal.repository.SensitiveWordsRepository;
import io.swagger.v3.oas.annotations.info.License;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@TestPropertySource(locations = "classpath:application-test.properties")
@Slf4j

class SensitiveWordsServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	SensitiveWordsRepository sensitiveWordsRepository;
	@Autowired
	private ObjectMapper objectMapper;

	SensitiveWords sensitiveWords = new SensitiveWords();
	String word;
	@Test
	void createWord() throws Exception {

	WordRequest wordRequest = getWordRequest();
	String productRequestString = objectMapper.writeValueAsString(wordRequest);

	mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v2/sensitiveWords/word")
					.contentType(MediaType.APPLICATION_JSON)
					.content(productRequestString))
			.andExpect(status().isOk());

		Assertions.assertEquals(3, sensitiveWordsRepository.findAll().size());
	}

	@Test
	void searchWord() throws Exception {

		word = "application";

		sensitiveWords.setWord(word.toUpperCase());
		sensitiveWordsRepository.save(sensitiveWords);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v2/sensitiveWords/word/search?word=application")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		String content = result.getResponse().getContentAsString();
		List<WordResponse> wordResponses = objectMapper.readValue(content, new TypeReference<>() {});

		Assertions.assertEquals(1, wordResponses.size());
		WordResponse wordResponse = wordResponses.get(0);

		Assertions.assertEquals(sensitiveWords.getId(), wordResponse.getId());
		Assertions.assertEquals(sensitiveWords.getWord(), wordResponse.getWord());

	}

	@Test
	void updateWord() throws Exception {
		word = "Test";
		sensitiveWords.setWord(word.toUpperCase());
		sensitiveWordsRepository.save(sensitiveWords);


		String updatedWord = "Testing";
		String newWord = updatedWord.toUpperCase();
		String jsonRequest = "{\"word\": \"" + newWord + "\"}";

		mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/api/v2/sensitiveWords/word/{id}",
				sensitiveWords.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(sensitiveWords.getId()))
				.andExpect(jsonPath("$.word").value(newWord));
	}

	private WordRequest getWordRequest()
	{
		word = "application";

		return WordRequest.builder()
				.word(word.toUpperCase())
				.build();
	}

}
