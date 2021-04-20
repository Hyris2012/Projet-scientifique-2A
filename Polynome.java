/**
 * Cette classe gère des polynomes de degré 2. Elle permet aussi le dessin de leur courbe.
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
	 * Constructeur vide.
	 */
	 
	public Polynome(){
		
	}
	/**
	 * Construit un polynome de coefficients a, b, c, et prévu pour être affiché dans panel de dimensions largeur et hauteur.
	 * @param a, b, et c les coefficients du Polynome sous sa forme développée
	 * hauteur et largeur la taille en pixels du panel dans lequel on veut l'afficher
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
	 * Méthode de calcul de l'image y du réel x par la fonction du Polynome instance.
	 * @param x l'abscisse, l'antécédent
	 * @return y l'image de x par le Polynome
	 */
	 
	public double calculFdeX(double x){ 
	
		double y = a * Math.pow(x,2) + b * x + c;
		return y;
	}
	
	/**
	 * Méthode qui remplit les ArrayListe permettant l'affichage de la courbe représentative du Polynome.
	 * @param aucun
	 * @return void
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
	 * Calcule les racines du Polynome et les stocke dans l'attribut racines.
	 * @param aucun
	 * @return void
	 */
	 
	public void calculRacines(){
		
		double delta;
		delta = b*b - 4*a*c;
		if(delta < 0){
			//System.out.println("Erreur : la trajectoire est souterraine ou la balle ne touche pas le sol");
			return;
		} if (delta == 0){
			//System.out.println("Erreur : la trajectoire est paranormale");
			racines[0] = (-b/(2*a));
			racines[1] = (-b/(2*a));
			return; 
		} if (delta > 0){
			double premiereRacine=(-b - Math.sqrt(delta))/(2*a);
			double deuxiemeRacine=(-b + Math.sqrt(delta))/(2*a);
			racines[0] = Math.min(premiereRacine,deuxiemeRacine);
			racines[1] = Math.max(premiereRacine,deuxiemeRacine);
		}
	}
	
	/**
	 * Méthode donne accès au lieu d'atterrissage : la racine la plus grande.
	 * @param aucun
	 * @return double
	 */
	 
	public double calculAtterrissage(){ 
		return Math.max(racines[0], racines[1]);
	}	
	
	/**
	 * Renvoie l'extremum de la fonction Polynome. Correspond à l'altitude maximum atteinte par la balle.
	 * @param aucun
	 * @return void
	 */
	
	public double calculSommet(){
		double extremum = valeurY.get((int)alpha);
		if(beta > extremum){
			new FenetreFinJeu("Erreur", "Attention, la parabole est tournée vers le bas... trajectoire paranormale");
		}
		return extremum;
	}
	
	/**
	 * Affiche l'équation du Polynome.
	 * @param aucun
	 * @return void
	 */
	
	public String toString (){
		return ("<html> P(X) = " + Outils.coupeDecimale(a) + " * X <sup>2</sup> + " + Outils.coupeDecimale(b) + " * X + " + Outils.coupeDecimale(c)+"</html>");
	}
	
	/**
	 * Calcule la distance entre les deux racines du Polynome. 
	 * @param aucun
	 * @return void
	 */
	 
	public double distanceEntreRacines(){
		double d = Math.abs(racines[0]-racines[1]);
		return d;
	}
	
	public double getA(){
		return a;
	}
	
	public double getB(){
		return b;
	}
	
	public double getC(){
		return c;
	}
	
	public double getAlpha(){
		return alpha;
	}
	
	public double getBeta(){
		return beta;
	}
	
	public double[] getRacines(){
		return racines;
	}
	
	public ArrayList <Double> getValeurX(){
		return valeurX;
	}
	
	public ArrayList <Double> getValeurY(){
		return valeurY;
	} 
}


