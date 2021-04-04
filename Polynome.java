import java.util.*;

public class Polynome {
	
	// classe gère des polynomes de degré 2 
	
	//forme développée du polynome
	private double a;
	private double b;
	private double c;
	//forme canonique
	private double alpha;
	private double beta;
	//valeurs des abscises et ordonnées des pts du polynome
	private ArrayList <Double> valeurX;
	private ArrayList <Double> valeurY;
	//racines
	private double[] racines; 
	
	public Polynome(){
		//tous les attributs initialisés à 0.0 par défaut ??
	}
	
	public Polynome(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
		racines= new double[2];
		calculRacines();
		alpha = -b/(2*a);
		this.valeurX = new ArrayList<Double>();
		this.valeurY = new ArrayList<Double>();
		
		for(double i= 0; i<1500; i++){
			valeurX.add(i);
	    }
	    calculFdeX();
	    beta = valeurY.get(0);			//beta est l'ordonnée à l'origine 
	}
	
	
	public void calculFdeX(){
		for( int i = 0; i<valeurX.size(); i++){
			double y = a * Math.pow(valeurX.get(i),2) + b * valeurX.get(i) + c;
			valeurY.add(y);
		}
	}
	
	/*public void calculTrajectoire(){
		double y = 0;
		
			y = p.calculFdeX(i);
			//-(pesanteur)/2*(angleIni)*(1/(Math.pow(vitesseInitiale*Math.cos(angleIni),2)))*(Math.pow(i-depart.getBase().x,2))+Math.tan(angleIni)*(i-depart.getBase().x)+depart.getBase().y;
				
	}*/
	
	public void calculRacines(){
		
		double delta;
		delta = b*b - 4*a*c;
		if(delta < 0){
			new FenetreFinJeu("Erreur","Erreur : la trajectoire est souterraine ou la balle ne touche pas le sol");
			return;
		} if (delta == 0){
			new FenetreFinJeu("Erreur", "Erreur : la trajectoire est paranormale");
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
	
	public double calculAtterrissage(){ 
		
		return Math.max(racines[0], racines[1]);
		
	}	
	
	
	
	public double calculSommet(){
		double extremum = valeurY.get((int)alpha);
		if(beta > extremum){
			new FenetreFinJeu("Erreur", "Attention, la parabole est tournée vers le bas... trajectoire paranormale");
		}
		return extremum;
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
	
	public String toString (){
		return ("La balle a parcouru " + distanceEntreRacines() + " et a atteint une hauteur de "+ calculSommet());
	}
	
	public double distanceEntreRacines(){
		double d= Math.abs(racines[0]-racines[1]);
		return d;
	}
}

