package com.te.storesmanagementsystem.beans;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@CsvBindByName(column = "Store Id")
	private String storeId;

	@CsvBindByName(column = "Post Code")
	private String postCode;

	@CsvBindByName
	private String city;

	@CsvBindByName
	private String address;

	@CsvBindByName(column = "Opened Date")
	@CsvDate(value = "dd/MM/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate openedDate;
	
	private Long numberOfDays;

}
