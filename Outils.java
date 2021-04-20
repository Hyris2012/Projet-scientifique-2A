import java.awt.*;
import javax.swing.*;

public class Outils{
	
	static final Color FOND_BLEU = new Color(135,206,235);
	
	public static void pause(long temps){
		//System.out.println("Début...");
		
		long start = System.currentTimeMillis();
		
		while((System.currentTimeMillis() - start) < temps){
			// on attend 
		}
		
		//System.out.println("...fin.");
	}
	
	public static Rectangle tailleUtileEcran(){
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = env.getMaximumWindowBounds();
		//System.out.println(bounds);
		return bounds;
	}
	
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
