/**
 * http://usejsdoc.org/
 */

myApp.controller('orderController', ['$scope', '$http', '$location', '$routeParams', function($scope, $http, $location, $routeParams){
$scope.orderList1=[];
	$scope.getAllOrder = function(){
		$http.get('/api/orders').success(function(response){
			$scope.orders = response;
		});
                
                
	}
        $scope.getOrderList = function(){
		$http.get('/api/orderList').success(function(response){
			$scope.orderList1 = response;
                });
	}
        
        $scope.myText = "<h1>HTML display</h1>";	

        $scope.searchProduct= function(orderid){
              var str="";
        //alert($scope.orderList1.length);
        for(var i=0;i<$scope.orderList1.length;i++){
          
            if($scope.orderList1[i][0].id==orderid){
                str+=$scope.orderList1[i][1].p.name +"_";
                str+=$scope.orderList1[i][1].orderPrice+"_";
                str+=$scope.orderList1[i][1].quantity;
                str+="    ";
            }
            }
            return str;
	}

	$scope.getOrder = function(){
		var id = $routeParams.id;
		$http.get('/api/orders/'+id).success(function(response){
			$scope.order = response;
		});
	}
        
	
}]);