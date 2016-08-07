/**
 * http://usejsdoc.org/
 */
var myApp = angular.module('myApp',['ngRoute']);

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
	})
        
	.otherwise({
		redirectTo: '/'
	});
});


myApp.filter('myFormat', function() {
    return function(x) {
        return x;
    };
});
