import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class FenetreAccueil extends JFrame implements ActionListener {
	private int largeur = 1100 ;
	private int hauteur = 800 ;
	private JButton debut ; 
	boolean FenJeu ; 
	FenetreJeu fenetrejeu ;
	private JPanel FenPrinc ; 
	private Color couleur = new Color(135,206,235);
	
	public FenetreAccueil() {
		super("Trajectory Manager") ; 
		fenetrejeu = new FenetreJeu() ; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300,100,largeur,hauteur);
		FenJeu = false ;
		
		FenPrinc = new JPanel() ; 
		FenPrinc.setLayout(null) ; 
		FenPrinc.setBounds(0,0,largeur,hauteur) ;
		FenPrinc.setBackground(couleur) ;
		
		debut = new JButton("Commencer a jouer !") ; 
		debut.addActionListener(this) ; 
		debut.setBounds((int)(largeur*(4/29.7)),(int)(hauteur*(13/21.0)),(int)(largeur*(20/29.7)),(int)(hauteur*(4/21.0))) ;
		debut.setBackground(Color.red) ; 
		debut.setFont(new Font("Stencil",Font.BOLD,50)) ;
		
		JLabel logo = new JLabel() ;
		logo.setIcon(new ImageIcon("./logo_trajectory_fenetreAccueil.png")) ; 
		logo.setBounds(0,0,largeur,hauteur) ;
		
		JLabel regle = new JLabel("<html>Bienvenue dans notre jeu ludique et scientifique : TRAJECTORY MANAGER.<br>Les regles sont simples, il faut tirer dans le panier et atteindre le score de 1500.<br>Les tirs peuvent vous apporter jusqu'a 300 points.<br>Si vous n'avez plus de vies sans atteindre ce score alors vous avez perdu.<br>Bonne chance ;)</html>");
		regle.setBounds((int)(largeur*(4/29.7)),(int)(hauteur*(9/21.0)),(int)(largeur*(20/29.7)),(int)(hauteur*(4/21.0))) ;
		regle.setBackground(couleur) ;
		regle.setFont(new Font("Arial",Font.BOLD,18)) ;
		
		FenPrinc.add(logo) ; 
		FenPrinc.add(debut) ; 
		FenPrinc.add(regle) ; 
		this.add(FenPrinc) ;
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		FenJeu = true ;
		fenetrejeu.setVisible(FenJeu) ;
		setVisible(false) ;
	}	
	
}
