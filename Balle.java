import java.util.*;
public class Balle {
	
	private double rayon;
	private double masse; 
	private double taille;
	private double vitesseInitiale;
	private double angleIni;			// angle en radians 
	private double pesanteur;
	private ArrayList <Double> valeurX;
	private ArrayList <Double> valeurY;
	// faudra le remplacer par un vecteur les coordonées du point origine
	private Vecteur depart; 
	private Polynome p; 
	
	// distanceAterrissage esr distance en x entre le point de départ et le point d'arrivée
	// hauteurMax = hauteur de la flèche au plus haut 

	
	
	public Balle (){
		
	}


	public Balle(double m, double r, Vecteur v){
		
		this.angleIni = v.getArgument() * Math.PI/180;	// pour convertir en degrés les radians qu'on a reçu
		
		this.vitesseInitiale = v.getModule();
		this.masse = m;
		this.rayon = r;
		this.valeurX = new ArrayList<Double>();
		this.valeurY = new ArrayList<Double>();
		this.pesanteur=9.81;
		
		for(double i= 0; i<500; i++){
			valeurX.add(i);
	    }
	        
	    this.depart = v;
	    initPolynome();
	    calculTrajectoire();
	}


	public Balle(double m, double r, Vecteur v, double g){
		this(m, r, v);
		this.pesanteur = g;
		
	}



	public void initPolynome(){
		double a = -(pesanteur)/2*(1/(Math.pow(vitesseInitiale*Math.cos(angleIni),2)));
		double b = Math.tan(angleIni);
		double c = depart.getBase().y;
		//System.out.println("a est : "+a+" ,b est "+b+" c est "+c);
		p = new Polynome(a, b, c );
		
		
	}
	
	public void calculTrajectoire(){
		double y = 0;
		for( int i = 0; i<valeurX.size(); i++){
			y = p.calculFdeX(i);
			//-(pesanteur)/2*(angleIni)*(1/(Math.pow(vitesseInitiale*Math.cos(angleIni),2)))*(Math.pow(i-depart.getBase().x,2))+Math.tan(angleIni)*(i-depart.getBase().x)+depart.getBase().y;
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
