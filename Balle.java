import java.util.*;
public class Balle {
	
	
	// ces 3-là ne sont pas encore utilisés 
	private double rayon;
	private double masse;
	private double taille;
	
	private double vitesseInitiale;
	private double angleIni;			// angle en radians 
	private double pesanteur;		
	
	private Vecteur depart; 
	private Polynome traj; 
	
	// distanceAterrissage est distance en x entre le point de départ et le point d'arrivée
	// hauteurMax = hauteur de la flèche au plus haut 

	/**
	 * constructeur vide de Balle
	 */
	
	public Balle (){
		
	}
	
	public Balle(double m, double r, Vecteur v, int largeur, int hauteur, double g){
		
		this.pesanteur = g; //*4 car sinon ça sort trop vite de l'écran
		this.angleIni = v.getArgument();	
		this.vitesseInitiale = v.getModule()/2;		//pourquoi sur 2 
		this.depart = v;
		
		// ces 3-là ne sont pas exploités dans le jeu ; taille et rayon même pas dans les calculs 
		this.masse = m;
		this.rayon = r;
	    this.taille = 1; 
	    
	    initPolynome(largeur, hauteur);
		
	}

	public Balle(double m, double r, Vecteur v, int largeur, int hauteur){
		this(m, r, v, hauteur, largeur, 9.81*4);
	}
	
	/**
	 * méthode permettant l'initialisation d'un polynôme qui décrit la trajectoire de la balle 
	 * en fonction de ses caractéristiques (masse, vitesse initiale, angle de lancer, gravité à laquelle elle est soumise)
	 * @param largeur et hauteur du panel dans lequel la trajectoire de la balle va être calcullée et dessinée
	 * @return void
	 */
	
	public void initPolynome(int largeurPanel, int hauteurPanel){
		double a = -(pesanteur)/(2*((Math.pow(vitesseInitiale*Math.cos(angleIni),2))));
		double b = Math.tan(angleIni);
		double c = depart.getBase().y; 
		
		traj = new Polynome(a, b, c, largeurPanel, hauteurPanel);
	}
	
	/**
	 * informations sur le parcours de la balle
	 * @param pas de paramètres 
	 * @return chaine de caractères : distance parcourue et altitude maximale atteinte
	 */
	
	public String toString (){
		return ("La balle a parcouru " + (int)(traj.distanceEntreRacines())+" pixels "+ "et a atteint une hauteur maximale de " + (int)(traj.calculSommet())+" pixels");
	}
	
	// accesseurs en lecture 
	
	/**
	 * accesseur en lecture de l'attribut Polynome traj
	 * @param aucun
	 * @return l'attribut traj de type Polynome
	 */
	
	public Polynome getPolynome(){		// devrait s'appeler getTraj
		return this.traj;
	}
	
	/**
	 * accesseur en lecture de l'attribut pesanteur
	 * @param aucun
	 * @return l'attribut pesanteur de type double
	 */
	
	public double getPesanteur(){
		return this.pesanteur/4;
	}
	
	/**
	 * accesseur en écriture de l'attribut pesanteur
	 * @param double
	 * @return void
	 */
	
	public void setPesanteur(double p){
		this.pesanteur=p*4;
	}
		
		

}
