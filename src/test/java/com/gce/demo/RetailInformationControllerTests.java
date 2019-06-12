package com.gce.demo;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.gce.demo.bo.CategoryInfo;
import com.gce.demo.controller.RetailInformationController;
import com.gce.demo.service.RetailInformationService;
import com.gce.demo.to.LocationTo;
import com.gce.demo.to.RetailInformationTo;


public class RetailInformationControllerTests {
	@InjectMocks
	private RetailInformationController retailInformationController;
	
	@Mock
    private  RetailInformationService retailInformationService;
	
	@Before
	public void init() {
		retailInformationController = new RetailInformationController();
		MockitoAnnotations.initMocks(this);

      }
	@Test
	public void testCreateNewShop() {
		CategoryInfo categoryInfo = new CategoryInfo();
		categoryInfo.setShopName("test");
		categoryInfo.setLatitude(100.000D);
		categoryInfo.setLongitude(100.000D);
		categoryInfo.setCategory("MALLS");
		categoryInfo.setOwnerName("TestOwner");
		Mockito.when(retailInformationService.createNewShop(Mockito.any(RetailInformationTo.class))).thenReturn(categoryInfo);
		
		RetailInformationTo retailInformationTo = new RetailInformationTo();
		retailInformationTo.setShopName("test");	
		retailInformationTo.setLatitude(100.000D);
		retailInformationTo.setLongitude(100.000D);
		retailInformationTo.setCategory("MALLS");
		retailInformationTo.setOwnerName("TestOwner");
		CategoryInfo categoryInfoObj = retailInformationController.createNewShop(retailInformationTo);
		Assert.assertNotNull(categoryInfoObj);
		Assert.assertNotNull(categoryInfoObj.getShopName());
	}
	@Test
	public void testGetAllShopsInfo() {
		CategoryInfo categoryInfo = new CategoryInfo();
		categoryInfo.setShopName("test");
		categoryInfo.setLatitude(100.000D);
		categoryInfo.setLongitude(100.000D);
		categoryInfo.setCategory("MALLS");
		categoryInfo.setOwnerName("TestOwner");
		List<CategoryInfo> categoryInfoList = new ArrayList<CategoryInfo>();
		categoryInfoList.add(categoryInfo);
		Mockito.when(retailInformationService.getAllShopsInfo()).thenReturn(categoryInfoList);
		
		List<CategoryInfo> categoryInfoObjList = retailInformationController.getAllShopsInfo();
		Assert.assertNotNull(categoryInfoObjList);
		Assert.assertNotNull(categoryInfoObjList.size() > 0);
		Assert.assertTrue(categoryInfoObjList.size()==1);
	}
	
	@Test
	public void testGetNearByLocations() {
		LocationTo locationTo = new LocationTo();
		locationTo.setLocation("120 George St, The Rocks NSW 2000, Australia");
		locationTo.setLatitude(100.000D);
		locationTo.setLongitude(100.000D);
		List<LocationTo> locationInfoList = new ArrayList<LocationTo>();
		locationInfoList.add(locationTo);
		Mockito.when(retailInformationService.getNearByLocations(Mockito.anyDouble(),Mockito.anyDouble())).thenReturn(locationInfoList);
		
		List<LocationTo> locationList = retailInformationController.getNearByLocations(12.234d, 32.3445d);
		Assert.assertNotNull(locationList);
		Assert.assertNotNull(locationList.size() > 0);
		Assert.assertTrue(locationList.size()==1);
	}
}
