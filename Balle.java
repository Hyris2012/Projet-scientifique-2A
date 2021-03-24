import java.util.*;
public class Balle {
	
	private double rayon;
	private double masse; 
	private double taille;
	private double vitesseInitiale;
	private double angleIni;
	private double pesenteur;
	private ArrayList <Double> valeurX;
	private ArrayList <Double> valeurY;
	// faudra le remplacer par un vecteur les coordonées du point origine
	private Vecteur depart; 
	

	
	
	public Balle (){
		}
		
	public Balle(int angle, int v0 , int masse, int rayon , int taille, ArrayList <Double> X,APoint b, APoint p, double m , double a){
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
	    depart = new Vecteur ( b,p,m,a);
	   
	    
	}
	    
	public Balle(int angle, int v0 , int masse, int rayon , int taille, ArrayList <Double> X, APoint b, APoint p, double m , double a, double g ){
		this(angle, v0 , masse, rayon ,taille, X, b, p, m, a);
		pesenteur = g;
		
		}
	
	public void calculTrajectoire(){
		double y = 0;
		for( int i = 0; i<valeurX.size(); i++){
			y = -(pesenteur)/2*(angleIni)*(1/(Math.pow(vitesseInitiale*Math.cos(angleIni),2)))*(Math.pow(i-depart.getBase().x,2))+Math.tan(angleIni)*(i-depart.getBase().x)+depart.getBase().y;
			valeurY.add(y);
			}
		
		
		}
	public void calculSommet(){
		
		
		
	}

}

