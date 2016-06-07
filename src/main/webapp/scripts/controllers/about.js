'use strict';

/**
 * @ngdoc function
 * @name ibmHackathonApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the ibmHackathonApp
 */
angular.module('ibmHackathonApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
    $scope.isPersonalitySearch = false;
    $scope.isSocialTendenciesSearch = false;
    
    $scope.myFunction = function() {
    	$scope.isPersonalitySearch = true;
    	$scope.isSocialTendenciesSearch = true;

        $.ajax({url: "/personInsights", success: function(result){
			var personalityInsights = result.tree.children[0].children[0].children;
			var rowCollection = [];
			personalityInsights.forEach(function(personality) {
				rowCollection.push({
					name: personality.name,
					percentage: personality.percentage,
					percentageDisplay:Math.round(personality.percentage*10000)/100
				});
			});
			rowCollection.sort(function(a, b) {
				return b.percentage-a.percentage;
			});
			$scope.rowCollection = rowCollection;
			$scope.isPersonalitySearch = false;
			$scope.$apply();
    	}});

    	$.ajax({url: "/toneAnalysis", success: function(result){
			console.log(result);
			window.result = result;
			var tones = result.documentTone.tones[2].tones;
			var rowCollection = [];
			tones.forEach(function(tone) {
				rowCollection.push({
					name: tone.name,
					score: tone.score,
					scoreDisplay:Math.round(tone.score*10000)/100
				});
			});
			rowCollection.sort(function(a, b) {
				return b.score-a.score;
			});
			$scope.rowCollectionSocialTendencies = rowCollection;
			$scope.isSocialTendenciesSearch = false;
			$scope.$apply();
    	}});

    };
  });
