import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class FenetreJeu extends JFrame{
	private JPanel FenPrinc ; 
	private JPanel paramG ;
	private JPanel courbe ; 
	private JPanel equa ;
	private JButton lancer ;

	public FenetreJeu () {
		super("Trajectory Manager") ;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		FenPrinc = new JPanel() ; 
		FenPrinc.setLayout(null) ; 
		
		paramG = new JPanel() ; 
		paramG.setLayout(null) ;
		paramG.setBounds(15,15,800,250) ;
		paramG.setBackground(Color.blue) ; 
		
		JLabel angle = new JLabel() ;
		angle.setBounds(10,10,230,50);
		angle.setText("Saisir l'angle choisi en degrés :") ;
		
		JSlider angle1 = new JSlider(0,90) ;
		angle1.setBounds(10,70,230,20) ;
		
		JLabel vitesse = new JLabel() ; 
		vitesse.setBounds(10,100,230,50) ; 
		vitesse.setText("saisir la vitesse initiale choisie (m/s) :") ;
		
		JLabel score = new JLabel() ; 
		score.setBounds(10,160,230,50) ;
		score.setText(" Score : X") ; 
		
		JLabel vie = new JLabel() ; 
		vie.setBounds(10,220,230,50) ;
		vie.setText("Encore Y vies") ; 
		
		lancer = new JButton() ; 
		lancer.setBounds(10,290,230,50) ;
		lancer.setText("Lancer !") ; 
		lancer.setBackground(Color.red) ; 
		
		paramG.add(angle) ;
		paramG.add(angle1) ;
		paramG.add(vitesse) ; 
		paramG.add(score) ;
		paramG.add(vie) ; 
		paramG.add(lancer) ; 
		FenPrinc.add(paramG) ;
		setVisible(true) ;  
	}
}
