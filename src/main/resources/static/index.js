angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/happy';
    $scope.authorized = false;
    $scope.isLoginButtonToggled = false;
    $scope.isCartButtonToggled = false;
    $scope.isFilterButtonToggled = false;

    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: contextPath + '/api/v1/products',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                p: pageIndex
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;

            let minPageIndex = pageIndex - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }

            let maxPageIndex = pageIndex + 2;
            if (maxPageIndex > $scope.ProductsPage.totalPages) {
                maxPageIndex = $scope.ProductsPage.totalPages;
            }

            $scope.PaginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);
        });
    };

    $scope.showCart = function () {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.Cart = response.data;
        });
    };

    $scope.clearCart = function () {
        $http({
            url: contextPath + '/api/v1/cart/clear',
            method: 'GET'
        }).then(function (response) {
            $scope.Cart = response.data;
        });
    };

    $scope.generatePagesIndexes = function(startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    // $scope.submitCreateNewProduct = function () {
    //     $http.post(contextPath + '/products', $scope.newProduct)
    //         .then(function (response) {
    //             $scope.newProduct = null;
    //             $scope.fillTable();
    //         });
    // };

    $scope.deleteProductById = function (productId) {
        $http.delete(contextPath + '/api/v1/products/' + productId)
            .then(function (response) {
                $scope.fillTable();
            });
    }

    //not sure which http method to use
    $scope.addOrder = function () {
        $http.get(contextPath + '/api/v1/order/')
            .then(function (response) {
                $scope.clearCart();
            });
    }

    $scope.addToCart = function (productId) {
        $http.get(contextPath + '/api/v1/cart/add/' + productId)
            .then(function (response) {
                $scope.showCart();
            });
    }

    $scope.clearCart = function () {
        $http.get(contextPath + '/api/v1/cart/clear')
            .then(function (response) {
                $scope.showCart();
            });
    }

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $scope.user.username = null;
                    $scope.user.password = null;
                    $scope.authorized = true;
                    $scope.isLoginButtonToggled = false;
                    $scope.fillTable();

                }
            }, function errorCallback(response) {
                window.alert("Error");
            });
    };

    /*tasty test*/
    $scope.logoff = function () {
        $http.defaults.headers.common.Authorization = null;
        $scope.ProductsPage = null;
        $scope.authorized = false;
        $scope.isLoginButtonToggled = false;
        $scope.isCartButtonToggled = false;
    };

    $scope.toggleLoginButton = function () {
        $scope.isLoginButtonToggled = !$scope.isLoginButtonToggled;
    }

    $scope.toggleCartButton = function () {
        $scope.isCartButtonToggled = !$scope.isCartButtonToggled;
    }

    $scope.toggleFilterButton = function () {
        $scope.isFilterButtonToggled = !$scope.isFilterButtonToggled;
    }

    // $scope.fillTable();
    // $scope.showCart();
});