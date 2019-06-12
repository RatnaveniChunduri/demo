package com.gce.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.gce.demo.bo.CategoryInfo;
import com.gce.demo.repository.CategoryRepository;
import com.gce.demo.service.impl.RetailInformationServiceImpl;
import com.gce.demo.to.RetailInformationTo;

public class RetailInformationServiceImplTest {
	@InjectMocks
	private RetailInformationServiceImpl retailInformationService;
	
	@Mock
    private  CategoryRepository categoryRepository;
	
	@Before
	public void init() {
		retailInformationService = new RetailInformationServiceImpl();
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
		Mockito.when(categoryRepository.save(Mockito.any(CategoryInfo.class))).thenReturn(categoryInfo);
		
		RetailInformationTo retailInformationTo = new RetailInformationTo();
		retailInformationTo.setShopName("test");
		retailInformationTo.setLatitude(100.000D);
		retailInformationTo.setLongitude(100.000D);
		retailInformationTo.setCategory("MALLS");
		retailInformationTo.setOwnerName("TestOwner");
		CategoryInfo categoryInfoObj = retailInformationService.createNewShop(retailInformationTo);
		Assert.assertNotNull(categoryInfoObj);
		Assert.assertNotNull(categoryInfoObj.getShopName());
	}
	@Test
	public void testgetAllShopsInfo() {
		CategoryInfo categoryInfo = new CategoryInfo();
		categoryInfo.setShopName("test");
		categoryInfo.setLatitude(100.000D);
		categoryInfo.setLongitude(100.000D);
		categoryInfo.setCategory("MALLS");
		categoryInfo.setOwnerName("TestOwner");
		List<CategoryInfo> categoryInfoList = new ArrayList<CategoryInfo>();
		categoryInfoList.add(categoryInfo);
		Mockito.when(categoryRepository.findAll()).thenReturn(categoryInfoList);
		
		List<CategoryInfo> categoryInfoObjList = retailInformationService.getAllShopsInfo();
		Assert.assertNotNull(categoryInfoObjList);
		Assert.assertNotNull(categoryInfoObjList.size() > 0);
		Assert.assertTrue(categoryInfoObjList.size()==1);
	}
}
