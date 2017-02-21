(function () {
  'use strict';

  angular
      .module('MyApp',['ngMaterial', 'ngMessages', 'material.svgAssetsCache','dataGrid', 'pagination'])
      .controller('AppCtrl', ['$scope', 'myAppFactory', function ($scope, myAppFactory) {

            $scope.error = false;
            $scope.data = {
                  selectedIndex: 0,
                  secondLocked:  true,
                  secondLabel:   "Item Two",
                  bottom:        false
                };
                $scope.next = function() {
                  $scope.data.selectedIndex = Math.min($scope.data.selectedIndex + 1, 2) ;
                };
                $scope.previous = function() {
                  $scope.data.selectedIndex = Math.max($scope.data.selectedIndex - 1, 0);
                };

          $scope.gridOptions = {
              data: [],
              urlSync: true
          };
          myAppFactory.getData().then(function (responseData) {
                if(!responseData.data.errorCode)
                {
                    $scope.gridOptions.data = responseData.data;
                }
                else
                {
                    $scope.error = true;
                    $scope.message = responseData.data.message;
                }

          });

      }])
      .factory('myAppFactory', function ($http) {
          return {
              getData: function () {
                  return $http({
                      method: 'GET',
                      url: 'http://localhost:8080/hwangdbs/spring/atm/'
                  });
              }
          }
      });
})();


/**
Copyright 2016 Google Inc. All Rights Reserved. 
Use of this source code is governed by an MIT-style license that can be in foundin the LICENSE file at http://material.angularjs.org/license.
**/