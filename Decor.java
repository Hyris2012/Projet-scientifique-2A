import java.awt.*;
public class Decor {
	
	private AePlayWave musiqueChoisie;
	private Color couleurChoisie; 		// black par défaut
	private Image imageChoisie; 
	
	public Decor(){
	}


	public Decor (AePlayWave m, Color c, Image i){
	musiqueChoisie = m; 
	couleurChoisie = c; 
	imageChoisie = i;

	}
	
	public AePlayWave getMusiqueChoisie(){
		return this.musiqueChoisie;
	}
	
	public Color getCouleurChoisie(){
		return this.couleurChoisie;
	}
	
	public Image getImageChoisie(){
		return this.imageChoisie;
	}

}
