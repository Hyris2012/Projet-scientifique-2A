import java.awt.*;
public class Decor {
	
	AePlayWave musiqueChoisie;
	Color couleurChoisie; 		// black par défaut
	Image imageChoisie; 
	
	public Decor(){
	}


	public Decor (AePlayWave m, Color c, Image i){
	musiqueChoisie = m; 
	couleurChoisie = c; 
	imageChoisie = i;

	}
}
