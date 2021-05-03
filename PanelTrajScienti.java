/**
 * Nom de la classe : PanelTrajScienti
 * Panel dans lequel sont affichés certains éléments du mode scientifique
 */
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class PanelTrajScienti extends PanelTraj {
	
	private FenetreScientifique fenS;
	
	/**
	 * Constructeur de la classe qui permet de créer la FenetreScientifique 
	 * @param fenS 	FenetreScientifique liée à la partie en cours 
	 * @param x 	position en x du PanelTrajScienti
	 * @param y 	position en y du PanelTrajScienti
	 * @param l 	largeur du PanelTrajScienti
	 * @param h 	hauteur du PanelTrajScienti
	 */	
	public PanelTrajScienti(FenetreScientifique fenS, int x, int y, int l, int h){
		super(fenS, x, y, l, h);
		this.fenS = fenS;
		
		// initialisation de l'unique fond de ce mode scientifique
		fond = new Decor(null, Color.black, T.getImage("Images/einstein.jpg").getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
	}
	
	/**
	 * Méthode redéfinissant lancerBalle de PanelTraj
	 * Permet l'affichage de l'équation de la trajectoire et des caractéristiques de celle-ci    
	 * Ne renvoie rien
	 * Name : lancerBalle 
	 * @param balle 	de type Balle, caractérise l'objet choisi à faire évoluer dans le panel 
	*/
	public void lancerBalle(Balle balle){
		super.lancerBalle(balle);
		fenS.vInit.setText("<html><center> " +  Outils.coupeDecimale((balle.getVitesseInitiale())/100) + " m/s </center></html>"); // divisé par 100 car on choisit arbitrairement que 1 m = 100 pixels
		fenS.aInit.setText("<html><center> " +  Outils.coupeDecimale(balle.getAngleInitial()) + " ° </center></html>");
		fenS.equationCourante.setText("<html><center> " + balle.getPolynome().toString() + "</center></html>"); // juste .equationCourante marche parce qu'il est en protected dans FenetreMere et donc accessible dans tout le package
	}
	
	/**
	 * Méthode redéfinissant mouseClicked de PanelTraj 
	 * Actualise l'affichage grâce au repaint 
	 * Ne renvoie rien  
	 * name : mouseClicked 
	 * @param e		MouseEvent  
	*/
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		repaint();
	}
	
	/**
	 * Methode permettant de modifier la vitesse d'affichage 
	 * Permet de modifier la vitesse d'affichage dans le PanelTrajScienti
	 * Associée à un Jslider permettant de choisir directement la vitesse d'affichage 
	 * Ne renvoie rien 
	 * Name : setVitesseAffichge 
	 * @param i 	de type int vitesse d'affichage choisie sur le JSlider associé 
	*/
	public void setVitesseAffichage(int i){
		this.vitesseAffichage=i;
	}
	
	/**
	 * Redéfinition de l'implémentation d' ActionListener héritée de PanelTraj 
	 * Ne renvoie rien 
	 * Name : actionPerformed 
	 * @param  e 	ActionEvent 
	 */	
	public void actionPerformed(ActionEvent e){
		super.actionPerformed(e);
		
		if(e.getSource()==time && (atterrie()|| dernierXAffiche >= this.getWidth())){
			
			fenS.distParcourue.setText("<html><center> " + Outils.coupeDecimale((balle.getPolynome().calculAtterrissage())/100) + " m parcourus </center></html>"); // divisé par 100 car on choisit que 1m = 100 pixels
			fenS.hAtteinte.setText("<html><center>" + Outils.coupeDecimale((this.getHeight() - balle.getPolynome().calculSommet())/100) + " m d'altitude atteints</center></html>");
			fenS.enJeu=false;
		}		
	}
}
