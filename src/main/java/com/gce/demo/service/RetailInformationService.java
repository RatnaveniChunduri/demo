package com.gce.demo.service;

import java.util.List;

import com.gce.demo.bo.CategoryInfo;
import com.gce.demo.to.LocationTo;
import com.gce.demo.to.RetailInformationTo;
public interface RetailInformationService {
	
	/**
	 * To create new shop
	 * @param retailInfo
	 * @return CategoryInfo
	 */
	public CategoryInfo createNewShop(RetailInformationTo retailInfo);

	 /**
     * To get all shops information
     * @return list of CategoryInfo
     */
	public List<CategoryInfo> getAllShopsInfo();
	
	

	/**
	 * To get getNearByLocations based on latitute and longitude
	 * @param lat
	 * @param lng
	 * @return list of LocationTo
	 */
	public List<LocationTo> getNearByLocations(Double lat, Double lng);

}
