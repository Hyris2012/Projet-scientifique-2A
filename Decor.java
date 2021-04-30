/** 
 * Nom de le classe : Decor
 * La classe Decor crée des objets de type Decor, elle crée un univers de jeu en associant un fond à une musique qui lui est propre 
 */
import java.awt.*;

public class Decor {
	
	private AePlayWave musiqueChoisie;
	private Color couleurChoisie; 		// black par défaut
	private Image imageChoisie; 	
	
	/** Constructeur par défaut
	 */
	public Decor(){
	}

	/** 
	 * Constructeur définissant le décor avec la musique qui lui est associée, son image et la couleur du thème
	 * @param m 	caractérise la musique associée au décor
	 * @param c 	couleur de la cible et du vecteur associé 
	 * @param i 	image représentant le fond du décor	 * 
	 */
	public Decor (AePlayWave m, Color c, Image i){
		musiqueChoisie = m; 
		couleurChoisie = c; 
		imageChoisie = i;
	}
	
	/**
	 * Accesseur en lecture de la musique asssociée au décor
	 * Ne prend pas de paramètre en compte  
	 * @return aePlayWave 		musiqueChoisie qui est associée au décor choisi  
	 */	
	public AePlayWave getMusiqueChoisie(){
		return this.musiqueChoisie;
	}
	
	/**
	 * Accesseur en lecture de la couleur du thème  
	 * Ne prend pas de paramètre en compte  
	 * @return Color 		couleurChoisie du thème du décor  
	 */	
	public Color getCouleurChoisie(){
		return this.couleurChoisie;
	}
	
	/**
	 * Accesseur en lecture de l'image associée au décor choisi
	 * Ne prend pas de paramètre en compte  
	 * @return Image 		imageChoisie associée au décor choisi   
	 */	
	public Image getImageChoisie(){
		return this.imageChoisie;
	}
}
