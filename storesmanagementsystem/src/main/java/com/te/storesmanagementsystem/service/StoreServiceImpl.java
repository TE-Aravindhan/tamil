package com.te.storesmanagementsystem.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.te.storesmanagementsystem.beans.StoreInfo;
import com.te.storesmanagementsystem.customexception.StoreIdNotFoundException;
import com.te.storesmanagementsystem.helpers.CSVHelper;

@Service
public class StoreServiceImpl implements StoreService {

	@Override
	public List<StoreInfo> getAllData(MultipartFile file, String option) {

		List<StoreInfo> info = CSVHelper.readDataFromCSVFile(file);

		for (StoreInfo storeInfo : info) {

			LocalDate from = storeInfo.getOpenedDate();
			LocalDate to = LocalDate.now();

			storeInfo.setNumberOfDays(ChronoUnit.DAYS.between(from, to));
		}

		List<StoreInfo> result = null;

		if (option.equalsIgnoreCase("city")) {
			result = info.stream().sorted((i, j) -> i.getCity().compareTo(j.getCity())).collect(Collectors.toList());

		} else if (option.equalsIgnoreCase("opened date")) {
			result = info.stream().sorted((i, j) -> i.getOpenedDate().compareTo(j.getOpenedDate()))
					.collect(Collectors.toList());

		}
		System.out.println(result);
		return result;

	}

	@Override
	public StoreInfo getDataById(MultipartFile file, String storeId) {

		List<StoreInfo> info = CSVHelper.readDataFromCSVFile(file);

		for (StoreInfo storeInfo : info) {

			LocalDate from = storeInfo.getOpenedDate();
			LocalDate to = LocalDate.now();

			storeInfo.setNumberOfDays(ChronoUnit.DAYS.between(from, to));
		}

		StoreInfo result = null;

		for (StoreInfo storeInfo : info) {

			if (storeInfo.getStoreId().equals(storeId)) {

				result = storeInfo;
			}
		}

		if (result != null) {
			
			System.out.println(result);

			return result;

		} else {

			throw new StoreIdNotFoundException("Please Enter the Valid Store Id");
		}

	}

}
