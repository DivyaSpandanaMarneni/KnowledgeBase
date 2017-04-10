/**
 * Created by Divya on 4/3/2017.
 */

var myApp = angular.module('myApp',[]);

myApp.controller('UploadController',['$scope','$http',function($scope,$http){
    
    $scope.uploadFile = function () {
        $scope.test="Spandana";
        $scope.file = document.getElementById("file").files[0];
        //$scope.filename =

        console.log("file content");
        console.log($scope.file);
        alert("Inside loadfile js");


        
        $http({
           method : 'POST',
            url : 'http://localhost:8080/fileUpload',
            headers: {'Content-Type' : 'application/json'},
            data: {
                'file' : $scope.file
            }
        }).success(function (data,status,headers,config) {
            alert("Updated successfully in the uploads");
        }).error(function (data,status,headers,config) {
            alert("Error while updating")
        });
    };
    

    // $scope.uploadFile = function () {
    //   var file = $scope.myFile;
    //     console.log("File is ",+file);
    //     var uplodUrl = "/uploadURL";
    //     fileUpload.uploadFileToUrl(file, uplodUrl);
    //
    // };
}]);

