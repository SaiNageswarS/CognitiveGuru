'use strict';

angular.module('bookme')

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/tasks/:uuid', {
    templateUrl: 'app/tasks/tasks.html',
    controller: 'tasksCtrl'
  });
}])

.controller('tasksCtrl', function($scope, $routeParams, RestService) {
    $scope.userId = $routeParams.uuid;
    $scope.tasks = RestService.Task.query({userUUID: $scope.userId})
});