'use strict';

angular.module('bookme')

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/home', {
    templateUrl: 'app/home/home.html',
    controller: 'homeCtrl'
  });
}])

.controller('homeCtrl', function() {
    
});