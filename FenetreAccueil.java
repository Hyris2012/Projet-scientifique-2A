import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class FenetreAccueil extends JFrame implements ActionListener {
	private int largeur = 1000 ;
	private int hauteur = 800 ;
	private JButton debut ; 
	boolean FenJeu = true ; 
	FenetreAccueil fenetreprin ; 
	FenetreJeu fenetrejeu ;
	private JPanel FenPrinc ; 
	
	public FenetreAccueil() {
		super("Trajectory Manager") ; 
		fenetrejeu = new FenetreJeu() ; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300,100,largeur,hauteur);
		setBackground(Color.white) ; 
		
		FenPrinc = new JPanel() ; 
		FenPrinc.setLayout(null) ; 
		FenPrinc.setBounds(0,0,largeur,hauteur) ;
		FenPrinc.setBackground(Color.white) ;
		
		debut = new JButton("Commencer a jouer !") ; 
		debut.addActionListener(this) ; 
		debut.setBounds((int)(largeur*(4/29.7)),(int)(hauteur*(15/21.0)),(int)(largeur*(20/29.7)),(int)(hauteur*(4/21.0))) ;
		
		FenPrinc.add(debut) ; 
		this.add(FenPrinc) ;
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		fenetrejeu.setVisible(FenJeu) ; 
		FenJeu = false ; 
		fenetreprin.setVisible(FenJeu) ; 
	}	
	/*protected void paintComponent(Graphics g) {
	super.paintComponent(g); // permet de désinner les composants contenu dans le composant
	g.drawImage(new ImageIcon("CHEMIN"), 0, 0, this);
	}*/
}
