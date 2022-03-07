package com.te.storesmanagementsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import com.te.storesmanagementsystem.beans.StoreInfo;
import com.te.storesmanagementsystem.customexception.StoreIdNotFoundException;

@ExtendWith(MockitoExtension.class)
public class StoreServiceImplTest {

	@InjectMocks
	StoreServiceImpl service;

	@Test
	void fetchingDataByIdTest() throws IOException {

		FileInputStream store = new FileInputStream("src/main/resources/store.csv");

		MockMultipartFile multipartFile = new MockMultipartFile("file", store);
		assertEquals("Aberdeen",
				service.getDataById(multipartFile, "1525eec4-7bed-4597-bf5a-e06fcede5f4f").getCity());

	}

	@Test
	void toCheckWithInvalidId() throws IOException {

		FileInputStream store = new FileInputStream("src/main/resources/store.csv");

		MockMultipartFile multipartFile = new MockMultipartFile("file", store);

		Exception exception = assertThrows(StoreIdNotFoundException.class, () -> {
			service.getDataById(multipartFile, "1525eec4-7bed-4597-bf5a-e06fcede5f").getStoreId();

		});

		String expectedMessage = "Please Enter the Valid Store Id";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}
	
	@Test
	void getDataOrderByCityTest() throws IOException {

		FileInputStream store = new FileInputStream("src/main/resources/store.csv");

		MockMultipartFile multipartFile = new MockMultipartFile("file", store);
		
		List<StoreInfo> list = service.getAllData(multipartFile, "city");
			
		assertEquals("Stroud", list.get(6).getCity());

	}
	
	@Test
	void getDataOrderByOpenedDateTest() throws IOException {

		FileInputStream store = new FileInputStream("src/main/resources/store.csv");

		MockMultipartFile multipartFile = new MockMultipartFile("file", store);
				
		List<StoreInfo> list = service.getAllData(multipartFile, "opened date");
			
		assertEquals("859aac4f-e34d-4392-aef7-9c0c14e49782", list.get(0).getStoreId());

	}

}
