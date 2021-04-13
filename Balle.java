import java.util.*;
public class Balle {
	
	private double rayon;
	private double masse; 
	private double taille;
	private double vitesseInitiale;
	private double angleIni;			// angle en radians 
	private double pesanteur;
	// on a enlevé valeurX et valeurY de balle pour les mettre dans polynomes pour créer un polynome indépendamment de la balle et pouvoir l'afficher 
	private Vecteur depart; 
	private Polynome p; 
	
	// distanceAterrissage esr distance en x entre le point de départ et le point d'arrivée
	// hauteurMax = hauteur de la flèche au plus haut 

	
	
	public Balle (){
	}

	public Balle(double m, double r, Vecteur v, int distMax){
		
		this.angleIni = v.getArgument();	
		
		this.vitesseInitiale = v.getModule();
		this.masse = m;
		this.rayon = r;
		this.pesanteur=9.81;
		
		      
	    this.depart = v;
	    initPolynome(distMax);
	}


	public Balle(double m, double r, Vecteur v, int distMax, double g){
		this(m, r, v, distMax);
		this.pesanteur = g;
		
	}



	public void initPolynome(int distMax){
		double a = -(pesanteur)/(2*((Math.pow(vitesseInitiale*Math.cos(angleIni),2))));
		double b = Math.tan(angleIni);
		double c = 0; //depart.getBase().y;
		//System.out.println("a est : "+a+" ,b est "+b+" c est "+c);
		p = new Polynome(a, b, c, distMax);
		
		
	}
	
	// accesseurs en lecture 
	
	
	
	public Polynome getPolynome(){
		return this.p;
	}

}
