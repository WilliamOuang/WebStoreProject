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
	})
	.otherwise({
		redirectTo: '/'
	});
});