import java.util.*;
public class Balle {
	
	private double rayon;
	private double masse; 
	private double taille;
	private double vitesseInitiale;
	private double angleIni;			// angle en radians 
	private double pesenteur;
	private ArrayList <Double> valeurX;
	private ArrayList <Double> valeurY;
	// faudra le remplacer par un vecteur les coordonées du point origine
	private Vecteur depart; 
	private Polynome p; 
	
	// distanceAterrissage esr distance en x entre le point de départ et le point d'arrivée
	// hauteurMax = hauteur de la flèche au plus haut 

	
	
	public Balle (){
		
	}


	public Balle(double angle, double v0 , double masse, double rayon , double taille, ArrayList <Double> X, APoint b, APoint p){
		
		angleIni = angle * Math.PI/180;	// pour convertir les degrés reçus en radians 
		
		vitesseInitiale = v0;
		this.masse = masse;
		this.rayon = rayon;
		this.taille = taille; 
		valeurX = new ArrayList<Double>();
		valeurY = new ArrayList<Double>();
		
		for(double i= 0; i<500; i=i+1){
			X.add(i);
			
	    }
	    
	    depart = new Vecteur (b,p);
	    
	    initPolynome();
	   
	    
	    
	}


	public Balle(double angle, double v0 , double masse, double rayon , double taille, ArrayList <Double> X, APoint b, APoint p, double g){
		this(angle, v0 , masse, rayon ,taille, X, b, p);
		pesenteur = g;
		
	}



	public void initPolynome(){
		double a = -(pesenteur)/2*(1/(Math.pow(vitesseInitiale*Math.cos(angleIni),2)));
		double b = Math.tan(angleIni);
		double c = depart.getBase().y;
		p = new Polynome(a, b, c );
		
		
	}
	
	public void calculTrajectoire(){
		double y = 0;
		for( int i = 0; i<valeurX.size(); i++){
			y = p.calculFdeX(i);
			//-(pesenteur)/2*(angleIni)*(1/(Math.pow(vitesseInitiale*Math.cos(angleIni),2)))*(Math.pow(i-depart.getBase().x,2))+Math.tan(angleIni)*(i-depart.getBase().x)+depart.getBase().y;
			valeurY.add(y);
		}
		
		
	}
	
	// accesseurs en lecture 
	
	public ArrayList <Double> getValeurX(){
		return valeurX;
	}
	
	public ArrayList <Double> getValeurY(){
		return valeurY;
	}
	
	public Polynome getPolynome(){
		return this.p;
	}

}
