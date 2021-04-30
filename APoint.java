/** 
 * Nom de la classe : APoint
 * Classe dans laquelle les points de la trajectoire sont créés, issue d'un TP de 2A
 * Calculs entre différents points 
 */ 
public class APoint {
    
	// Attributs
    public double x;
    public double y;
    
    /**
     * Le constructeur
     * @param les coordonnées du point
     */
    public APoint(double ax, double ay){
        x = ax;
        y = ay;
    }
    
    /**
     * pour calculer la distance euclidienne par rapport à un autre point
     * @param le point à partir duquel il faut calculer la distance
     * @return la distance euclidienne
     */        
    public double distance( APoint otherPoint ) {
        double dx = x - otherPoint.x;
        double dy = y - otherPoint.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    public double getHauteurEntre(APoint autrePoint){
		return(Math.abs(y - autrePoint.y));
	}
    
    /**
     * Pour afficher les coordonnées du point
     * @return les coordonnées du point sous la forme [x=1.0,y=1.0]
     */
    public String toString() {
		String res ="";
        res="[x=" + x + ",y=" + y + "]";
        return res;  
    }    
}


	


