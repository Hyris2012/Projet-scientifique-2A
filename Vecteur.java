/**
 * Nom de la classe : Vecteur
 * Classe permettant de créer le vecteur de base utilisé pour le lancer  
 */

public class Vecteur {
	private APoint base;
	private APoint pointe;
	private double module;
	private double argument; // pour récupérer l'angle fait par la souris --> en radians
	
	/**
	 * Constructeur par défaut de la classe vecteur 
	 */
	public Vecteur(){
	}
	
	/** Constructeur du vecteur si on connait les deux points entre lesquels tracer le vecteur
	 * @param b 	de type APoint représente la base du vecteur
	 * @param p 	de type APoint représente la pointe du vecteur 
	 */
	public Vecteur(APoint b, APoint p){
		base = b;
		pointe = p;
		module = b.distance(p);
		argument = Math.asin((p.getHauteurEntre(b))/module); // les angles sont en radian 		
	}
	
	/**Constructeur du vecteur si on connait le point de départ, le module et l'argument du vecteur
	 * @param b 	de type APoint représente la base du vecteur
	 * @param mod 	de type double représente le module du vecteur 
	 * @param arg 	de type double représente l'argument donné au vecteur 
	 */
	public Vecteur (APoint b, double mod, double arg){
		base = b;
		module = mod;
		argument = arg;
		double x = module*Math.cos(argument) + b.x;
		double y = module*Math.sin(argument) + b.y;
		pointe = new APoint(x , y);
	}
		
	/**
	 * Calcule la projection du vecteur sur l'axe x
	 * Ne prend pas de paramètre en compte  
	 * @return double 	valeur décimale de l'abscisse du vecteur   
	 */
	public double abscisse(){
		return (pointe.x -	base.x);
	}
	
	/**
	 * Calcule la projection du vecteur sur l'axe y
	 * Ne prend pas de paramètre en compte  
	 * @return double 	valeur décimale de l'ordonnée du vecteur   
	 */
	public double ordonnee(){
		return (pointe.y - base.y);
	}

	/**
	 * Accesseur en lecture des coordonnées de la base du vecteur 
	 * Ne prend pas de paramètre en compte  
	 * @return  base de type Apoint, coordonnées de la base du vecteur   
	 */	
	public APoint getBase(){
		return this.base;
	}
	
	/**
	 * Accesseur en lecture des coordonnées de la pointe du vecteur 
	 * Ne prend pas de paramètre en compte  
	 * @return pointe de type Apoint, coordonnées de la pointe du vecteur   
	 */
	public APoint getPointe(){
		return this.pointe;
	}
	
	/**
	 * Accesseur en lecture du module du vecteur 
	 * Ne prend pas de paramètre en compte  
	 * @return module de type double, valeur décimale du module du vecteur   
	 */
	public double getModule(){
		return this.module;
	}
	
	/**
	 * Accesseur en lecture de l'argument du vecteur 
	 * Ne prend pas de paramètre en compte  
	 * @return argument de type double, 	valeur décimale de l'argument du vecteur en radians
	 */
	public double getArgument (){
		return this.argument;
	}
}

