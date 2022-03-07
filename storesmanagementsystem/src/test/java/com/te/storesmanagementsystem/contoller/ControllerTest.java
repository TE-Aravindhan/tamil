package com.te.storesmanagementsystem.contoller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.storesmanagementsystem.beans.StoreInfo;
import com.te.storesmanagementsystem.controller.StoreController;
import com.te.storesmanagementsystem.customexception.StoreIdNotFoundException;
import com.te.storesmanagementsystem.service.StoreServiceImpl;

@WebMvcTest(StoreController.class)
public class ControllerTest {

	@MockBean
	private StoreServiceImpl service;

	@Autowired
	private MockMvc mockmvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void getByIdTest() throws Exception {

		StoreInfo info = new StoreInfo();
		info.setAddress("Unit C Merrywalks Shopping Centre");
		info.setCity("Stroud");
		info.setNumberOfDays(9971l);
		info.setOpenedDate(LocalDate.parse("1994-10-31"));
		info.setPostCode("GL5 1RR");
		info.setStoreId("1525eec4-7bed-4597-bf5a-e06fcede5f4f");

		FileInputStream fis = new FileInputStream("src/main/resources/store.csv");

		String option = "1525eec4-7bed-4597-bf5a-e06fcede5f4f";
		MockMultipartFile multipartFile = new MockMultipartFile("file", fis);

		Mockito.when(service.getDataById(multipartFile, option)).thenReturn(info);
		System.out.println(info);

		String uri = "/store/getdata/";

		MvcResult mvcResult = mockmvc.perform(get(uri).param("storeId", option)).andExpect(status().isOk()).andReturn();
		String actualResponse = mvcResult.getResponse().getContentAsString();
		System.out.println(actualResponse);

		String expectedResponse = objectMapper.writeValueAsString(info);

		assertThat(actualResponse).isEqualToIgnoringWhitespace(expectedResponse);
	}

}
