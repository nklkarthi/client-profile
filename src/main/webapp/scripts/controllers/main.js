'use strict';

/**
 * @ngdoc function
 * @name ibmHackathonApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the ibmHackathonApp
 */
angular.module('ibmHackathonApp')
  .controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
    $scope.isPersonalitySearch = false;
    $scope.isSocialTendenciesSearch = false;
	$scope.isSpeechToText = false;
    $scope.myFunction = function() {
    	$scope.isPersonalitySearch = true;
    	$scope.isSocialTendenciesSearch = true;
    	$scope.isSpeechToText = true;

        $.ajax({url: "/twitterPersonInsights", success: function(result){
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

    	$.ajax({url: "/twitterToneAnalysis", success: function(result){
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

    	$.ajax({url: "http://localhost:8080/speechToText", success: function(result){
			$scope.speechText = result.results[0].alternatives[0].transcript;
	    	$scope.isSpeechToText = false;
			$scope.$apply();
    	}});

    };
  });
