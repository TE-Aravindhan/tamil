package com.te.storesmanagementsystem.beans;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StoreInfoTest {
	
	String json = "{\"storeId\":\"859aac4f-e34d-4392-aef7-9c0c14e49782\",\"postCode\":\"G81 2TL\",\"city\":\"Clydebank\",\"address\":\"106 Sylvania Way\",\"openedDate\":null,\"numberOfDays\":null}";
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	void jsonConversionTest() throws JsonMappingException, JsonProcessingException {
		
		StoreInfo info = new StoreInfo();
		
		info.setAddress("106 Sylvania Way");
		info.setCity("Clydebank");
		info.setPostCode("563322");
		info.setStoreId("859aac4f-e34d-4392-aef7-9c0c14e49782"); 
		
		StoreInfo readValue = objectMapper.readValue(json, StoreInfo.class);

		assertEquals(json, objectMapper.writeValueAsString(readValue));
			
	}

}
