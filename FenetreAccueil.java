/** 
 * Nom de la classe : FenetreAccueil
 * Créé une fenêtre permettant de choisir son mode : scientifique ou jeu 
 */	
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class FenetreAccueil extends JFrame implements ActionListener {
	
	private int largeur = 1100 ;
	private int hauteur = 800 ;
	
	private JButton scientifique ; 
	private JButton jeu ;
	
	private FenetreMere trajectory ;	
	private JPanel fenPrinc ; 
	private final Image LOGO_ACCUEIL;
	private JLabel labelLogoAccueil;
	
	/**
	 * Constructeur par défaut de la classe
	 * Crée une fenêtreAccueil  
	 */ 
	public FenetreAccueil() {
		super("Trajectory Manager") ; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300,100,largeur,hauteur);
		setResizable(false);
		
		fenPrinc = new JPanel() ; //1
		fenPrinc.setLayout(null) ; 
		fenPrinc.setBounds(0,0,largeur,hauteur) ;
		fenPrinc.setBackground(Outils.FOND_BLEU) ;
		
		scientifique = new JButton("<html><center>Mode<br> scientifique !</center></html>") ; //2
		scientifique.addActionListener(this) ; 
		scientifique.setBounds((int)(largeur*(4/29.7)),(int)(hauteur*(13/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(4/21.0))) ;
		scientifique.setBackground(Color.blue) ; 
		scientifique.setFont(new Font("Stencil",Font.BOLD,30));
		
		jeu = new JButton("Mode jeu !") ; //3
		jeu.addActionListener(this) ; 
		jeu.setBounds((int)(largeur*(16/29.7)),(int)(hauteur*(13/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(4/21.0))) ;
		jeu.setBackground(Color.red) ; 
		jeu.setFont(new Font("Stencil",Font.BOLD,30)) ;
		
		LOGO_ACCUEIL = Toolkit.getDefaultToolkit().getImage("Images/logo_trajectory_fenetreAccueil.png").getScaledInstance(this.getWidth(), 275, Image.SCALE_DEFAULT);
		labelLogoAccueil = new JLabel() ; //4
		labelLogoAccueil.setIcon(new ImageIcon(LOGO_ACCUEIL)) ; 
		labelLogoAccueil.setBounds(0,0,this.getWidth(),275) ;
		
		JLabel regle = new JLabel("<html><center><u>Mode Scientifique:</u> <br> Explorez à travers différents paramètres mis en votre disposition<br>leur influence sur la formation d'une trajectoire<br><br><br><u>Mode Jeu:</u><br> Lancez des objets incongrus sur une plateforme sans en toucher les bords, <br>Evitez les obstacles et marquez 3000 points pour gagner ! Bonne chance (*^▽^*) ! </center></html>"); //5
		regle.setBounds((int)(largeur*(4.3/29.7)),(int)(hauteur*(7/21.0)),(int)(largeur*(20/29.7)),(int)(hauteur*(6/21.0))) ;
		regle.setBackground(Outils.FOND_BLEU) ;
		regle.setFont(new Font("Arial",Font.BOLD,18)) ;
		
		fenPrinc.add(scientifique); //2
		fenPrinc.add(jeu); //3
		fenPrinc.add(labelLogoAccueil); //4
		fenPrinc.add(regle); //5
		this.add(fenPrinc); //1
		this.setVisible(true);		
	}
	
	/**
	 * Implémentation de l'interface ActionListener 
	 * Gère les interactions avec les éléments d'IHM branchés
	 * ne renvoie rien 
	 * Name : actionPerformed 
	 * @param  e 	ActionEvent
	*/
	public void actionPerformed(ActionEvent e){		
		if(e.getSource() == jeu){
			trajectory = new FenetreJeu();
		}else if (e.getSource() == scientifique){
			trajectory = new FenetreScientifique();
		}
		this.setVisible(false);
	}		
}
