import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class FenetreFinJeu extends JFrame {
	
	public FenetreFinJeu(String type, String affichage){
		super(type);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 400, 400, 200);
		
		JLabel etiquette = new JLabel("affichage");
		
		JPanel unique = new JPanel();
		unique.setLayout(null);
		unique.setBackground(Color.blue);
		
		JPanel second = new JPanel();
		second.setLayout(null);
		second.setBounds(60, 40, 280, 100);
		second.setBackground(Color.white);
		
		second.add(etiquette);
		
		unique.add(second);
		
		add(unique);
		
		setVisible(true);
		
	}
	
	public static void main (String[] args){
		new FenetreFinJeu("Test", "Hello world");
	}
	
}

