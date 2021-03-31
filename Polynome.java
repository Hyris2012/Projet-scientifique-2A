public class Polynome {
	
	// classe gère des polynomes de degré 2 
	
	//forme développée du polynome
	private double a;
	private double b;
	private double c;
	//forme canonique
	private double alpha;
	private double beta;
	//racines
	private double[] racines; 
	
	public Polynome(){
		//tous les attributs initialisés à 0.0 par défaut ??
	}
	
	public Polynome(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
		calculRacines();
		alpha = -b/(2*a);
		beta = calculFdeX(0.0);			//beta est l'ordonnée à l'origine 
		
	}
	
	
	public double calculFdeX(double x){
		double y = a * x * x + b * x + c;
		return y;
	}
	
	public void calculRacines(){
		
		double delta;
		delta = b*b - 4*a*c;
		if(delta < 0){
			new FenetreFinJeu("Erreur","Erreur : la trajectoire est souterraine ou aérienne.");
			return;
		} if (delta == 0){
			new FenetreFinJeu("Erreur", "Erreur : la trajectoire est paranormale");
			racines[0] = (-b/(2*a));
			racines[1] = (-b/(2*a));
			return; 
		} if (delta > 0){
			racines[0] = (-b - Math.sqrt(delta))/(2*a);
			racines[1] = (-b + Math.sqrt(delta))/(2*a);
		}
	}
	
	public double calculAtterrissage(){
		
		return Math.max(racines[0], racines[1]);
		
	}	
	
	public double calculSommet(){
		double extremum = calculFdeX(alpha);
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
}

