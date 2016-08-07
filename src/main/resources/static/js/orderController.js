/**
 * http://usejsdoc.org/
 */

myApp.controller('orderController', ['$scope', '$http', '$location', '$routeParams', function($scope, $http, $location, $routeParams, $sce){
        $scope.orderList1=[];
        $scope.renderHtml = function (htmlCode) {
            return $sce.trustAsHtml(htmlCode);
        };
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
        

        $scope.searchProduct= function(orderid){
            
            var str="<table class='table'><thead><tr><th>Name</th><th>Order Price </th><th> Quantity </th></tr></thead><tbody>";
 
              
        //alert($scope.orderList1.length);
        for(var i=0;i<$scope.orderList1.length;i++){
          
            if($scope.orderList1[i][0].id==orderid){
                str+="<tr class='success'> <td>"+$scope.orderList1[i][1].p.name +"</td> ";
                str+="<td>"+$scope.orderList1[i][1].orderPrice +"</td> ";
                str+="<td>"+$scope.orderList1[i][1].quantity +"</td></tr> ";
                str+="";
            }
            }
               str+="</tbody> </table>   ";
            return str;
	}

	$scope.getOrder = function(){
		var id = $routeParams.id;
		$http.get('/api/orders/'+id).success(function(response){
			$scope.order = response;
		});
	}
	
}]);

