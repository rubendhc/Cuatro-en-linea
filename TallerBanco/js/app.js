(function ( ) {
	var app = angular.module('banco',[]);

	app.controller("BancoController",['$http',function($http){
		var banco = this;
		banco.movimientos = [ ];

		$http.get('http://www.titandesarrollo.com/_api/movimientos').success(function(data){
	//	$http.get('http://localhost/movimiento.json').success(function(data){
			banco.movimientos = data;
            console.log(data);
		});

	}]);

	app.controller('MovimientoController',['$http' ,function($http){
		this.movimiento = {};

		this.addMovimiento = function(){
			$http.post('http://www.titandesarrollo.com/_api/movimientos', {param: 'movimiento'} );
		}
	}]);

})();
