/**
 * Nom de la classe : Restart 
 * Fenetre apparaissant la fin d'une partie permettant d'en démarrer une nouvelle ou de quitter le jeu 
 */
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.lang.String;

public class Restart extends JFrame implements ActionListener{
	
	private FenetreJeu fenJ;
	private int largeur = 1100;
	private int hauteur = 600;
	private JButton quitte; 
	private JButton restart;
		
	/**
	 * Constructeur de la classe créant une fenetre dans lequelle on peut choisir de recommencer une partie ou quitter le jeu  
	 * @param fenJ 			FenetreJeu associée à la partie jouée 
	 * @param affichage 	affiche un message de fin de partie suivant si le joueur a gagné ou perdu
	 */	
	public Restart(FenetreJeu fenJ, String affichage){
		super("Trajectory Manager") ;
		this.fenJ = fenJ;
		setResizable(false);
		setBounds(300,100,largeur,hauteur);
		setBackground(Outils.FOND_BLEU) ;
		
		JPanel fenPrinc = new JPanel() ; //1
		fenPrinc.setLayout(null) ; 
		fenPrinc.setBounds(0,0,largeur,hauteur) ;
		fenPrinc.setBackground(Outils.FOND_BLEU) ;
		
		JLabel ecriture = new JLabel(affichage); //2
		ecriture.setBounds((int)(largeur*(6/29.7)),(int)(hauteur*(1/21.0)),(int)(largeur*(19/29.7)),(int)(hauteur*(10/21.0)));
		ecriture.setFont(new Font("Serif",Font.BOLD,42)) ;
		ecriture.setBackground(Outils.FOND_BLEU);
		
		quitte = new JButton("Quitter le Jeu"); //3
		quitte.setBounds((int)(largeur*(16.5/29.7)),(int)(hauteur*(10/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(6/21.0))) ;
		quitte.setFont(new Font("Stencil",Font.BOLD,30));
		quitte.setBackground(new Color (90,90,90));
		quitte.addActionListener(this); 
		
		restart = new JButton("<html><center> Recommencer <br> une partie </center></html>"); //4
		restart.setBounds((int)(largeur*(3.5/29.7)),(int)(hauteur*(10/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(6/21.0)));
		restart.setFont(new Font("Stencil",Font.BOLD,30));
		restart.setBackground(Color.red); 
		restart.addActionListener(this);
		
		fenPrinc.add(ecriture); //2
		fenPrinc.add(quitte); //3
		fenPrinc.add(restart); //4		
		this.add(fenPrinc);//1
		this.setVisible(true);
	}
	
	/**
	 * Implémentation de l'interface ActionListener
	 * Gère la visibilité des FenetreJeu et FenetreAccueil suite au clic sur le bouton rejouer ou quitter 
	 * Branchement au préalable des écouteurs sur les boutons 
	 * Ne renvoie rien 
	 * Name : actionPerformed 
	 * @param  e 	ActionEvent
	*/
	public void actionPerformed(ActionEvent e) {
		fenJ.setVisible(false);
		
		if(e.getSource()==restart){
			setVisible(false);
			new FenetreJeu();
		}
		
		if(e.getSource()==quitte){
			setVisible(false);
			new FenetreAccueil();
		}
	}
}

