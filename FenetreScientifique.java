/*
 * 
 * 
 */
 
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.lang.String;


public class FenetreScientifique extends FenetreMere {
	
	protected PanelTrajScienti courbe ; 
	private JSlider angle1;
	private JSlider vitesse1;
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
		
		JLabel angle = new JLabel("saisir l'angle choisi en degres :"); //3
		angle.setFont(new Font("Arial",Font.BOLD,20)) ;
		angle.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(6/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ; 
		
		angle1 = new JSlider(0,90,45) ; //2
		angle1.setMajorTickSpacing(15);
		angle1.setPaintLabels(true) ; 
		angle1.setPaintTicks(true) ; 
		angle1.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(8/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		
		JLabel vitesse = new JLabel("saisir la vitesse initiale choisie (m/s) :"); //3
		vitesse.setFont(new Font("Arial",Font.BOLD,20)) ;
		vitesse.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(10/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ; 
		
		vitesse1 = new JSlider(0,90,45) ; //4
		vitesse1.setMajorTickSpacing(15);
		vitesse1.setPaintLabels(true) ;
		vitesse1.setPaintTicks(true) ; 
		vitesse1.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(12/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0)));
		
		changePar = new JButton("changer parametres");
		changePar.setFont(new Font("Arial",Font.BOLD,20)) ;
		changePar.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(14.5/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		changePar.addActionListener(this);
		changePar.setBackground(Color.red);
		
		
		FenPrinc.add(courbe);
		FenPrinc.add(angle1);
		FenPrinc.add(vitesse1);
		FenPrinc.add(angle);
		FenPrinc.add(vitesse);
		FenPrinc.add(info);
		FenPrinc.add(changePar);
		
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
