/** 
 * Nom de la classe : Balle
 * Classe dans laquelle les objets qu'on lance sont créés  
 */ 
 import java.util.*;

public class Balle {
		
	private double vitesseInitiale;
	private double angleIni;			// angle en radians 
	private double pesanteur;		
	private final double PESANTEUR_PAR_DEFAUT = 9.81;
	
	private Vecteur depart; 
	private Polynome traj; 
	
/**
	* Constructeur par défaut de Balle
	*/	
	public Balle (){
	}
		
/**
	 * Constructeur permettant de créer une balle à partir des paramètres suivants :
	 * @param v			  vecteur permettant d'avoir accès à l'angle et au module de la vitesse initiale donnée à la balle       
	 * @param largeur     fixe la largeur du panel dans lequel la trajectoire va être dessinée 
	 * @param hauteur     fixe la hauteur du panel dans lequel la trajectoire va être dessinée
	 */
	public Balle(Vecteur v, int largeur, int hauteur){
		
		this.pesanteur = PESANTEUR_PAR_DEFAUT*4;  // on multiplie par 4 sinon ça sort trop vite de l'écran dans le mode jeu
		this.angleIni = v.getArgument();	
		this.vitesseInitiale = v.getModule()/2;		// on /2 car sinon la vitesse initiale est un peu trop importante et on joue tout le temps avec des petits vecteurs 
		this.depart = v;
	    	    
	    initPolynome(largeur, hauteur);		
	}	
		
	 /**
 * Constructeur prenant en compte les même paramètres que précédemment excepté + @param g la gravité choisie
 */
	public Balle(Vecteur v, int largeur, int hauteur, double g){
		
		this.pesanteur = g;
		this.angleIni = v.getArgument();	
		this.vitesseInitiale = v.getModule()/2;		// on /2 car sinon la vitesse initiale est un peu trop importante et on joue tout le temps avec des petits vecteurs 
		this.depart = v;
	    	    
	    initPolynome(largeur, hauteur);
		
	}
	
	/**
	* Méthode permetant l'initialisation d'un polynôme décirvant la trajectoire de la balle 
	* Name : initPolynome
	* @param 	largeur du panel dans lequel la trajectoire de la balle va être calculée et dessinée
	* @param 	hauteur du panel dans lequel la trajectoire de la balle va être calculée et dessinée
	*/	
	public void initPolynome(int largeurPanel, int hauteurPanel){
		double a = -(pesanteur)/(2*((Math.pow(vitesseInitiale*Math.cos(angleIni),2))));
		double b = Math.tan(angleIni);
		double c = depart.getBase().y; 
		
		traj = new Polynome(a, b, c, largeurPanel, hauteurPanel);
	}
			
	/**
	 * Accesseur en lecture de l'attribut traj de type polynome 
	 * Ne prend pas de paramètres en compte
	 * @return traj 	Objet de type polynome, permet d'avoir accès à celui-ci 
	 */	
	public Polynome getPolynome(){
		return this.traj;
	}
		
	/**
	 * Accesseur en lecture de l'attribut vitesseInitiale
	 * Ne prend pas de paramètre
	 * @return vitesseInitiale l'attribut vitesseInitiale de type double
	 */	 
	public double getVitesseInitiale(){
		return this.vitesseInitiale;
	}
	
	/**
	 * Accesseur en lecture de l'attribut angleIni
	 * Ne prend pas de paramètre
	 * @return l'attribut angleIni converti en degrés de type double
	 */	
	public double getAngleInitial(){ // on choisit de retourner une valeur en degré, plus accessible à l'utilisateur
		return ((this.angleIni*180)/Math.PI);
	}
}
