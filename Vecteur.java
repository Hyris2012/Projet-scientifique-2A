/**
 * Classe permettant de créer le vecteur de base utilisée pour le lancer  
 */

public class Vecteur {
	private APoint base; // à voir si on fait une balle qui s'initialise toujours au même endroit ou pas (ça serait mieux peut etre)
	private APoint pointe; // bout de la flèche 
	private double module;
	private double argument; // pour récupérer l'angle fait par la souris --> EN RADIANS ??
	
	/**
 * Constructeur par défaut de la classe vecteur 
 */
	public Vecteur(){
	}
	
	/** Constructeur du vecteur servant à viser si on connait les deux points entre lesquels tracer le vecteur
 * @param b 	représente la base du vecteur
 * @param p 	représente la pointe du vecteur 
 */
	public Vecteur(APoint b, APoint p){
		base = b;
		pointe = p;
		module = b.distance(p);
		argument = Math.asin((p.getHauteurEntre(b))/module); // les angles sont en radian 		
	}
	
/**Constructeur du vecteur servant à viser si on connait le point de départ, le module et l'argument du vecteur
 * @param b 	représente la base du vecteur
 * @param mod 	représente le module du vecteur 
 * @param arg 	représente l'argument donné au vecteur 
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
 * Accesseur en lecture des coordonnées de la base du vecteur 
 * name : getBase
 * Ne prend pas de paramètre en compte  
 * @return Apoint 	coordonnées de la base du vecteur   
 */	
	public APoint getBase(){
		return this.base;
	}
	
	/**
 * Accesseur en lecture des coordonnées de la pointe du vecteur 
 * name : getPointe
 * Ne prend pas de paramètre en compte  
 * @return Apoint 	coordonnées de la pointe du vecteur   
 */
	public APoint getPointe(){
		return this.pointe;
	}
	
	/**
 * Accesseur en lecture du module du vecteur 
 * name : getModule
 * Ne prend pas de paramètre en compte  
 * @return double 	valeur décimale du module du vecteur   
 */
	public double getModule(){
		return this.module;
	}
	
	/**
 * Accesseur en lecture de l'argument du vecteur 
 * name : getArgument
 * Ne prend pas de paramètre en compte  
 * @return double 	valeur décimale de l'argument du vecteur   
 */
	public double getArgument (){
		return this.argument;
	}
	
	/**
 * Calcule la valeur de la différence en abscisse entre la pointe et la base du vecteur
 * Donne la valeur en abscisse du vecteur 
 * name : abscisse
 * Ne prend pas de paramètre en compte  
 * @return double 	valeur décimale de l'abscisse du vecteur   
 */
	public double abscisse(){
		return (pointe.x -	base.x);
	}
	
	/**
 * Calcule la valeur de la différence en ordonnée entre la pointe et la base du vecteur
 * Donne la valeur en ordonnée du vecteur 
 * name : ordonnee
 * Ne prend pas de paramètre en compte  
 * @return double 	valeur décimale de l'ordonnée du vecteur   
 */
	public double ordonnee(){
		return (pointe.y - base.y);
	}
}

