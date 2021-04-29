/**
 * Cette classe représente un conteneur permettant le déroulement d'une partie de Trajectory Manager en mode scientifique.
 */
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class PanelTrajScienti extends PanelTraj {
	private FenetreScientifique fenS;
	
	/**
 * Constructeur de la classe qui permet de créer le conteneur de la fenetreScientifique 
 * @param fenS 	fenetreScientifique qui est liée à la partie en cours 
 * @param x 	position en x dans le Panel au début du jeu
 * @param y 	position en y dans le Panel au début du jeu
 * @param l 	largeur du PanelTraj choisie 
 * @param h 	hauteur du PanelTraj choisie
 */	
	public PanelTrajScienti(FenetreScientifique fenS, int x, int y, int l, int h){
		super(fenS, x, y, l, h);
		this.fenS = fenS;
		
		// initialisation de l'unique fond de ce mode scientifique
		fond = new Decor(null, Color.black, T.getImage("einstein.jpg").getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
	}
	
	/**Redéfinition de la méthode lancerBalle de la classe mère
 * Permet l'affichage de l'équation de la trajectoire et des caractéristiques de celle-ci    
 * Ne renvoie rien
 * name : lancerBalle 
 * @param balle 	caractérise l'objet choisi à faire évoluer dans le panel 
*/
	public void lancerBalle(Balle balle){
		super.lancerBalle(balle);
		fenS.vInit.setText("<html><center> " +  Outils.coupeDecimale((balle.getVitesseInitiale())/100) + " m/s </center></html>"); // divisé par 100 car on choisit arbitrairement que 1m = 100 pixels
		fenS.aInit.setText("<html><center> " +  Outils.coupeDecimale(balle.getAngleInitial()) + " ° </center></html>");
		fenS.equationCourante.setText("<html><center> " + balle.getPolynome().toString() + "</center></html>"); // juste .equationCourante marche parce qu'il est en protected dans FenetreMere et donc accessible dans tout le package
	}
	
	/**Redéfini la méthode de la classe mère 
 * Actualise l'affichage grâce au repaint 
 * Ne renvoie rien  
 * name : mouseClicked 
 * @param e		MouseEvent est un évènement qui se déclenche dès qu'une action précise est réalisée, ici dès qu'on clic avec la souris   
*/
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		repaint();
	}
	
	/**Accesseur en écriture de la vitesse d'affichage 
 * Permet de modifier la vitesse d'affichage dans le PanelTrajScienti
 * Associée à un Jslider permettant de choisir directement la vitesse d'affichage 
 * Ne renvoie rien 
 * name : setVitesseAffichge 
 * @param i 	vitesse d'affichage choisie sur le JSlider associé 
*/
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
