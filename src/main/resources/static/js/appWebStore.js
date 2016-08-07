/**
 * http://usejsdoc.org/
 */
var myApp = angular.module('myApp',['ngRoute','ngSanitize']);

myApp.config(function($routeProvider){
	$routeProvider
                .when('/', {
		controller:'productController',
		templateUrl: 'views/products.html'
	}).when('/addproduct', {
		controller:'productController',
		templateUrl: 'views/product.html'
	}).when('/editproduct/:id', {
		controller:'productController',
		templateUrl: 'views/edit_product.html'
	}).when('/listProduct', {
		controller:'productController',
		templateUrl: 'views/listProduct.html'
	}).when('/listOrder', {
		controller:'orderController',
		templateUrl: 'views/listOrder.html'
	}).when('/confirmOrder', {
		controller:'productController',
		templateUrl: 'views/confirmOrder.html'
	})
        
	.otherwise({
		redirectTo: '/'
	});
});

myApp.filter("sanitize", ['$sce', function($sce) {
  return function(htmlCode){
    return $sce.trustAsHtml(htmlCode);
  }
}]);
myApp.filter('unsafe', function($sce) { return $sce.trustAsHtml; });
myApp.filter('myFormat', function() {
    return function(x) {
        return x;
    };
});
