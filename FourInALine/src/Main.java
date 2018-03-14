import java.awt.BorderLayout;

import javax.swing.JFrame;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		 
		    //crear un objeto f de la clase JFrame 
		    JFrame f = new JFrame("Test Applet/Aplicación"); 

		    //crear una instancia de TestApplet 
		    Game game = new Game(); 

		    //añadir la instancia del applet al marco 
		    f.getContentPane().setLayout(new BorderLayout()); 
		    f.getContentPane().add("Center", game); 

		    //inicializar las variables al ancho y el alto de la tag <applet> 
		    int width = 900; 
		    int height = 700; 
		    f.setSize(width, height); 

		    //llamar a init() y a start() si es necesario 
		    game.init(); 
		    game.start(); 
		    //game.play_sound();
		    //hacer visible el marco 
		    f.setVisible(true); 
		    
		    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  


	}

}
