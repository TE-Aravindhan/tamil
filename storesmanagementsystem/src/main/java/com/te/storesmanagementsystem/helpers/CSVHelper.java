package com.te.storesmanagementsystem.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.te.storesmanagementsystem.beans.StoreInfo;

public class CSVHelper {

	public static List<StoreInfo> readDataFromCSVFile(MultipartFile file) {

		List<StoreInfo> store = new ArrayList<>();

		try {
			Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

			CsvToBean<StoreInfo> csvToBean = new CsvToBeanBuilder<StoreInfo>(reader).withType(StoreInfo.class)
					.withIgnoreLeadingWhiteSpace(true).build();

			store = csvToBean.parse();

		} catch (IOException e) {

			e.getMessage();

		}
		return store;
	}

}
