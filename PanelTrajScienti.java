import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/*
 * NOTE : j'ai bloqué le clic dans les 2 panels traj, ce qui implique pour panelTrajScienti qu'il faut recliquer sur jouer pour relancer une trajectoire, mais je me dis que c'est pas trop un problème puisqu'on veut bouger les JSlider entre chaque lancé et donc reAppuyer sur jouer
 *  
 * */

public class PanelTrajScienti extends PanelTraj {
	private FenetreScientifique fenS;
	
	public PanelTrajScienti(FenetreScientifique fenS, int x, int y, int l, int h){
		super(fenS, x, y, l, h);
		this.fenS = fenS;
		
		// initialisation de l'unique fond de ce mode scientifique
		fond = new Decor(null, Color.black, T.getImage("einstein.jpg").getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
	}
	
	public void lancerBalle(Balle balle){
		super.lancerBalle(balle);
		fenS.info.setText("<html> " + Outils.coupeDecimale(balle.getPolynome().calculAtterrissage()) + " mètres parcourus<br>" + Outils.coupeDecimale(this.getHeight() - balle.getPolynome().calculSommet()) + " mètres d'altitude atteints</html>");
		fenS.equa.setText("<html> Equation de la trajectoire : <br>"+ balle.getPolynome().toString()+"</html>"); // juste .equa marche parce qu'il est en protected dans FenetreMere et donc accessible dans tout le package
		
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		//System.out.println(balle.getPesanteur());
		repaint();
	}
	
	public void setVitesseAffichage(int i){
		this.vitesseAffichage=i;
	}
	
	public void actionPerformed(ActionEvent e){
		super.actionPerformed(e);
		
		if(e.getSource()==time && (atterrie()|| dernierXAffiche >= this.getWidth())){
			fenS.info.setVisible(true);
			fenS.enJeu=false;
		}
		
		
		
	}

}
