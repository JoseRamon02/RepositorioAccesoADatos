package Vista;
import javax.swing.JFrame;

public class Game {
	
	public static void main(String[] args){
		
		JFrame window  = new JFrame("Dragon Bolas");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setContentPane(new GamePanel());
		window.setResizable(false);
		window.setVisible(true);
		window.pack();
		
	}
	

}
