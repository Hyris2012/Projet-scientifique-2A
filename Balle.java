import java.util.*;
public class Balle {
	
	private double rayon;
	private double masse; 
	private double taille;
	private double vitesseInitiale;
	private double angleIni;			// angle en radians 
	private double pesanteur=9.81*4;
	
	private Vecteur depart; 
	private Polynome traj; 
	
	// distanceAterrissage est distance en x entre le point de départ et le point d'arrivée
	// hauteurMax = hauteur de la flèche au plus haut 

	
	
	public Balle (){
		
	}

	public Balle(double m, double r, Vecteur v, int largeur, int hauteur){
		
		this.angleIni = v.getArgument();	
		
		this.vitesseInitiale = v.getModule()/2;
		System.out.println(vitesseInitiale+"v=");
		this.masse = m;
		this.rayon = r;
		//this.pesanteur = 9.81*4;
		
		      
	    this.depart = v;
	    initPolynome(largeur, hauteur);		// la largeur et la hauteur du panel pour le calcul des coordonnées pixel
	}


	public Balle(double m, double r, Vecteur v, int hauteur, int largeur, double g){
		this(m, r, v, hauteur, largeur);
		this.pesanteur = g*4; //*4 car sinon ça sort trop vite de l'écran
		
	}

	public void initPolynome(int largeurPanel, int hauteurPanel){
		double a = -(pesanteur)/(2*((Math.pow(vitesseInitiale*Math.cos(angleIni),2))));
		double b = Math.tan(angleIni);
		double c = depart.getBase().y; 
		
		traj = new Polynome(a, b, c, largeurPanel, hauteurPanel);
	}
	
	public String toString (){
		return ("La balle a parcouru " + (int)(traj.distanceEntreRacines())+" pixels "+ "et a atteint une hauteur maximale de " + (int)(traj.calculSommet())+" pixels");
	}
	
	// accesseurs en lecture 
	
	public Polynome getPolynome(){		// devrait s'appeler getTraj
		return this.traj;
	}
	
	public double getPesanteur(){
		return this.pesanteur/4;
	}
	
	public void setPesanteur(double p){
		this.pesanteur=p*4;
	}
		
		

}
