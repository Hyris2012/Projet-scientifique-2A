import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class FenetreAccueil extends JFrame implements ActionListener {
	private int largeur = 1100 ;
	private int hauteur = 800 ;
	private JButton scientifique ; 
	private JButton jeu ;
	
	private FenetreMere trajectory ;	// ancienne fenetreJeu (qui était de type FenetreJeu)
	private JPanel FenPrinc ; 
	private Color fondBleu = new Color(135,206,235);//ancien Color couleur
	
	public FenetreAccueil() {
		super("Trajectory Manager") ; 
		// trajectory = new FenetreMere(); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300,100,largeur,hauteur);
		setResizable(false);
		// setVisible(false) par défaut ?
		
		FenPrinc = new JPanel() ; 
		FenPrinc.setLayout(null) ; 
		FenPrinc.setBounds(0,0,largeur,hauteur) ;
		FenPrinc.setBackground(fondBleu) ;
		
		scientifique = new JButton("<html>Mode<br> scientifique !</html>") ; 
		scientifique.addActionListener(this) ; 
		scientifique.setBounds((int)(largeur*(4/29.7)),(int)(hauteur*(13/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(4/21.0))) ;
		scientifique.setBackground(Color.blue) ; 
		scientifique.setFont(new Font("Stencil",Font.BOLD,30));
		
		jeu = new JButton("Mode jeu !") ; 
		jeu.addActionListener(this) ; 
		jeu.setBounds((int)(largeur*(16/29.7)),(int)(hauteur*(13/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(4/21.0))) ;
		jeu.setBackground(Color.red) ; 
		jeu.setFont(new Font("Stencil",Font.BOLD,30)) ;
		
		JLabel logo = new JLabel() ;
		logo.setIcon(new ImageIcon("./logo_trajectory_fenetreAccueil.png")) ; 
		logo.setBounds(0,0,largeur,hauteur) ;
		
		JLabel regle = new JLabel("<html>Bienvenue dans notre jeu ludique et scientifique : TRAJECTORY MANAGER.<br>Les regles sont simples, il faut tirer dans le panier et atteindre le score de 1500.<br>Les tirs peuvent vous apporter jusqu'a 300 points.<br>Si vous n'avez plus de vies sans atteindre ce score alors vous avez perdu.<br>Bonne chance ;)</html>");
		regle.setBounds((int)(largeur*(4/29.7)),(int)(hauteur*(9/21.0)),(int)(largeur*(20/29.7)),(int)(hauteur*(4/21.0))) ;
		regle.setBackground(fondBleu) ;
		regle.setFont(new Font("Arial",Font.BOLD,18)) ;
		
		FenPrinc.add(logo); 
		FenPrinc.add(scientifique); 
		FenPrinc.add(jeu);
		FenPrinc.add(regle); 
		this.add(FenPrinc);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == jeu){
			trajectory = new FenetreJeu();
		}else if (e.getSource() == scientifique){
			trajectory = new FenetreScientifique();
		}
		//trajectory.setVisible(true);
		this.setVisible(false);
	}	
	
}
