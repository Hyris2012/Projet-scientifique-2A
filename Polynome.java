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
	
	// info sur le JPanel pour taille des arraylist 
	private int largeurPanel; 
	private int hauteurPanel;
	
	//valeurs des abscises et ordonnées EN PIXEL des pts du polynome, pour être affiché 
	private ArrayList <Double> valeurX;
	private ArrayList <Double> valeurY;
	
	//racines
	private double[] racines; 
	
	public Polynome(){
		//tous les attributs initialisés à 0.0 par défaut ??
	}
	
	public Polynome(double a, double b, double c, int largeur, int hauteur) {
		this.a = a;
		this.b = b;
		this.c = c;
		
		racines= new double[2];
		calculRacines();
		
		alpha = -b/(2*a);
		beta = calculFdeX(0);		
		
		this.valeurX = new ArrayList<Double>();
		this.valeurY = new ArrayList<Double>();
		this.largeurPanel = largeur;
		this.hauteurPanel = hauteur;
	    
	    remplissageListe();
	}
	
	
	public double calculFdeX(double x){ 
	
		double y = a * Math.pow(x,2) + b * x + c;
		return y;
	}
	
	public void remplissageListe(){
		
		for(double i = 0; i < this.largeurPanel; i++){
			valeurX.add(i);
	    }
	    double y;
		for(int i = 0; i < this.largeurPanel; i++){
			y = hauteurPanel - calculFdeX(valeurX.get(i));
			valeurY.add(y);
		}
	}
	
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
		return ("P(X) = " + coupeDecimale(a) + " * X^2 + " + coupeDecimale(b) + " * X + " + coupeDecimale(c));// arrondir les a, b, c
	}
	
	public double distanceEntreRacines(){
		double d = Math.abs(racines[0]-racines[1]);
		return d;
	}
	/*methode pour enlever les decimales des coefficients du polynome
	 public static double decimale(double coeff){
		 String s=String.valueOf(coeff);  
		 //boolean decimale = false;
		 int i = 0;
		 while(i<s.length()){
			 if (s.charAt(i) == '.'){
			 s = s.substring(0, i+2);	 
		     }
		     i++;
	     }
		 coeff = Double.parseDouble(s);  
		 return coeff;
		 
	}*/
	
	public static String coupeDecimale(double a){
        String str = Double.toString(a);
        int i = 0;
        boolean b = false;
                
        while(!b && i < str.length()){
            b = str.charAt(i) == '.';
            i++;
        }
                
        if(b){
            int j = Math.min(str.length() , i+3);
            str = str.substring(0 ,j);               
        }
        return (str);      
    } 
}


