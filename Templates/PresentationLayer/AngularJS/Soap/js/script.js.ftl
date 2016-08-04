
	
	var customerApp = angular.module('customerApp',['ui.bootstrap']);
	
	/*
	*	Customer Value service.
	*	This holds the value of customer object;
	*/
	customerApp.value("serverIp", "${serverHost}");
	
	/*
	*	Add new customer service call.
	*	This service is used to add the new customer.
	*/
	customerApp.service('addCustomer', function($http,serverIp){
		this.call = function(customer, successCallBack, failureCallBack){
			$http({
				method: 'POST',
				url: serverIp + '${projectName}/BusinessDelegator',
				data: JSON.stringify({
					serviceType: "POJO",
					service : "com.app.customer.stub.CustomerStub",
					serviceMethod: "insert",
					paramType:"com.app.customer.vo.Customer",
					param: JSON.stringify(customer),
				})
			}).then(successCallBack, failureCallBack);
		};
	});
	
	/*
	*	Search Customer service call.
	*	This service is used to Search the customer with given searchData and searchField.
	*/
	customerApp.service('searchCustomers', function($http,serverIp){
		this.call = function(searchData, searchField, successCallBack, failureCallBack){
			$http({
				method: 'POST',
				url: serverIp + '${projectName}/BusinessDelegator',
				data: JSON.stringify({
					serviceType: "POJO",
					service : "com.app.customer.stub.CustomerStub",
					serviceMethod: "search",
					paramType:"String,String",
					param: JSON.stringify({"searchValue" : searchData, "searchColumn" : searchField}),
				})
			}).then(successCallBack, failureCallBack);
		};
	});
	
	/*
	*	Delete Customer service call.
	*	This service is used to delete the customer.
	*/
	customerApp.service('deleteCustomers', function($http,serverIp){
		this.call = function(customers, successCallBack, failureCallBack){
			$http({
				method: 'POST',
				url: serverIp + '${projectName}/BusinessDelegator',
				data: customers,
				data: JSON.stringify({
					serviceType: "POJO",
					service : "com.app.customer.stub.CustomerStub",
					serviceMethod: "deleteAll",
					paramType:"java.util.List<com.app.customer.vo.Customer>",
					param: JSON.stringify(customers),
				})
			}).then(successCallBack, failureCallBack);
		};
	});
	
	/*
	*	Update Customer service call.
	*	This service is used to Update the customer.
	*/
	customerApp.service('updateCustomer', function($http, serverIp){
		this.call = function(customer, successCallBack, failureCallBack){
			$http({
				method: 'POST',
				url: serverIp + '${projectName}/BusinessDelegator',
				data: JSON.stringify({
					serviceType: "POJO",
					service : "com.app.customer.stub.CustomerStub",
					serviceMethod: "update",
					paramType:"com.app.customer.vo.Customer",
					param: JSON.stringify(customer),
				})
			}).then(successCallBack, failureCallBack);
		};
	});
	
	/*
	*	List Customers service call.
	*	This service is used to list all the customers.
	*/
	customerApp.service('listCustomers', function($http, serverIp){
		this.call = function(successCallBack, failureCallBack){
			$http({
				method: 'POST',
				url: serverIp + '${projectName}/BusinessDelegator',
				data: JSON.stringify({
					serviceType: "POJO",
					service : "com.app.customer.stub.CustomerStub",
					serviceMethod: "getAllCustomer",
					paramType:"",
					param: JSON.stringify({}),
				})
			}).then(successCallBack, failureCallBack);
		};
	});

	customerApp.controller('AddListController', ['$scope', 'listCustomers', 'deleteCustomers', 'searchCustomers', function($scope, listCustomers, deleteCustomers, searchCustomers) {
		  $scope.searchtxt = '';
		  $scope.searchField = '--Select the Field--';
		  $scope.availableOptions = ["customerid","name", "email", "phone", "address", "orders", "action","--Select the Field--"];
		  
		   $scope.search = function(){
				console.log("Search Value : " + $scope.searchtxt);
				console.log("Search Field : " + $scope.searchField);
				var searchSuccess = function(response){
					$scope.setCustomers(response.data);
					$scope.figureOutCustomers();
				};
				var searchFailure = function(response){
					alert(response.data);
				};
				searchCustomers.call($scope.searchtxt, $scope.searchField, searchSuccess, searchFailure);
		   }
		    $scope.clear = function(){
				 $scope.searchtxt = '';
				$scope.searchField = '--Select the Field--';
				listCustomers.call(successList, failureList);
		   }
		   
		   
		  $scope.showAddPage = function(){
			console.log("Adding new Customer");
			$scope.setCustomer({});
			$scope.setListPage(false);
			$scope.setShowAddButton(true);
		  }
		  
		  $scope.deleteCustomers = function(){
			console.log("Deleting Customer(s)");
			var CustomersToBeDeleted = [];
			var customers = $scope.getCustomers();
			for(var i=0; i < customers.length; i++)
			{
				if(customers[i].delete){
					delete customers[i]['delete'];
					delete customers[i]['$$hashKey'];
					CustomersToBeDeleted.push(customers[i]);
				}
			}
			console.log(CustomersToBeDeleted);
			var success = function(response){
				alert(response.data);
				listCustomers.call(successList, failureList);
			};
			var failure = function(response){
				alert("Error" + response.data);
			};
			deleteCustomers.call(CustomersToBeDeleted, success, failure);
		  }
		  
		  $scope.edit = function(editCustomer) {
				console.log("Edit Customer : " + editCustomer.customerid );
				$scope.setCustomer(editCustomer);
				$scope.setListPage(false);
				$scope.setShowAddButton(false);
			}
			
			/*
			* Pagination
			*/
		  $scope.itemsPerPage = 10;
		  $scope.currentPage = 1;
		  
		  $scope.figureOutCustomers = function() {
			var begin = (($scope.currentPage - 1) * $scope.itemsPerPage);
			var end = begin + $scope.itemsPerPage;
			$scope.filteredCustomers = $scope.getCustomers().slice(begin, end);
		  };
		   
		  $scope.pageChanged = function() {
			$scope.figureOutCustomers();
		  };
		  
		  /*
		  *Shows list of customers.
		  */
		  var successList = function successCallback(response) {
					$scope.setCustomers(response.data);
					$scope.setListPage(true);
					$scope.figureOutCustomers();
			};
			var failureList = function errorCallback(response) {
					alert("Error : " + response.data);
			};
			listCustomers.call(successList, failureList);
		  
		}]);
		
		
		customerApp.controller('addController', ['$scope', 'addCustomer', 'updateCustomer', function($scope, addCustomer, updateCustomer) {
		
			$scope.list = function(){
				console.log("navigating to List Page");
				$scope.setListPage(true);
			}
			var addSuccess = function successCallback(response) {
					alert("Success : " + response.data);
					$scope.setListPage(true);
					console.log($scope.getCustomer());
					$scope.getCustomers().push($scope.getCustomer());
					
			};
			var addFailure = function errorCallback(response) {
					alert("Error : " + response.data);
			};
			$scope.add = function(){
				console.log("Adding new customer to DB : " + $scope.getCustomer().customerid);
				$scope.getCustomer().createddate = new Date();
				$scope.getCustomer().modifieddate = new Date();
				addCustomer.call($scope.getCustomer(),addSuccess,addFailure);
				
			}
			
			var updateSuccess = function successCallback(response) {
					alert("Success : " + response.data);
					$scope.setCustomer({});
					$scope.setListPage(true);
			};
			var updateFailure = function errorCallback(response) {
					alert("Error : " + response.data);
			};
			
			$scope.update = function(){
				console.log("Updating customer to DB : " + $scope.getCustomer().customerid);
				delete $scope.getCustomer()['delete'];
				delete $scope.getCustomer()['$$hashKey'];
				updateCustomer.call($scope.getCustomer(),updateSuccess,updateFailure);
			}
		
		}]);
		
		customerApp.controller('globalController', ['$scope', function($scope) {
			
			$scope.customers = [];
		   $scope.customer = {};
		   $scope.listPage = true;
		   $scope.showAddButton = true;
		   
		   $scope.setListPage = function(val){
				$scope.listPage = val;
		   }
		    $scope.setShowAddButton = function(val){
				$scope.showAddButton = val;
		   }
		   $scope.setCustomer = function(customer){
				$scope.customer = customer;
		   }
		   $scope.getCustomer = function(){
				return $scope.customer;
		   }
		    $scope.setCustomers = function(customers){
				$scope.customers = customers;
		   }
		   $scope.getCustomers = function(){
				return $scope.customers;
		   }
		   $scope.showListPage = function(){
				$scope.setListPage(true);
		   }
		   
			
		}]);