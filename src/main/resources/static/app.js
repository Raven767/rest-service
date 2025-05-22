var app = angular.module('storeApp', []);

app.controller('StoreController', function($scope, $http) {
    const apiUrl = 'https://localhost:8443/stores';

    $scope.stores = [];
    $scope.newStore = {};
    $scope.isEdit = false;

    $scope.getStores = function() {
        $http.get(apiUrl)
            .then(function(response) {
                $scope.stores = response.data;
            })
            .catch(function(error) {
                console.error('Ошибка при получении магазинов:', error);
            });
    };

    $scope.saveStore = function() {
        if ($scope.isEdit) {
            $http.put(apiUrl + '/' + $scope.newStore.id, $scope.newStore)
                .then(function(response) {
                    if (response.status === 200) {
                        $scope.getStores();
                        $scope.newStore = {};
                        $scope.isEdit = false;
                    }
                })
                .catch(function(error) {
                    console.error('Ошибка при обновлении магазина:', error);
                });
        } else {
            if ($scope.newStore.name) {
                $http.post(apiUrl, { name: $scope.newStore.name })
                    .then(function(response) {
                        if (response.status === 201) {
                            $scope.getStores();
                            $scope.newStore = {};
                        } else {
                            console.error('Ошибка при добавлении магазина:', response);
                        }
                    })
                    .catch(function(error) {
                        console.error('Ошибка при добавлении магазина:', error);
                    });
            } else {
                alert("Пожалуйста, введите название магазина.");
            }
        }
    };

    $scope.editStore = function(store) {
        $scope.newStore = angular.copy(store);
        $scope.isEdit = true;
    };

    $scope.deleteStore = function(id) {
        if (confirm('Вы уверены, что хотите удалить этот магазин?')) {
            $http.delete(apiUrl + '/' + id)
                .then(function(response) {
                    if (response.status === 204) {
                        $scope.getStores();
                    }
                })
                .catch(function(error) {
                    console.error('Ошибка при удалении магазина:', error);
                });
        }
    };

    $scope.getStores();
});