import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.lang.String;

public class Restart extends JFrame implements ActionListener{
	private FenetreJeu fenJ;
	private int largeur = 1100;
	private int hauteur = 800;
	private Color fondBleu = new Color(135,206,235);
	private JButton quitte; 
	private JButton restart;
	
	public Restart(String affichage){ //Pour vérifier que l'IHM est correcte 
		super("Trajectory Manager") ;
		setResizable(true);
		setBounds(300,100,largeur,hauteur);
		setBackground(fondBleu) ;
		
		JPanel FenPrinc = new JPanel() ; 
		FenPrinc.setLayout(null) ; 
		FenPrinc.setBounds(0,0,largeur,hauteur) ;
		FenPrinc.setBackground(fondBleu) ;
		
		JLabel ecriture = new JLabel(affichage); 
		ecriture.setBounds((int)(largeur*(10/29.7)),(int)(hauteur*(2/21.0)),(int)(largeur*(15/29.7)),(int)(hauteur*(10/21.0)));
		ecriture.setFont(new Font("Serif",Font.BOLD,26)) ;
		ecriture.setBackground(fondBleu);
		
		quitte = new JButton("Quitter le Jeu");
		quitte.setBounds((int)(largeur*(16.5/29.7)),(int)(hauteur*(10/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(6/21.0))) ;
		quitte.setFont(new Font("Stencil",Font.BOLD,26));
		quitte.setBackground(new Color (90,90,90)); 
		
		restart = new JButton("<html>Recommencer <br>une partie");
		restart.setBounds((int)(largeur*(3.5/29.7)),(int)(hauteur*(10/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(6/21.0)));
		restart.setFont(new Font("Stencil",Font.BOLD,26));
		restart.setBackground(Color.red); 
		
		FenPrinc.add(ecriture);
		FenPrinc.add(restart);
		FenPrinc.add(quitte);
		add(FenPrinc);
		setVisible(true);
	}
	
	public Restart(FenetreJeu fenJ, String affichage){
		super("Trajectory Manager") ;
		this.fenJ = fenJ;
		setResizable(true);
		setBounds(300,100,largeur,hauteur);
		setBackground(fondBleu) ;
		
		JPanel FenPrinc = new JPanel() ; 
		FenPrinc.setLayout(null) ; 
		FenPrinc.setBounds(0,0,largeur,hauteur) ;
		FenPrinc.setBackground(fondBleu) ;
		
		JLabel ecriture = new JLabel(affichage); 
		ecriture.setBounds((int)(largeur*(10/29.7)),(int)(hauteur*(2/21.0)),(int)(largeur*(15/29.7)),(int)(hauteur*(10/21.0)));
		ecriture.setFont(new Font("Serif",Font.BOLD,36)) ;
		ecriture.setBackground(fondBleu);
		
		quitte = new JButton("Quitter le Jeu");
		quitte.setBounds((int)(largeur*(16.5/29.7)),(int)(hauteur*(10/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(6/21.0))) ;
		quitte.setFont(new Font("Stencil",Font.BOLD,30));
		quitte.setBackground(new Color (90,90,90));
		quitte.addActionListener(this); 
		
		restart = new JButton("Recommencer une partie");
		restart.setBounds((int)(largeur*(3.5/29.7)),(int)(hauteur*(10/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(6/21.0)));
		restart.setFont(new Font("Stencil",Font.BOLD,30));
		restart.setBackground(Color.red); 
		restart.addActionListener(this);
		
		FenPrinc.add(ecriture);
		FenPrinc.add(restart);
		FenPrinc.add(quitte);
		add(FenPrinc);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		fenJ.setVisible(false);
		
		if(e.getSource()==restart){
			new FenetreJeu();
		}
		if(e.getSource()==quitte){
			setVisible(false);
		}
	}
}

