/** 
 * Nom de la classe : FenetreMere
 * Classe abstraite permettant de poser une base de fenêtre commune à ses classes filles : FenetreJeu et FenetreScientifique
 */
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.lang.String;

public class FenetreMere extends JFrame implements ActionListener{
	
	protected JPanel fenPrinc ;
	protected JButton jouer ;
	protected int largeur;
	protected int hauteur;
	protected JButton retourFenAccueil;
	protected boolean enJeu = false;
	
	/**
	 * Constructeur par défaut et seul constructeur de la classe
	 * Ne prend en compte aucun paramètre 
	 * Crée une fenetreMere qui pourra etre différenciée par la suite en une fenêtre de jeu et une fenêtre scientifique
	 */
	public FenetreMere(){
		super("Trajectory Manager") ;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Rectangle r = Outils.tailleUtileEcran(); // pour prendre toute la taille de l'écran quelle que soit la résolution
		hauteur = (int) r.getHeight();
		largeur = (int) r.getWidth();
		setBounds((int) r.getX(),(int) r.getY(),largeur,hauteur);
		setResizable(false);		
		
		fenPrinc = new JPanel() ; //1
		fenPrinc.setLayout(null) ; 
		fenPrinc.setBounds(0,0,largeur,hauteur) ;
		fenPrinc.setBackground(Outils.FOND_BLEU) ; 
		fenPrinc.repaint();
		
		jouer = new JButton("Jouer ! ") ; //2
		jouer.setFont(new Font("Stencil",Font.BOLD,50)) ;
		jouer.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(17.7/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		jouer.setBackground(Color.red) ; 
		jouer.addActionListener(this);
		
		JLabel insa = new JLabel() ; //3
		insa.setIcon(new ImageIcon("./Images/insa_logo.png")) ; 
		insa.setBounds((int)(largeur*(25/29.7)),(int)(hauteur*(0.7/21)),(int)(largeur*(4/29.7)),(int)(hauteur*(1.5/21.0))) ;
		
		JLabel logo = new JLabel() ; //4
		logo.setIcon(new ImageIcon("./Images/logo_trajectory_fenetreJeu.png")) ; 
		logo.setBounds((int)(largeur*(20/29.7)),(int)(hauteur*(0.7/21)),(int)(largeur*(5/29.7)),(int)(hauteur*(3/21.0))) ;
		
		retourFenAccueil = new JButton ("retour"); //5
		retourFenAccueil.setFont(new Font("Serif",Font.BOLD,20)) ;
		retourFenAccueil.setBounds((int)(largeur*(0.5/29.7)),(int)(hauteur*(0.5/21.0)),(int)(largeur*(2/29.7)),(int)(hauteur*(1/21.0)));
		retourFenAccueil.setForeground(Color.white);
		retourFenAccueil.setBackground(new Color (90,90,90)); 
		retourFenAccueil.addActionListener(this);
				
		fenPrinc.add(jouer); //2
		fenPrinc.add(logo); //3
		fenPrinc.add(insa); //4
		fenPrinc.add(retourFenAccueil);	//5	
	}
	
	/**
	 * Implémentation de l'interface ActionListener 
	 * Permet de créer une nouvelle fenêtre d'accueil
	 * Branchement d'écouteur préalable sur le bouton concerné
	 * Ne renvoie rien 
	 * Name : actionPerformed 
	 * @param  e 	ActionEvent
	*/	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == retourFenAccueil){
			this.setVisible(false);
			new FenetreAccueil();
		}
	}
}
