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
    $scope.selectedTaskType = "Incomplete";

    var setSelectedTasktype = function(taskType) {
      $scope.selectedTaskType = taskType;
      $scope.tasks = RestService.Task
                      .query({userUUID: $scope.userId,
                              taskType: $scope.selectedTaskType
                              });
    };
    
    setSelectedTasktype($scope.selectedTaskType);
    $scope.setSelectedTasktype = setSelectedTasktype;

    $scope.taskStatusToggle = function(task) {
        task.finishedOn = (task.finishedOn === null)?new Date():null;
        task.$save();
    };
});