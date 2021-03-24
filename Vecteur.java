public class Vecteur {
	private APoint base; // à voir si on fait une balle qui s'initialise toujours au même endroit ou pas (ça serait mieux peut etre)
	private APoint pointe; // bout de la flèche 
	private double module;
	private double argument; // pour récupérer l'angle fait par la souris 
	
	public Vecteur(){
	}
	
	public Vecteur( APoint b, APoint p, double m, double a){
		base=b;
		pointe=p;
		m=b.distance(p);
		argument= Math.asin((p.getHauteurEntre(b))/m);
		
	}
	
		
}

