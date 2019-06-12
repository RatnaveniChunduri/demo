var RetailService = angular.module('RetailService', []);
RetailService.service('Service', function ($http) {
    this.getRetailInfo = function () {
    	return $http.get('/retail/getAllShopsInfo')
		.then(function(result) {
			if (result.status === 200) {
				return result.data;					
			}
			
		}, function(error){
			if(error.status === 404 ){
			
			}else{
				
			}
		});
    },
    this.getAdressList = function (ltn,lng) {
    	return $http.get('/retail/getNearByLocations/'+ltn+'/'+lng)
		.then(function(result) {
			if (result.status === 200) {
				return result.data;					
			}
			
		}, function(error){
			if(error.status === 404 ){
			
			}else{
				
			}
		});
    },
    
    this.createRetailInfo = function (retailInfo){
    	return $http.post('/retail/create',retailInfo)
		.then(function(result) {
			if (result.status === 200) {
				return result.data;					
			}
			
		}, function(error){
			if(error.status === 404 ){
			
			}else{
				
			}
		});
    };

});

