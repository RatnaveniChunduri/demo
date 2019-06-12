package com.gce.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gce.demo.bo.CategoryInfo;
import com.gce.demo.service.RetailInformationService;
import com.gce.demo.to.LocationTo;
import com.gce.demo.to.RetailInformationTo;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/retail")
public class RetailInformationController {
	@Autowired
	private RetailInformationService retailInformationService;
 
	/**
	 * To create new shop
	 * @param retailInfo
	 * @return CategoryInfo
	 */
	@RequestMapping(method = RequestMethod.POST, 
			value ="/create", 
			produces ="application/json")
	public CategoryInfo createNewShop(@RequestBody RetailInformationTo retailInfo) {
		if(retailInfo == null) {
			throw new IllegalArgumentException("createNewShop() cannot complete, retailInfo is not provided as parameter.");
		}
		return retailInformationService.createNewShop(retailInfo);
	}
	
    /**
     * To get all shops information
     * @return list of CategoryInfo
     */
	@RequestMapping(method = RequestMethod.GET, 
			value ="/getAllShopsInfo", 
			produces ="application/json")
	public List<CategoryInfo> getAllShopsInfo() {
		return retailInformationService.getAllShopsInfo();
	}

	/**
	 * To get getNearByLocations based on latitute and longitude
	 * @param lat
	 * @param lng
	 * @return list of LocationTo
	 */

	@RequestMapping(method = RequestMethod.GET, 
			value ="/getNearByLocations/{latitude}/{longitude}", 
			produces ="application/json")
	public List<LocationTo> getNearByLocations(@PathVariable ("latitude") Double latitude, @PathVariable ("longitude") Double longitude) {
		if (latitude == null || longitude == null){
			 throw new IllegalArgumentException("getNearByLocations() cannot complete, latitude/longitude is not provided as parameter.");
		}
		return retailInformationService.getNearByLocations(latitude, longitude);
	}

}
