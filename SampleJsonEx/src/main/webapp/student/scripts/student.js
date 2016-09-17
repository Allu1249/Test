'use strict';
var app = angular.module('myStudents', []);
app.controller('studentCtrl', function($scope, $http) {
	$http.get("http://localhost:8080/controller/student").then(
			function(response) {
				$scope.students = response.data;
			})
});

/*'use strict';
angular
        .module('myStudentsDetails')
        .config(['$stateProvider',
            '$urlRouterProvider',
            function ($stateProvider, $urlRouterProvider) {
                $urlRouterProvider.otherwise('/');
                $stateProvider
                        .state('home', {
                            url: '/',
                            templateUrl: 'http://localhost:8080/controller/student/view/studentDetails.html',
                        });
            }
        ]);*/