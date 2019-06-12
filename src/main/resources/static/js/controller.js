var myApp = angular.module('DemoApp', ['RetailService']);
myApp.controller('RetailInfoController', function ($scope, Service,$timeout) {
	$scope.showSearchLoader  =false;
	$scope.showRetailInfoList = false;
	$scope.showSearchResult  = false;
	$scope.initailMsg = false;
	$scope.init = function(){
		$scope.retailInfoList = [];
		
		Service.getRetailInfo().then(function(retailInfoList) {
			if (retailInfoList != null && retailInfoList.length > 0){
				 $scope.retailInfoList = retailInfoList;
				 $scope.showRetailInfoList = true;;
			} else {
				 $scope.initailMsg = true;
			}
		});
		if( window && window.navigator){
			window.navigator.geolocation.getCurrentPosition(function(position){
				var curLatitude = position.coords.latitude;
				var curLongitude = position.coords.longitude;
				$scope.addressObjList =[];
				Service.getAdressList(curLatitude,curLongitude).then(function(addressList) {
					$scope.addressObjList = addressList;
				});
			});
		}
	};
	$scope.createNewShop = function(){
		$(".modal-body").css({"min-height" : "160px"});
		$scope.showShopNameError = false;
		$scope.showCategoryError = false;
		$scope.showLocationError = false;
		$scope.showOwnerError = false;
		 $scope.shopName="";
		 $scope.category="";
		 $scope.ownerName="";
		 $scope.location="";
		$('#createNewShop').modal({
			show: true,
			backdrop:true
		});
	};
	$scope.noRecordsFound = false;
	$('#categoryDropDown a').click(function(){
		$('#selected').text($(this).text());
		$scope.category = $(this).text();
	});
	$scope.showSelectedAddress = function (value, latitude,longitude){
		$('#selectedLocation').text(value);
		$scope.location = value;
		$scope.latitude=latitude;
		$scope.longitude=longitude;

	};

	$scope.createShop = function(){
		var retailInfo = {};
		var isValidateForm = $scope.validateForm();
		if (isValidateForm){
		retailInfo.shopName = $scope.shopName;
		retailInfo.category = $scope.category;
		retailInfo.ownerName = 	$scope.ownerName;
		retailInfo.latitude =  $scope.latitude;
		retailInfo.longitude = $scope.longitude;
		retailInfo.location = $scope.location;
		var retailInfoObj = JSON.stringify(retailInfo);
		Service.createRetailInfo(retailInfoObj).then(function(retailInfo) {
			$scope.initailMsg = false;
			$scope.retailInfoList.push(retailInfo);
			$scope.noRecordsFound = false;
			 $scope.showRetailInfoList = true;;
			$('#createNewShop').modal('hide');
		});
		}	

	};
	$scope.validateForm = function(){
		var isValidForm = true;
		if (!Boolean($scope.shopName)){
			$scope.showShopNameError = true;
			isValidForm= false;
		}
		if (!Boolean($scope.category)){
			$scope.showCategoryError = true;
			isValidForm= false;
		}
		if (!Boolean($scope.location)){
			$scope.showLocationError = true;
			isValidForm= false;
		}
		if (!Boolean($scope.ownerName)){
			$scope.showOwnerError = true;
			isValidForm= false;
		}
		if (!isValidForm){
			$(".modal-body").css({"min-height" : "240px"});
		}
		return isValidForm;
	};

	$scope.showSearchResult = false;
	$scope.search = function(){
		$scope.showSearchError = false;
		if (!Boolean($scope.searchShopName)){
			$scope.showSearchError = true;
			return;
		}
		$scope.showSearchLoader  =true;
		if (Boolean($scope.retailInfoList) && $scope.retailInfoList.length > 0){
			$scope.searchList=[];
			$scope.retailInfoListCopy = angular.copy($scope.retailInfoList);
			for (var k=0;k<$scope.retailInfoListCopy.length; k++){
				if ($scope.searchShopName == $scope.retailInfoListCopy[k].shopName){
					var curLatitude = $scope.retailInfoListCopy[k].latitude;
					var curLongitude = $scope.retailInfoListCopy[k].longitude;
					$scope.searchList.push($scope.retailInfoListCopy[k]);
					$scope.retailInfoListCopy.splice(k,1);
					Service.getAdressList(curLatitude,curLongitude).then(function(addressList) {
						if(addressList!= null && addressList.length > 0) {
							for(var i=0;i<addressList.length;i++){
								for (var j=0;j<$scope.retailInfoListCopy.length;j++){
									if ($scope.retailInfoListCopy[j].location==addressList[i].location)
									{
										$scope.searchList.push($scope.retailInfoListCopy[j]);
									}
								}
							}
						}
					});
					if (Boolean($scope.searchList) && $scope.searchList.length >0 ){
						$scope.showSearchLoader  =false;
						$scope.showSearchResult = true;
						$scope.$apply();
					}  else {
						$scope.showSearchLoader  =false;	
						$scope.noRecordsFound = true;
					}

				} else {
					$scope.showSearchLoader  =false;	
					$scope.noRecordsFound = true;
					$scope.showRetailInfoList = false;
				}
			}
		} else{
			$scope.showSearchLoader  =false;	
			$scope.noRecordsFound = true;
		}
	};
	$scope.getkeys = function (event) {
		$scope.showSearchError = false;
		if (!Boolean($scope.searchShopName)){
			$scope.showSearchResult = false;
			$scope.noRecordsFound = false;
			if ($scope.retailInfoList != null && $scope.retailInfoList.length > 0){
				$scope.showRetailInfoList = true;;
			} 
		}

	};

});