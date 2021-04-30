/**
 * Nom de la Classe : Outils
 * Classe composée d'outils visant à faciliter certaines opérations
*/
import java.awt.*;
import javax.swing.*;

public class Outils{
	
	static final Color FOND_BLEU = new Color(135,206,235);
	
	/** 
	 * Méthode qui permet de marquer une pause dans l'execution lorsque celle-ci est appelée
	 * Ne renvoie rien 
	 * Name : pause 
	 * @param temps 	long caractérisant le temps de la pause 
	 */
	public static void pause(long temps){
		long start = System.currentTimeMillis();
		
		while((System.currentTimeMillis() - start) < temps){
			// on attend 
		}
	}
	
	/**  
	 * Méthode qui définit la taille utilisable de l'écran de l'utilisateur 
	 * Ne prend en compte aucun paramètre 
	 * Name : tailleUtileEcran
	 * @return bounds 	rectangle ayant pour largeur et hauteur la résolution de l'écran de l'utilisateur
	 */
	 
	public static Rectangle tailleUtileEcran(){
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = env.getMaximumWindowBounds();
		return bounds;
	}
	
	/** 
	 * Méthode qui tronque un double après un certain de nombre de décimales 
	 * Name : coupeDecimale  
	 * @param  a 		valeur à laquelle on veut réaliser la coupe décimale de type double
	 * @return str 		chaîne de caractères de la taille voulue à la taille de la coupe décimale 		
	 */	
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
