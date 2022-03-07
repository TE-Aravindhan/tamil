package com.te.storesmanagementsystem.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.te.storesmanagementsystem.beans.StoreInfo;

public interface StoreService {
	
	public List<StoreInfo> getAllData(MultipartFile file, String option);
	
	public StoreInfo getDataById(MultipartFile file,String storeId);

}
