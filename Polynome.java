/**
 * Nom de la classe : Polynome
 * Cette classe crée et gère des polynomes de degré 2 
 * Elle permet aussi le dessin de leur courbe
 */
 
import java.util.*;

public class Polynome {
	
	//forme développée du polynome
	private double a;
	private double b;
	private double c;
	
	//forme canonique
	private double alpha;
	private double beta;
	
	// info sur le JPanel pour taille des arraylist 
	private int largeurPanel; 
	private int hauteurPanel;
	
	//valeurs des abscises et ordonnées EN PIXEL des pts du polynome, pour être affiché 
	private ArrayList <Double> valeurX;
	private ArrayList <Double> valeurY;
	
	//racines
	private double[] racines; 	
	
/**
 * Constructeur par défaut de la classe
 */	 
	public Polynome(){		
	}
	
	/**
 * Construit un Polynome de degré 2 de coefficients a, b et c 
 * Le polynôme est calculé pour être affiché dans le panel de dimensions les attributs largeur et hauteur
 * @param a 		coefficient du Polynome qui se trouve devant le terme x² 
 * @param b 		coefficient du Polynome qui se trouve devant le terme x
 * @param c 		coefficient constant du Polynome 
 * @param largeur 	largeur du panel dans lequel on veut afficher le Polynome 
 * @param hauteur 	hauteur du panel dans lequel on veut afficher le Polynome
 */ 
	public Polynome(double a, double b, double c, int largeur, int hauteur) {
		this.a = a;
		this.b = b;
		this.c = c;
		
		racines= new double[2];
		calculRacines();
		
		alpha = -b/(2*a);
		beta = calculFdeX(0);		
		
		this.valeurX = new ArrayList<Double>();
		this.valeurY = new ArrayList<Double>();
		this.largeurPanel = largeur;
		this.hauteurPanel = hauteur;
	    
	    remplissageListe();
	}
	
	/**
	 * Méthode qui calcule l'image y du réel x par le Polynome instancié.
	 * Name : calculFdeX
	 * @param x 	abscisse du point, l'antécédent de y par la fonction associée au Polynome 
	 * @return y 	image de x par la fonction associée au Polynome 
	 */	 
	public double calculFdeX(double x){ 	
		double y = a * Math.pow(x,2) + b * x + c;
		return y;
	}
	
	/**
	 * Méthode qui remplit les ArrayList permettant l'affichage de la courbe représentative du Polynome
	 * Ne prend pas de paramètre en compte et ne renvoie rien
	 * Name : remplissageListe 
	 */	 
	public void remplissageListe(){
		
		for(double i = 0; i < this.largeurPanel; i++){		
			valeurX.add(i);
	    }
	    double y;
		for(int i = 0; i < this.largeurPanel; i++){
			y = hauteurPanel - calculFdeX(valeurX.get(i));
			valeurY.add(y);
		}
	}
	
	/**
	 * Méthode qui calcule les racines du Polynome et les stocke dans l'attribut racines
	 * Ne prend pas de paramètre en compte et ne renvoie rien
	 * Name : calculRacines 
	 */
	public void calculRacines(){
		
		double delta;
		delta = b*b - 4*a*c;
		 if (delta == 0){			
			racines[0] = (-b/(2*a));
			racines[1] = (-b/(2*a));
			return; 
		} 
		if (delta > 0){
			double premiereRacine=(-b - Math.sqrt(delta))/(2*a);
			double deuxiemeRacine=(-b + Math.sqrt(delta))/(2*a);
			racines[0] = Math.min(premiereRacine,deuxiemeRacine);
			racines[1] = Math.max(premiereRacine,deuxiemeRacine);
		}
	}
	
	/**
	 * Méthode qui donne accès au lieu d'atterrissage : la racine la plus grande.
	 * Name : calculAtterissage 
	 * @return la valeur de la plus grande racine
	 */
	public double calculAtterrissage(){ 
		return Math.max(racines[0], racines[1]);
	}	
	
	/**
	 * Méthode qui renvoie l'extremum de la fonction Polynome 
	 * Ne prend pas de paramètre en compte
	 * Name : calculSommet 
	 * @return extremum 	la valeur de l'altitude maximale atteinte
	 */
	public double calculSommet(){
		double extremum = 0.0;
		if ((int) alpha < valeurY.size()){			
			extremum = valeurY.get((int)alpha);			
		}
		return extremum;
	}
	
	/**
	 * Affiche l'équation correspondant au Polynome créé
	 * Ne prend pas de paramètre en compte 
	 * Name : toString 
	 * @return String  équation en fonction de x correspondant au Polynome 
	 */
	public String toString (){
		return ("<html><center> P(X) = " + Outils.coupeDecimale(a) + " * X <sup>2</sup> + " + Outils.coupeDecimale(b) + " * X + " + Outils.coupeDecimale(c)+"</center></html>");
	}
	
	/**
	 * Calcule la distance entre les deux racines du Polynome
	 * Ne prend pas de paramètre en compte 
	 * Name : distanceEntreRacines 
	 * @return d 	de type double
	 */
	public double distanceEntreRacines(){
		double d = 0.0;
		if (racines != null){
			d = Math.abs(racines[0]-racines[1]);
		}
		return d;
	}
	
	/**
	 * Accesseur en lecture du tableau regroupant la valeur des racines du Polynome 
	 * Ne prend pas de paramètre
	 * @return racines 		tableau de doubles   
	 */
	public double[] getRacines(){
		return racines;
	}
	
	/**
	 * Accesseur en lecture de la liste regroupant les valeurs prises par x   
	 * Ne prend pas de paramètre
	 * @return valeurX 		valeurs prises par x lors de l'exécution du programme    
	 */
	public ArrayList <Double> getValeurX(){
		return valeurX;
	}
	
	/**
	 * Accesseur en lecture de la liste regroupant les valeurs prises par y 
	 * Ne prend pas de paramètre 
	 * @return valeurY 		valeurs prises par y lors de l'exécution du programme    
	 */
	public ArrayList <Double> getValeurY(){
		return valeurY;
	} 
}


