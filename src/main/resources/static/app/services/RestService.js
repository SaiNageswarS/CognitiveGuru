angular.module('bookme')
.factory('RestService', function($resource) {
    return {
        Task: $resource("/task/:userUUID")
    };
});