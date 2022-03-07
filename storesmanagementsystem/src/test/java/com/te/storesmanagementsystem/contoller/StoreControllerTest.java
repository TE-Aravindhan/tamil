package com.te.storesmanagementsystem.contoller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.storesmanagementsystem.beans.StoreInfo;
import com.te.storesmanagementsystem.service.StoreServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class StoreControllerTest {

	@MockBean
	private StoreServiceImpl service;

	private MockMvc mockmvc;

	@Autowired
	private WebApplicationContext context;

	ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	void setUp() throws Exception {
		mockmvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	void getAllDataTest() throws UnsupportedEncodingException, Exception {
		StoreInfo store = new StoreInfo();
		FileInputStream fis = new FileInputStream("src/main/resources/store.csv");
		String option = "1525eec4-7bed-4597-bf5a-e06fcede5f4f";
		MockMultipartFile multipartFile = new MockMultipartFile("file", fis);
		when(service.getDataById(multipartFile, option)).thenReturn(store);

		String contentAsString = mockmvc
				.perform(get("/store/getalldata/").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(mapper.writeValueAsString(store))
						.param("storeId", "1525eec4-7bed-4597-bf5a-e06fcede5f4f"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals(store, mapper.readValue(contentAsString, StoreInfo.class));
	}

	@Test
	void getStoresByCityTest() throws UnsupportedEncodingException, Exception {
		StoreInfo store = new StoreInfo();
		List<StoreInfo> list = new ArrayList<>();

		FileInputStream fis = new FileInputStream("src/main/resources/store.csv");
		MockMultipartFile multipartFile = new MockMultipartFile("file", fis);

		when(service.getAllData(multipartFile, "city")).thenReturn(list);

		String contentAsString = mockmvc
				.perform(get("/store/getalldata/city").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(mapper.writeValueAsString(store)).param("option", "city"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals(mapper.writeValueAsString(list), contentAsString);
	}
}
