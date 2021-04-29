/** La classe FenetreMere permet de poser une base de fenêtre commune à ses classes filles : FenetreJeu et FenetreScientifique
 */
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.lang.String;

public abstract class FenetreMere extends JFrame implements ActionListener{
	
	protected JPanel fenPrinc ;
	protected JButton jouer ;
	protected int largeur;
	protected int hauteur;
	protected JButton retourFenAccueil;
	protected boolean enJeu = false;
	
	/**
 * Constructeur par défaut et seul constructeur de la classe
 * Ne prend en compte aucun paramètre 
 * Crée une fenetreMere qui pourra etre différenciée par la suite en une fenetre de jeu et une fenetre scientifique
 */
	public FenetreMere(){
		super("Trajectory Manager") ;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Rectangle r = Outils.tailleUtileEcran();
		hauteur = (int) r.getHeight();
		largeur = (int) r.getWidth();
		setBounds((int) r.getX(),(int) r.getY(),largeur,hauteur);
		setResizable(false);
		
		
		fenPrinc = new JPanel() ; 
		fenPrinc.setLayout(null) ; 
		fenPrinc.setBounds(0,0,largeur,hauteur) ;
		fenPrinc.setBackground(Outils.FOND_BLEU) ; 
		fenPrinc.repaint();
		
		jouer = new JButton("Jouer ! ") ; 
		jouer.setFont(new Font("Stencil",Font.BOLD,50)) ;
		jouer.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(17.7/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		jouer.setBackground(Color.red) ; 
		jouer.addActionListener(this);
		
		JLabel insa = new JLabel() ; 
		insa.setIcon(new ImageIcon("./insa_logo.png")) ; 
		insa.setBounds((int)(largeur*(25/29.7)),(int)(hauteur*(0.7/21)),(int)(largeur*(4/29.7)),(int)(hauteur*(1.5/21.0))) ;
		
		JLabel logo = new JLabel() ;
		logo.setIcon(new ImageIcon("./logo_trajectory_fenetreJeu.png")) ; 
		logo.setBounds((int)(largeur*(20/29.7)),(int)(hauteur*(0.7/21)),(int)(largeur*(5/29.7)),(int)(hauteur*(3/21.0))) ;
		
		retourFenAccueil = new JButton ("retour");
		retourFenAccueil.setFont(new Font("Serif",Font.BOLD,20)) ;
		retourFenAccueil.setBounds((int)(largeur*(0.5/29.7)),(int)(hauteur*(0.5/21.0)),(int)(largeur*(2/29.7)),(int)(hauteur*(1/21.0)));
		retourFenAccueil.setForeground(Color.white);
		retourFenAccueil.setBackground(new Color (90,90,90)); 
		retourFenAccueil.addActionListener(this);
		
		
		
		fenPrinc.add(jouer); 
		fenPrinc.add(logo);
		fenPrinc.add(insa);
		fenPrinc.add(retourFenAccueil);		
	}
	
	/**
 * Permet de créer une nouvelle fenêtre d'accueil si le joueur souhaite quitter la fenetre dans laquelle il se situe  
 * Branchement d'écouteurs auparavant sur le bouton concerné
 * ne renvoie rien 
 * name : actionPerformed 
 * @param  e 	ActionEvent
*/	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == retourFenAccueil){
			this.setVisible(false);
			new FenetreAccueil();
		}
	}
}
