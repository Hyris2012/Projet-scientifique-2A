import java.util.*;
public class Balle {
	
	private double rayon;
	private double masse; 
	private double taille;
	private double vitesseInitiale;
	private double angleIni;
	private double pensenteur;
	private ArrayList <Double> valeurX;
	private ArrayList <Double> valeurY;
	// faudra le remplacer par un vecteur les coordonées du point origine

	
	
	public Balle (){
		}
		
	public Balle(int angle, int v0 , int masse, int rayon , int taille, ArrayList <Double> X ){
		angleIni = angle;
		vitesseInitiale = v0;
		this.masse = masse;
		this.rayon = rayon;
		this.taille = taille; 
		valeurX = new ArrayList<Double>();
		valeurY = new ArrayList<Double>();
		
		for(double i= 0; i<500; i=i+1){
			X.add(i);
			
	    }
	}
	    
	public Balle(int angle, int v0 , int masse, int rayon , int taille, ArrayList <Double> X, double p ){
		this(angle, v0 , masse, rayon , taille, X);
		pensenteur = p;
		
		}
	
	public void calculTrajectoire(){
		int y = 0;
		for( int i = 0; i<valeurX.size(); i++){
			//y = -(pesenteur)/(angleIni)*(1/(Math.pow(vitesseInitiale*Math.cos(angleIni),2));
			}
		
		}
		

}

