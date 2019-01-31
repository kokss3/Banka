(function () {
    'use strict';
    angular.module('app').controller('AccountsController', AccountsController);

    AccountsController.$inject = ['$http'];

    function AccountsController($http) {
        var vm = this;

        vm.accounts = [];
        vm.getAll = getAll;
        vm.getLess = getLess;
        vm.deleteAcc = deleteAcc;

        init();

        function init(){
           getAll();
        }

        function getAll(){
            var url = '/all';
            var accountsPromise = $http.get(url);
            accountsPromise.then(function(response) {
                vm.accounts = response.data;
            });
        }

        function getLess(){
            var url = '/less/' + 2000;
            var accountsPromise = $http.get(url);
            accountsPromise.then(function(response) {
                vm.accounts = response.data;
            });
        }

        function deleteAcc(id){
            var url = '/remove/' + id;
            console.log(id);
            $http.post(url).then(function (response) {
               vm.accounts =response.data;
            });
        }
    }

})();