package main;

import java.applet.Applet;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;



public class Game extends Applet implements Runnable, MouseListener {

	// :::::::::::::::::::::::::Atributes::::::::::::::::::::::::::::::::::
	private static int screen_width = 700;
	private static int screen_height = 600;
	private Graphics2D g2d;
	private Board main_board; // representación del tablero de juego
	private Chip chips_array[];
	private int chip_counter = 0;
	private Thread gameloop;
	private BufferedImage backbuffer;
	private AffineTransform identity = new AffineTransform();
	private int turn;
	private int player1 = 1;
	private int player2 = 2;

	// :::::::::::::::::::::::::Methods::::::::::::::::::::::::::::::::::
	public void init() {
		backbuffer = new BufferedImage(screen_width, screen_height,
		BufferedImage.TYPE_INT_RGB);
		g2d = backbuffer.createGraphics();
		
		

		// Inicialización del tablero
		main_board = new Board(6, 7, 4);
		

				// Inicilización de las fichas
		chips_array = new Chip[42];

		
		
		setSize(800, 700);
		turn = player1;
				
				
		addMouseListener(this);
		

	}

	// @overwrite
	public void paint(Graphics g) {
		// Nombre draw_lines
		// Propósito: Dibujar toda la pantalla del juego
		// Datos de entrada Objeto de tipo Graphics g2d
		// salida: vacio
		// Tamaño pantalla 700 x 600 (100 por cada fila y columna)
		super.paint(g);

		g.drawImage(backbuffer, 0, 0, this);

		g2d.setTransform(identity);

		g2d.setPaint(Color.gray);
		g2d.fillRect(0, 0, screen_width, screen_height);

		draw_lines();
		draw_chips();
		
		g2d.drawString("Game Over", 300, 200);

	}

	public void draw_lines() {
		// Nombre draw_lines
		// Propósito: Dibujar las lineas del tablero
		// Datos de entrada Objeto de tipo Graphics g2d
		// salida: vacio
		// Tamaño pantalla 700 x 600 (100 por cada fila y columna)
		for (int i = 0; i < screen_height + 100; i += 100) {
			g2d.setTransform(identity);
			g2d.setColor(Color.WHITE);
			g2d.drawLine(0, i, screen_width, i);
			
		}

		for (int i = 0; i < screen_width + 100; i += 100) {
			g2d.setTransform(identity);
			g2d.setColor(Color.WHITE);
			g2d.drawLine(i, 0, i, screen_height);
		}
	}

	public void draw_chips() {
		// Nombre draw_chips
		// Propósito: Dibujar las fichas puestas en el tablero
		// Datos de entrada: Arreglo de coordenadas de los puntos
		// salida: vacio
		if (chip_counter > 0) {
			for (int i = 0; i < chip_counter; i++) {
				g2d.setTransform(identity);
				// g2d.translate(array_points[i].x, array_points[i].y);
				g2d.setColor(chips_array[i].get_color());
				g2d.fillOval(chips_array[i].get_coor_x()*100, chips_array[i].get_coor_y()*100, 100, 100);
			}
		}

	}

	public Point fit_point(Point p) {
		// Nombre fit_point
		// Propósito: Ajustar la coordenada capturada del click de mouse a la
		// centena mas cercana
		// Datos de entrada: Point (Objeto de punto en el plano)
		// salida: Point

		return p;
	}

	public void insert_new_point(Point p) {
		// Nombre insert_new_point
		// Propósito: Ingresa un objeto de tipo punto en el array de puntos
		// Datos de entrada: objeto tipo point
		// salida: vacio
		int aux_turn = 0;

	
	if (chip_counter < 42 && main_board.get_row_free_pos(p.x/100) >= 0) {
		if (turn == player1) {
			int x = p.x / 100;
			int y = main_board.get_row_free_pos(x);
			main_board.insert_chip(x, turn);
			Chip chip = new Chip(x, y, Color.RED);
			chips_array[chip_counter] = chip;
			chip_counter++;
			aux_turn = 3;
			System.out.println("Player1 = " + main_board.detect_four_in_a_row(y, x, turn));
			
		} else if (turn == player2) {
				int x = p.x / 100;
				int y = main_board.get_row_free_pos(x);
				main_board.insert_chip(x, turn);
				Chip chip = new Chip(x, y, Color.BLACK);
				chips_array[chip_counter] = chip;
				chip_counter++;
				aux_turn = 4;
				System.out.println("Player2 = " + main_board.detect_four_in_a_row(y, x, turn));
		}

		if (aux_turn == 3) {// Sedo turno a player2
			turn = 2;
		}else if(aux_turn == 4){//Sedo turno a player1
			turn = 1;
		}
		
		//main_board.print_matriz();
		

		// System.out.print(x+" y "+y);
	}

	

		
	}
	
	public boolean validate_pos(Point p){
		return true;
	}

	// @overwrite
	public void update(Graphics g) {
		// Sobrescribir el método update

		paint(g);

	}

	public void start() {
		// Inicilización del hilo
		gameloop = new Thread(this);
		gameloop.start();
	}

	public void run() {
		// Se obtiene el hilo actual;
		Thread t = Thread.currentThread();

		while (t == gameloop) {
			try {

				game_update();

				Thread.sleep(20);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			repaint();
		}

	}

	public void stop() {
		gameloop = null;
	}

	private void game_update() {
		update_chip();
	}

	public void update_chip() {
		
		

	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		insert_new_point(e.getPoint());
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
