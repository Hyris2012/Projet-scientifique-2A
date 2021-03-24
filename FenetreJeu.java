import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class FenetreJeu extends JFrame{
	private JPanel FenPrinc ; 
	//private JPanel paramG ;
	private JPanel courbe ; 
	private JLabel equa ;
	private JButton lancer ;
	private int largeur = 1500;
	private int hauteur = 1000; 
	public int score1 ;
	public int vie1 ; 

	public FenetreJeu () {
		super("Trajectory Manager") ;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(180,10,largeur,hauteur);
		
		FenPrinc = new JPanel() ; 
		FenPrinc.setLayout(null) ; 
		FenPrinc.setBounds(0,0,largeur,hauteur) ;
		FenPrinc.setBackground(Color.white) ; 
		
		/* paramG = new JPanel() ; 
		paramG.setLayout(null) ;
		paramG.setBounds((int)(largeur*(0.2/29.7)),(int)(hauteur*(0.5/21.0)),(int)(largeur*(8.0/29.7)),(int)(hauteur*(19.0/21.0))) ;
		paramG.setBackground(Color.green) ; */
		
		JLabel angle = new JLabel("Saisir l'angle choisi en degres :") ; //1
		angle.setFont(new Font("Arial",Font.BOLD,20)) ;
		angle.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(0.7/21)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0)));
		
		JSlider angle1 = new JSlider(0,90,45) ; //2
		angle1.setMajorTickSpacing(15);
		angle1.setPaintLabels(true) ; 
		angle1.setPaintTicks(true) ; 
		angle1.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(2.5/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		
		JLabel vitesse = new JLabel("saisir la vitesse initiale choisie (m/s) :") ; //3
		vitesse.setFont(new Font("Arial",Font.BOLD,20)) ;
		vitesse.setBounds((int)(largeur*(0.7/21.0)),(int)(hauteur*(4.3/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ; 
		
		JSlider vitesse1 = new JSlider(0,90,45) ; //4
		vitesse1.setMajorTickSpacing(15);
		vitesse1.setPaintLabels(true) ;
		vitesse1.setPaintTicks(true) ; 
		vitesse1.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(6.1/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		
		score1 = 0 ; 
		JLabel score = new JLabel(" Score : "+score1) ; //5
		score.setFont(new Font("Serif",Font.BOLD,36)) ;
		score.setBounds((int)(largeur*(3/29.7)),(int)(hauteur*(7.9/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		
		vie1 = 0 ;
		JLabel vie = new JLabel("Encore "+vie1+" vies") ; //6
		vie.setFont(new Font("Serif",Font.BOLD,36)) ;
		vie.setBounds((int)(largeur*(3/29.7)),(int)(hauteur*(9.7/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		
		JLabel objet = new JLabel("Choisissez l'objet :") ; //7
		objet.setFont(new Font("Arial",Font.BOLD,20)) ;
		objet.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(11.5/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		
		JComboBox objet1 = new JComboBox() ; //8
		objet1.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(13.3/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1/21.0))) ;
		
		JLabel endroit = new JLabel("Choisissez l'endroit :") ; //9
		endroit.setFont(new Font("Arial",Font.BOLD,20)) ;
		endroit.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(14.6/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		
		JComboBox endroit1 = new JComboBox() ; //10
		endroit1.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(16.4/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1/21.0))) ;
		
		lancer = new JButton("Jouer ! ") ; //11
		lancer.setFont(new Font("Stencil",Font.BOLD,50)) ;
		lancer.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(17.7/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		lancer.setBackground(Color.red) ; 
		
		equa = new JLabel("Equation de la trajectoire : ") ; //12
		equa.setBounds((int)(largeur*(15/29.7)),(int)(hauteur*(15/21.0)),(int)(largeur*(15/29.7)),(int)(hauteur*(3/21.0))) ; 
		equa.setFont(new Font("Arial",Font.BOLD,32)) ;
		
		FenPrinc.add(angle) ; //1
		FenPrinc.add(angle1) ; //2
		FenPrinc.add(vitesse) ; //3
		FenPrinc.add(vitesse1) ; //4
		FenPrinc.add(score) ; //5
		FenPrinc.add(vie) ; //6
		FenPrinc.add(objet) ; //7
		FenPrinc.add(objet1) ; //8 
		FenPrinc.add(endroit) ; //9
		FenPrinc.add(endroit1) ; //10
		FenPrinc.add(lancer) ; //11
		FenPrinc.add(equa) ; //12
		this.add(FenPrinc) ;
		setVisible(true) ;  
	}
}
