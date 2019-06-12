package com.gce.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gce.demo.bo.CategoryInfo;
import com.gce.demo.repository.CategoryRepository;
import com.gce.demo.service.RetailInformationService;
import com.gce.demo.to.LocationTo;
import com.gce.demo.to.CategoryTypeEnum;
import com.gce.demo.to.RetailInformationTo;
import com.google.maps.GeoApiContext;
import com.google.maps.NearbySearchRequest;
import com.google.maps.PlacesApi;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

@Service
public class RetailInformationServiceImpl  implements RetailInformationService{

	@Autowired
	CategoryRepository categoryRepository;
	
	/**
	 * To create new shop
	 * @param retailInfo
	 * @return CategoryInfo
	 */
	@Override
	public CategoryInfo createNewShop(RetailInformationTo retailInfo) {
		CategoryInfo category = new CategoryInfo();
		category.setOwnerName(retailInfo.getOwnerName());
		category.setShopName(retailInfo.getShopName());
		if (retailInfo.getCategory() != null){
			
			if(retailInfo.getCategory().equalsIgnoreCase(CategoryTypeEnum.GENERAL_STORE.getName())){
				category.setCategory(CategoryTypeEnum.GENERAL_STORE.getValue());
			} else if(retailInfo.getCategory().equalsIgnoreCase(CategoryTypeEnum.MEDICAL_STORE.getName())){
				category.setCategory(CategoryTypeEnum.MEDICAL_STORE.getValue());
			} else if(retailInfo.getCategory().equalsIgnoreCase(CategoryTypeEnum.SUPER_MARKET.getName())){
				category.setCategory(CategoryTypeEnum.SUPER_MARKET.getValue());
			}	else if(retailInfo.getCategory().equalsIgnoreCase(CategoryTypeEnum.MALL.getName())){
				category.setCategory(CategoryTypeEnum.MALL.getValue());
			}
		}
		category.setLocation(retailInfo.getLocation());
		category.setLatitude(retailInfo.getLatitude());
		category.setLongitude(retailInfo.getLongitude());
		return categoryRepository.save(category);
	}
	
	 /**
     * To get all shops information
     * @return list of CategoryInfo
     */
	@Override
	public List<CategoryInfo> getAllShopsInfo() {
		
		return categoryRepository.findAll();
	}
	

	/**
	 * To get getNearByLocations based on latitute and longitude
	 * @param lat
	 * @param lng
	 * @return list of LocationTo
	 */
	@Override
	public List<LocationTo> getNearByLocations(Double lat, Double lng) {
		GeoApiContext context = new GeoApiContext.Builder().maxRetries(0).disableRetries()
				.apiKey("AIzaSyACjBGevUdbq1sYJfE5boB0YumJncmPtPI")
				.build();
		List<LocationTo> locationList = new ArrayList<LocationTo>();
		NearbySearchRequest req = PlacesApi.nearbySearchQuery(context, new com.google.maps.model.LatLng(lat,lng));
		try {
			PlacesSearchResponse resp = req.radius(20000).awaitIgnoreError();
			if (resp != null && resp.results != null && resp.results.length > 0) {
				for (PlacesSearchResult r : resp.results) {
					PlaceDetails details = PlacesApi.placeDetails(context,r.placeId).awaitIgnoreError();
					LocationTo locationTo = new LocationTo();
					locationTo.setLocation(details.formattedAddress);
					locationTo.setLatitude(details.geometry.location.lat);
					locationTo.setLongitude(details.geometry.location.lng);
					locationList.add(locationTo);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return locationList;
	}

}


