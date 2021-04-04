import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class FenetreFinJeu extends JFrame {
	
	public FenetreFinJeu(){
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(550, 400, 400, 200);
		
		JLabel etiquette = new JLabel();
		etiquette.setBounds(10, 10, 200, 80);
		
		JPanel unique = new JPanel();
		unique.setLayout(null);
		unique.setBackground(Color.blue);
		
		JPanel second = new JPanel();
		second.setLayout(null);
		second.setBounds(60, 40, 280, 100);
		second.setBackground(Color.white);
		
		second.add(etiquette);
		
		unique.add(second);
		
		
	//	add(test);
		
		setVisible(true);
	}
	
	public FenetreFinJeu(String type, String affichage){
		super(type);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(550, 400, 550, 250);
		
		JLabel etiquette = new JLabel(affichage);
		etiquette.setBounds(10, 10, 450, 150);
		
		JPanel unique = new JPanel();
		unique.setLayout(null);
		unique.setBackground(Color.blue);
		
		JPanel second = new JPanel();
		second.setLayout(null);
		second.setBounds(60, 40, 500, 200);
		second.setBackground(Color.white);
		
		second.add(etiquette);
		
		unique.add(second);
		
		add(unique);
		
		setVisible(true);
		
	}
	
}
