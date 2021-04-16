import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.lang.String;

public class FenetreScientifique extends FenetreMere {
	
	protected PanelTrajScienti courbe ; 
	private JSlider hauteurInit;
	private JSlider vitesseAffichage;
	private JSlider rayonChoisi;
	private JSlider graviteChoisie;
	private JPanel paramG;
	private JButton changePar;
	
	
	public FenetreScientifique(){
		super();
		
		courbe = new PanelTrajScienti(this, (int)(getWidth()*(10/29.7)),(int)(getHeight()*(2.5/21.0)),(int)(getWidth()*(18/29.7)),(int)(getHeight()*(14/21.0)));
		courbe.setLayout(null);
		courbe.setBackground(Color.white);
		
		info = new JLabel("Equation de la trajectoire : ") ;
		info.setBounds(courbe.getX(),jouer.getY(),courbe.getWidth(),jouer.getHeight());
		info.setBackground(Color.white); 
		info.setFont(new Font("Arial",Font.BOLD,26));
		
		// on pourrait garder un JSlider pour moduler la vitesse à laquelle on voit la balle bouger ?
		
		JLabel hInit = new JLabel("Hauteur initiale :"); //3 
		hInit.setFont(new Font("Arial",Font.BOLD,20)) ;
		hInit.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(6/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ; 
		
		hauteurInit = new JSlider(0,courbe.getHeight(),45) ; //2
		hauteurInit.setMajorTickSpacing((int)(courbe.getHeight()/6));
		hauteurInit.setPaintLabels(true) ; 
		hauteurInit.setPaintTicks(true) ; 
		hauteurInit.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(8/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		
		JLabel vitesse = new JLabel("Vitesse d'affichage :"); //3
		vitesse.setFont(new Font("Arial",Font.BOLD,20)) ;
		vitesse.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(10/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ; 
		
		vitesseAffichage = new JSlider(0,90,45) ; //4 //JE SAIS PAS ENCORE QUELLE VITESSE METTRE
		vitesseAffichage.setMajorTickSpacing(15);
		vitesseAffichage.setPaintLabels(true) ;
		vitesseAffichage.setPaintTicks(true) ; 
		vitesseAffichage.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(12/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0)));
		
		/*changePar = new JButton("changer parametres");
		changePar.setFont(new Font("Arial",Font.BOLD,20)) ;
		changePar.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(14.5/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		changePar.addActionListener(this);
		changePar.setBackground(Color.red);
		*/
		
		JLabel gravite = new JLabel("Gravité :"); //3
		gravite.setFont(new Font("Arial",Font.BOLD,20)) ;
		gravite.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(10/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ; // changer les bounds
		
		graviteChoisie = new JSlider(0,90,45) ; //4 //JE SAIS PAS ENCORE QUELLE ECHELLE METTRE
		graviteChoisie.setMajorTickSpacing(15);
		graviteChoisie.setPaintLabels(true) ;
		graviteChoisie.setPaintTicks(true) ; 
		graviteChoisie.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(12/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))); // changer les bounds 
		
		JLabel rayonBalle = new JLabel("Vitesse d'affichage :"); //3
		rayonBalle.setFont(new Font("Arial",Font.BOLD,20)) ;
		rayonBalle.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(10/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ; // changer les bonds
		
		rayonChoisi = new JSlider(0,90,45) ; //4 //JE SAIS PAS ENCORE QUELLE ECHELLE METTRE
		rayonChoisi.setMajorTickSpacing(15);
		rayonChoisi.setPaintLabels(true) ;
		rayonChoisi.setPaintTicks(true) ; 
		rayonChoisi.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(12/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))); // changer les bounds
		
		FenPrinc.add(courbe);
		FenPrinc.add(hauteurInit);
		FenPrinc.add(vitesseAffichage);
		FenPrinc.add(hInit);
		FenPrinc.add(vitesse);
		FenPrinc.add(info);
		FenPrinc.add(gravite);
		FenPrinc.add(graviteChoisie);
		FenPrinc.add(rayonBalle);
		FenPrinc.add(rayonChoisi);
		//FenPrinc.add(changePar);
		
		this.add(FenPrinc);		// est-ce que ce serait possible de le mettre dans FenetreMere
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource() == jouer){
			courbe.flecheSuitSouris = true;
			courbe.reInit();
			courbe.repaint();
			
			if(!enJeu){
				enJeu = true;
				courbe.setVisible(true);
			
				courbe.flecheSuitSouris = true;
				courbe.reInit();
				courbe.repaint();	
			}		
			
		}
		if(e.getSource() == changePar){
			enJeu = false ;
		}
	}

}
