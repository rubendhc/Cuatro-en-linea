package main;

public class Board {
	
	private int[][] matriz;
	private int[] four_line;//Arreglo donde se almacenan las coordenadas de las fichas que forman 4 en línea
	private int[] stack_flags; //Arreglo donde se almacena las posición disponible en cada columna
	private int win_player=0;

	public Board(int r,int c, int line) {//r = row; c = column
		matriz = new int[r][c];
		four_line = new int[line];
		stack_flags = new int[c];

		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				matriz[i][j]=0;
			}
		}

		for(int i = 0; i < line; i++){
			four_line[i] = 0;
		}

		for (int i = 0; i < c ; i++ ) {
			stack_flags[i]=r-1;
		}
		
		
	}

	/**
	 * @return void
	 */
	public void set_matriz() {
		// TODO Auto-generated method stub
	}

	/**
	 * @param heigth
	 * @return void
	 */
	public void set_heigth(int heigth) {
		// TODO Auto-generated method stub
	}

	/**
	 * @param width
	 * @return void
	 */
	public void set_width(int width) {
		// TODO Auto-generated method stub
	}

	/**
	 * @return int[][]
	 */
	/*
	public int[][] get_matriz() {
		// TODO Auto-generated method stub
		
	}
	 */
	
	public void set_win_player(int win_player, boolean answer) {
		// TODO Auto-generated method stub
		if(answer){
			this.win_player = win_player;
		}
		
	}
	
	/**
	 * @return int
	 */
	public int get_heigth() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return int
	 */
	public int get_width() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @param row
	 * @param chip
	 * @return void
	 */
	public void insert_chip(int c, int player) {//py_chip número que identifica al jugador
		System.out.print(c);
		if(stack_flags[c]>=0){
			matriz[stack_flags[c]][c] = player;
			stack_flags[c] = stack_flags[c]-1; 
		}

	}


	public int get_row_free_pos(int c){
		return stack_flags[c];
	}
	
	public int get_win_player() {
		// TODO Auto-generated method stub
		return win_player;
	}
	

	/**
	 * @return int
	 */
	public int count_chips() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return boolean
	 */
	public boolean detect_four_in_a_row(int r, int c, int player) {
		// TODO Auto-generated method stub
		boolean answer=false;
		
		if(v_northeast(r, c, player)){
			answer = v_northeast(r, c, player);
		}else if(v_east(r, c, player)){
			answer = v_east(r, c, player);
		}else if(v_southeast(r, c, player)){
			answer = v_southeast(r, c, player);
		}else if(v_south(r, c, player)){
			answer = v_south(r, c, player);
		}else if(v_southwest(r, c, player)){
			answer = v_southwest(r, c, player);
		}else if(v_west(r, c, player)){
			answer = v_west(r, c, player);
		}else if(v_northwest(r, c, player)){
			answer = v_northwest(r, c, player);
		}else if(v_north(r, c, player)){
			answer = v_north(r, c, player);
		}
		
		
		set_win_player(player, answer);
		
		return answer;
	}

	public int get_value_i_j(int c, int r){
		return matriz[c][r];
	}
	
	
	public void print_matriz(){
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(matriz[i][j]);
			}
			System.out.print("\n");
			
		}
	}
	
	
	public boolean v_east(int r, int c, int player){
		// Nombre v_right (validación derecha)
		// Propósito: validar si existen tres números iguales a partir de la posición ingresada hacia la derecha
		// Datos de entrada: entero r(fila), entero c(column), entero player(Jugador)
		// salida: booleana;
		int count=0; 
		if(c<4){
			for(int i = 0; i < 3; i++){
				if(matriz[r][c+1] == player){
					c+=1;
					++count;
				}
				else {
					return false;
				}		
			}
		}
		return count(count);
	}
	
	public boolean v_southeast(int r, int c, int player){
		// Nombre v_southeast (Validación diagonal inferior derecha)
		// Propósito: Valida diagonal inferior derecha existen números iguales
		// Datos de entrada: entero r(fila), entero c(column), entero player(Jugador)
		// salida: booleana;
		int count=0; 
		if(c<4 && r < 3){
			for(int i = 0; i < 3; i++){
				if(matriz[r+1][c+1] == player){
					c+=1;
					r+=1;
					++count;
				}
				else {
					return false;
				}		
			}
		}
		return count(count);
	}
	
	public boolean v_south(int r, int c, int player){
		// Nombre v_southeast (Validación diagonal inferior derecha)
		// Propósito: Valida diagonal inferior derecha existen números iguales
		// Datos de entrada: entero r(fila), entero c(column), entero player(Jugador)
		// salida: booleana;
		int count=0; 
		if(r<3){
			for(int i = 0; i < 3; i++){
				if(matriz[r+1][c] == player){
					r+=1;
					++count;
				}
				else {
					return false;
				}		
			}
		}
		return count(count);
	}
	
	public boolean v_southwest(int r, int c, int player){
		// Nombre v_southeast (Validación diagonal inferior derecha)
		// Propósito: Valida diagonal inferior derecha existen números iguales
		// Datos de entrada: entero r(fila), entero c(column), entero player(Jugador)
		// salida: booleana;
		int count=0; 
		if(r < 3 && c > 2){
			for(int i = 0; i < 3; i++){
				if(matriz[r+1][c-1] == player){
					c-=1;
					r+=1;
					++count;
				}
				else {
					return false;
				}		
			}
		}
		return count(count);
	}
	
	public boolean v_west(int r, int c, int player){
		// Nombre v_southeast (Validación diagonal inferior derecha)
		// Propósito: Valida diagonal inferior derecha existen números iguales
		// Datos de entrada: entero r(fila), entero c(column), entero player(Jugador)
		// salida: booleana;
		int count=0; 
		if(c>2){
			for(int i = 0; i < 3; i++){
				if(matriz[r][c-1] == player){
					c-=1;
					++count;
				}
				else {
					return false;
				}		
			}
		}
		return count(count);
	}
	
	public boolean v_northwest(int r, int c, int player){
		// Nombre v_southeast (Validación diagonal inferior derecha)
		// Propósito: Valida diagonal inferior derecha existen números iguales
		// Datos de entrada: entero r(fila), entero c(column), entero player(Jugador)
		// salida: booleana;
		int count=0; 
		if(r > 2 && c > 2 ){
			for(int i = 0; i < 3; i++){
				if(matriz[r-1][c-1] == player){
					c-=1;
					r-=1;
					++count;
				}
				else {
					return false;
				}		
			}
		}
		return count(count);
	}
	
	public boolean v_north(int r, int c, int player){
		// Nombre v_southeast (Validación diagonal inferior derecha)
		// Propósito: Valida diagonal inferior derecha existen números iguales
		// Datos de entrada: entero r(fila), entero c(column), entero player(Jugador)
		// salida: booleana;
		int count=0; 
		if(r > 2){
			for(int i = 0; i < 3; i++){
				if(matriz[r-1][c] == player){
					r-=1;
					++count;
				}
				else {
					return false;
				}		
			}
		}
		return count(count);
	}
	
	public boolean v_northeast(int r, int c, int player){
		// Nombre v_southeast (Validación diagonal inferior derecha)
		// Propósito: Valida diagonal inferior derecha existen números iguales
		// Datos de entrada: entero r(fila), entero c(column), entero player(Jugador)
		// salida: booleana;
		int count=0; 
		if(c < 4 && r > 2){
			for(int i = 0; i < 3; i++){
				if(matriz[r-1][c+1] == player){
					r-=1;
					c+=1;
					++count;
				}
				else {
					return false;
				}		
			}
		}
		return count(count);
	}
	
	public boolean count(int n){
		// Nombre count
		// Propósito: valida si un entero n es igual a 3
		// Datos de entrada: entero n
		// salida: booleana;
		if(n == 3){
			return true;
		}else {
			return false;
		}
	}
	

}
