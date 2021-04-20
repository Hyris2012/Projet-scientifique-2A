import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.lang.String;


public abstract class FenetreMere extends JFrame implements ActionListener{
	
	protected JPanel FenPrinc ;
	protected JButton jouer ;
	protected int largeur;
	protected int hauteur;
	protected JButton retourFenAccueil;
	protected boolean enJeu = false;
	protected JLabel info;	// ancien 'equa'
	
	public FenetreMere(){
		super("Trajectory Manager") ;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Rectangle r = Outils.tailleUtileEcran();
		hauteur = (int) r.getHeight();
		largeur = (int) r.getWidth();
		setBounds((int) r.getX(),(int) r.getY(),largeur,hauteur);
		setResizable(false);
		
		
		FenPrinc = new JPanel() ; 
		FenPrinc.setLayout(null) ; 
		FenPrinc.setBounds(0,0,largeur,hauteur) ;
		FenPrinc.setBackground(Outils.FOND_BLEU) ; 
		FenPrinc.repaint();
		
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
		
		
		
		FenPrinc.add(jouer); 
		FenPrinc.add(logo);
		FenPrinc.add(insa);
		FenPrinc.add(retourFenAccueil);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == retourFenAccueil){
			this.setVisible(false);
			new FenetreAccueil();
		}
	}
}
