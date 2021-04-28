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
		fenS.vInit.setText("<html><center> " +  Outils.coupeDecimale((balle.getVitesseInitiale())/100) + " m/s </center></html>"); // divisé par 100 car on choisit arbitrairement que 1m = 100 pixels
		fenS.aInit.setText("<html><center> " +  Outils.coupeDecimale(balle.getAngleInitial()) + " ° </center></html>");
		fenS.equationCourante.setText("<html><center> " + balle.getPolynome().toString() + "</center></html>"); // juste .equationCourante marche parce qu'il est en protected dans FenetreMere et donc accessible dans tout le package
		
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
			
			fenS.distParcourue.setText("<html><center> " + Outils.coupeDecimale((balle.getPolynome().calculAtterrissage())/100) + " m parcourus </center></html>"); // divisé par 100 car on choisit que 1m = 100 pixels
			fenS.hAtteinte.setText("<html><center>" + Outils.coupeDecimale((this.getHeight() - balle.getPolynome().calculSommet())/100) + " m d'altitude atteints</center></html>");
			fenS.enJeu=false;
		}
		
		
		
	}

}
