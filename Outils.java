/**
 * Classe composée d'outils ayant pour but d'être utilisés dans d'autres classes dans le but de faciliter certaines opérations 
*/
import java.awt.*;
import javax.swing.*;

public class Outils{
	
	static final Color FOND_BLEU = new Color(135,206,235);
	
	/** Permet de marquer une pause dans le programme lorsque celle-ci est appelée
 * ne renvoie rien 
 * name : pause 
 * @param temps 	caractérise le temps de la pause 
 */
	public static void pause(long temps){
		//System.out.println("Début...");
		
		long start = System.currentTimeMillis();
		
		while((System.currentTimeMillis() - start) < temps){
			// on attend 
		}
		
		//System.out.println("...fin.");
	}
	
	/** Défini la taille utilisable de l'écran de l'utilisateur 
 * Ne prend en compte aucun paramètre 
 * name : tailleUtileEcran
 * @return bounds 	il s'agit d'un rectangle ayant pour largeur et hauteur celles utilisables sur l'écran de l'utilisateur
 */
	public static Rectangle tailleUtileEcran(){
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = env.getMaximumWindowBounds();
		//System.out.println(bounds);
		return bounds;
	}
	
	/** Tronque un double après un certain de nombre de décimales 
 * cast un double sur un string, or un string est une chaîne de caractères dont on peut choisir le nombre, ainsi on a la coupe décimale voulue 
 * name : coupeDecimale  
 * @param  a 		valeur à laquelle on veut réaliser la coupe décimale 
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
