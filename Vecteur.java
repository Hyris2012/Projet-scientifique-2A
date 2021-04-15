public class Vecteur {
	private APoint base; // à voir si on fait une balle qui s'initialise toujours au même endroit ou pas (ça serait mieux peut etre)
	private APoint pointe; // bout de la flèche 
	private double module;
	private double argument; // pour récupérer l'angle fait par la souris --> EN RADIANS ??
	
	public Vecteur(){
	}
	
	public Vecteur(APoint b, APoint p){
		base = b;
		pointe = p;
		module = b.distance(p);
		argument = Math.asin((p.getHauteurEntre(b))/module); // les angles sont en radian 
		
	}
	
	// constructeur : point de départ, module, argument
	public Vecteur (APoint b, double mod, double arg){
		base = b;
		module = mod;
		argument = arg;
		double x = module*Math.cos(argument) + b.x;
		double y = module*Math.sin(argument) + b.y;
		pointe = new APoint(x , y);
	}
	
	public APoint getBase(){
		return this.base;
	}
	
	public APoint getPointe(){
		return this.pointe;
	}
	
	public double getModule(){
		return this.module;
	}
	
	public double getArgument (){
		return this.argument;
	}
	
	public double abscisse(){
		return (pointe.x -	base.x);
	}
	
	public double ordonnee(){
		return (pointe.y - base.y);
	}
}

