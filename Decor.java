/** La classe Decor crée des objets de type décor c'est-à-dire des univers de jeu avec un fond et une musique propre à celui-ci
 * 
 * 
 */
import java.awt.*;
public class Decor {
	
	private AePlayWave musiqueChoisie;
	private Color couleurChoisie; 		// black par défaut
	private Image imageChoisie; 
	
	
/** Constructeur par défaut permettant la compilation
 */
	public Decor(){
	}

/** Constructeur qui permet de définir le décor avec la musique qui lui est associée, son image et la couleur du thème
 * @param m 	caractérise la musique associée au décor
 * @param c 	couleur de la cible et du vecteur associé 
 * @param i 	image représentant le fond du décor
 * 
 */
	public Decor (AePlayWave m, Color c, Image i){
	musiqueChoisie = m; 
	couleurChoisie = c; 
	imageChoisie = i;
	}
	
	/**
 * Accesseur en lecture de la musique asssociée au décor
 * name : getMusiqueChoisie 
 * Ne prend pas de paramètre en compte  
 * @return aePlayWave 		musique qui est asssocié au décor choisi  
 */	
	public AePlayWave getMusiqueChoisie(){
		return this.musiqueChoisie;
	}
	
	/**
 * Accesseur en lecture de la couleur du thème  
 * name : getCouleurChoisie
 * Ne prend pas de paramètre en compte  
 * @return Color 		couleur du thème du décor  
 */	
	public Color getCouleurChoisie(){
		return this.couleurChoisie;
	}
	
	/**
 * Accesseur en lecture de l'image associée au décor choisi
 * name : getImageChoisie
 * Ne prend pas de paramètre en compte  
 * @return Image 		image associée au décor choisi   
 */	
	public Image getImageChoisie(){
		return this.imageChoisie;
	}

}
