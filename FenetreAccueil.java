import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class FenetreAccueil extends JFrame implements ActionListener {
	private int largeur = 1100 ;
	private int hauteur = 800 ;
	private JButton scientifique ; 
	private JButton jeu ;
	
	private FenetreMere trajectory ;	
	private JPanel FenPrinc ; 
	private final Image logoAccueil;
	private JLabel labelLogoAccueil;
	
	public FenetreAccueil() {
		super("Trajectory Manager") ; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300,100,largeur,hauteur);
		setResizable(false);
		
		FenPrinc = new JPanel() ; 
		FenPrinc.setLayout(null) ; 
		FenPrinc.setBounds(0,0,largeur,hauteur) ;
		FenPrinc.setBackground(Outils.FOND_BLEU) ;
		
		scientifique = new JButton("<html><center>Mode<br> scientifique !</center></html>") ; 
		scientifique.addActionListener(this) ; 
		scientifique.setBounds((int)(largeur*(4/29.7)),(int)(hauteur*(13/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(4/21.0))) ;
		scientifique.setBackground(Color.blue) ; 
		scientifique.setFont(new Font("Stencil",Font.BOLD,30));
		
		jeu = new JButton("Mode jeu !") ; 
		jeu.addActionListener(this) ; 
		jeu.setBounds((int)(largeur*(16/29.7)),(int)(hauteur*(13/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(4/21.0))) ;
		jeu.setBackground(Color.red) ; 
		jeu.setFont(new Font("Stencil",Font.BOLD,30)) ;
		
		logoAccueil = Toolkit.getDefaultToolkit().getImage("logo_trajectory_fenetreAccueil(2).png").getScaledInstance(this.getWidth(), 275, Image.SCALE_DEFAULT);
		labelLogoAccueil = new JLabel() ;
		labelLogoAccueil.setIcon(new ImageIcon(logoAccueil)) ; 
		labelLogoAccueil.setBounds(0,0,this.getWidth(),275) ;
		
		JLabel regle = new JLabel("<html><center><u>Mode Scientifique:</u> <br> Explorez à travers différents paramètres mis en votre disposition<br>leur influence sur la formation d'une trajectoire<br><br><br><u>Mode Jeu:</u><br> Lancez des objets incongrus sur une plateforme sans en toucher les bords, <br>Evitez les obstacles et marquez 3000 points pour gagner ! Bonne chance (*^▽^*) ! </center></html>");
		regle.setBounds((int)(largeur*(4.3/29.7)),(int)(hauteur*(7/21.0)),(int)(largeur*(20/29.7)),(int)(hauteur*(6/21.0))) ;
		regle.setBackground(Outils.FOND_BLEU) ;
		regle.setFont(new Font("Arial",Font.BOLD,18)) ;
		
		FenPrinc.add(labelLogoAccueil); 
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
		this.setVisible(false);
	}	
		
}
