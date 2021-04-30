/** 
 * Nom de la classe : Cible
 * Créé et gère les objet cible OU obstacle de taille et vitesse variable utilisés dans le mode jeu
 */
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Cible implements ActionListener {
	
	private int hauteur;
	private int largeur; 
	private PanelTraj courbe;
	
	private int positionX;
	private int positionY;
	private final int POSITION_MIN = 142;
	private final int POSITION_MAX = 1042; // car si l'obstacle est trop loin, il ne sert à rien
	
	private Timer time = new Timer(1, this);
	private int sens = 1; // pour que la cible parte vers la droite au début
	private int vitesse;
	
	private boolean estUneCible; // pour différencier la cible de l'obstacle
	private Image imageCible;
	
	/**
	 * Constructeur qui met en place une cible associée au PanelTraj p
	 * @param h 	hauteur de la cible/obstacle  
	 * @param l 	largeur de la cible/obstacle
	 * @param p 	PanelTraj associé à la cible/obstacle
	 * @param b 	booléen déterminant la nature de l'objet : true = cible, false = obstacle
	 */	
	public Cible(double h, double l, PanelTraj p, boolean b){
	
		estUneCible=b;
		courbe = p;
		
		hauteur = (int) (h*courbe.getHeight());
		largeur = (int) (l*courbe.getWidth());
	        
        setPosition();
	}
	
	/**
	 * Methode qui déplace la coordonnée X pour que l'objet fasse des aller-retour horizontaux dans le panel
	 * Name : deplaceX
	 * Ne prend en compte aucun paramètre et ne renvoie rien 
	 */	
	public void deplaceX(){
		
		if (positionX <= POSITION_MIN){
			sens = 1;
		}else if(positionX >= courbe.getWidth()-largeur){
			sens = -1;
		}
		positionX = positionX + sens*vitesse;
	}
	
	/**
	 * Methode qui déplace la coordonnée y de telle manière que l'objet fasse des aller-retour verticaux dans le panel
	 * Name : deplaceY
	 * Ne prend en compte aucun paramètre et ne renvoie rien 
	 */
	public void deplaceY(){
		
		if (positionY <= 0){
			sens = 1;
		}else if(positionY >= courbe.getHeight()-100-hauteur){
			sens = -1;
		}
		positionY = positionY + sens*vitesse;
	}
	
	/**
	 * Méthode qui détermine si un point de coordonnée x et y touche la cible (par le dessus)
	 * Name : toucheCible
	 * @param  x 	coordonnées en abscisse du point
	 * @param  y 	coordonnées en ordonnée du point 
	 * @return b 	booléen qui renvoie true si la cible est touchée, false sinon
	 */	 
	public boolean toucheCible(int x, int y){
		boolean b;
		b = (x >= positionX && x <= (positionX + this.largeur) && y >= positionY);
		return b;
	}
	
	/**
	 * Méthode qui détermine si un point de coordonnée x et y touche le côté de la cible
	 * name : toucheCoteCible
	 * @param  x 	coordonnées en abscisse du point
	 * @param  y 	coordonnées en ordonnée du point 
	 * @return b 	booléen qui renvoie true si la cible est touchée, false sinon
	 */	 
	public boolean toucheCoteCible(int x, int y){ // pas infaillible : si on tire en ligne droite sur le coin on gagne, si on tombe verticalement sur le coin on risque de perdre ; mais la proba est très faible
		boolean b;
		b = (x >= positionX && x <= (positionX + 10) && y >= positionY+5);	// 5 parce que la vitesse en expert est de 4 (évite que l'objet "passe à travers")
		return b;
	}
	
	/**
	 * Méthode qui détermine si un point de coordonnée x et y touche un obstacle
	 * Name : toucheObstacle 
	 * @param  x 	coordonnées en abscisse du point
	 * @param  y 	coordonnées en ordonnée du point 
	 * @return b 	booléen qui renvoie true si l'obstacle est touché, false sinon
	*/		 
	public boolean toucheObstacle(int x, int y){
		boolean b;
		b = (x > positionX && x < (positionX + this.largeur) && y >= positionY && y <= (positionY + hauteur));
		return b;
	}
		
	/**
	 * Redéfinission de la méthode actionPerformed de l'interface ActionListener
	 * Entrée périodique dans le actionPerformed permet le déplacement de la cible selon x ou de l'obstacle selon y
	 * ne renvoie rien 
	 * Name : actionPerformed 
	 * @param  e 	ActionEvent
	*/	
	public void actionPerformed(ActionEvent e) {
		if(estUneCible){
			deplaceX();
		}else{
			deplaceY(); // pour que l'obstacle se déplace verticalement (expert)
		}
		courbe.repaint();
	}
	
	/**
	 * Méthode qui règle la vitesse de la cible ou de l'obstacle suivant la difficulté choisie 
	 * ne renvoie rien 
	 * Name : setVitesse 
	 * @param  s 	représente la difficulté choisie dans le JComboBox
	*/		
	public void setVitesseCible(String s){
		switch (s){
        case "Débutant" :
			this.vitesse = 0;
			break;
        case "Intermédiaire":
			if (estUneCible){
				this.vitesse = 2;
			}else{
				this.vitesse = 0; // la cible bouge mais l'obstacle ne bouge pas
			}
            break;
        case "Expert":
			if (estUneCible){
				this.vitesse=4;
			}else{
				this.vitesse=2; // la cible bouge plus vite que l'obstacle
			}
            break;
        default :
            this.vitesse = 0;
            break;
        }
	}
	
	/**
	 * Méthode qui initialise la position de la cible
	 * Name : setPosition
	 * Ne prend pas de paramètre et ne renvoie rien
	 */
	public void setPosition(){
		
		positionX=(int)(Math.random()*(courbe.getWidth()-largeur-courbe.getWidth()*0.01)); // le dernier terme correspond à la largeur des petits pics autour de la cible
		
		if(positionX<POSITION_MIN){ // pour éviter que la cible vienne contre l'origine, sinon c'est un peu trop facile de gagner
			positionX=POSITION_MIN;
		}
		
		if(!estUneCible && positionX>POSITION_MAX){
			positionX = POSITION_MAX;
		}
		
		if(!estUneCible){
			positionY=(int)(Math.random()*(courbe.getHeight()-hauteur-100));
		}else{
			positionY=courbe.getHeight()-hauteur;
		}	
	}
		
	/**
	 * Accesseur en lecture de la hauteur de la cible  
	 * Ne prend pas de paramètre en compte  
	 * @return int 		valeur de la hauteur de la cible    
	 */
	public int getHauteurCible(){
		return this.hauteur;
	}
		
	/**
	 * Accesseur en lecture de la largeur de la cible   
	 * Ne prend pas de paramètre
	 * @return int 		valeur de la Largeur de la cible    
	 */	
	public int getLargeurCible(){
		return this.largeur;
	}
	
	/**
	 * Accesseur en lecture de la largeur du PanelTraj dans lequel évolue la cible 
	 * Ne prend pas de paramètre en compte  
	 * @return PanelTraj 		PanelTraj actif avec tous ses paramètres  
	 */	
	public PanelTraj getCourbe(){
		return this.courbe;
	}
	
	/**
	 * Accesseur en écriture de la largeur du PanelTraj dans lequel évolue la cible 
	 * Ne renvoie rien  
	 * @param c		défini le PanelTraj actif, dans lequel toutes les modifications de déplacement auront lieu  
	 */	
	public void setCourbe(PanelTraj c){
		this.courbe=c;
	}
	
	/**
	 * Accesseur en lecture de la position en abscisses de la cible/obstacle 
	 * Ne prend pas de paramètre en compte  
	 * @return int 		défini la position en abscisses de la cible/obstacle dans le PanelTraj  
	 */	
	public int getPositionX(){
		return this.positionX;
	}
	
	/**
	 * Accesseur en lecture de la position en ordonnées de la cible/obstacle 
	 * Ne prend pas de paramètre en compte  
	 * @return int 		défini la position en ordonnées de la cible/obstacle dans le PanelTraj  
	 */
	public int getPositionY(){
		return this.positionY;
	}	
		
	/**
	 * Accesseur en lecture des attributs du timer en action
	 * Ne prend pas de paramètre en compte  
	 * @return time 	retourne un objet de type Timer, ayant pour paramètre un laps de temps entre chaque incrémentation  
	 */
	public Timer getTimerCible(){
		return this.time;
	}
		
	/**
	 * Accesseur en lecture de la vitesse de la cible  
	 * Ne prend pas de paramètre en compte  
	 * @return int 		prend la valeur de la vitesse de la cible, variant suivant le niveau de difficulté   
	 */	
	public int getVitesseCible(){
		return this.vitesse;
	}	
}
