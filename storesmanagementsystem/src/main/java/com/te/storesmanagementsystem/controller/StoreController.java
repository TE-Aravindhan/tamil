package com.te.storesmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.te.storesmanagementsystem.beans.Response;
import com.te.storesmanagementsystem.service.StoreService;

@RestController
@RequestMapping("/store")
public class StoreController {

	@Autowired
	StoreService service;

	/*
	 * This method is used to get all the data from provided CSV file
	 * 
	 * @param It accept CSV file in the form of Multipartfile and option as String
	 * in option it takes either city or opened date to sort the records accordingly
	 * 
	 * @return It returns list of all the store info in the form of JSON which it
	 * gets from service method as response with status code
	 * 
	 * @since 1.0
	 */

	@GetMapping("/getalldata/")
	public ResponseEntity<Response> getAllData(@RequestParam("file") MultipartFile file,
			@RequestParam("option") String option) {
		Response response = new Response(false, service.getAllData(file, option));
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	} // end of the getAllData method

	/*
	 * This method is used to get the particular store information from provided CSV
	 * file
	 * 
	 * @param It accept CSV file in the form of Multipartfile and store id as String
	 * based on that store id it fetch the particular store info
	 * 
	 * @return It returns the store information in the form of JSON based on the
	 * given store id which it gets from service method as response with status code
	 * 
	 * @since 1.0
	 */

	@GetMapping("/getdata/")
	public ResponseEntity<Response> getDataById(@RequestParam("file") MultipartFile file,
			@RequestParam String storeId) {
		Response response = new Response(false, service.getDataById(file, storeId));
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	} // end of getDataById method

}
