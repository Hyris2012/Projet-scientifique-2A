import java.awt.*;
import javax.swing.*;

public class Outils{
	
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

}
