/** Classe cible représente un objet cible OU obstacle de taille et vitesse variable.
 * 
 * 
 */
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Cible implements ActionListener {	//extends JPanel
	
	private int hauteur;
	private int largeur; 
	private PanelTraj courbe;
	private int positionX;
	private int positionY;
	private Timer time = new Timer(1, this);
	private int sens = 1;
	private int vitesse;
	private final int positionMin = 142;
	private final int positionMax = 1042; // car si l'obstacle est trop loin, il ne sert à rien
	private boolean estUneCible; // pour différencier la cible de l'obstacle
	private Image imageCible;
	
	/**
	 * Construit une cible associée au PanelTraj p
	 * @param h hauteur, l largeur de la cible, p PanelTraj associé, b booléen déterminant la nature de l'objet : true = cible, false = obstacle
	 */
	
	public Cible(double h, double l, PanelTraj p, boolean b){
	
	/*public Cible(double h, double l, PanelTraj p, boolean b, String imageUrl){
		Toolkit T = Toolkit.getDefaultToolkit();
	  	imageCible = T.getImage(imageUrl).getScaledInstance(largeur, hauteur, Image.SCALE_DEFAULT);*/
	  	
		estUneCible=b;
		courbe = p;
		
		hauteur = (int) (h*courbe.getHeight());
		largeur = (int) (l*courbe.getWidth());
		
		//System.out.println("h : "+hauteur+", l : "+largeur);
        
        setPosition();
	}
	
	/*public void dessine(Graphics g){
		g.drawImage(imageCible, positionX, positionY, null);
	}*/
	
	/**
	 * Methode déplace la coordonnée X de telle manière que l'objet fasse des aller-retour dans le panel
	 * A la vitesse en nb de pixels en attribut de l'objet
	 * @param aucun
	 * @return void
	 */
	
	public void deplaceX(){
		
		if (positionX <= positionMin){
			sens = 1;
		}else if(positionX >= courbe.getWidth()-largeur){
			sens = -1;
		}
		positionX = positionX + sens*vitesse;
	}
	
	public void deplaceY(){
		
		if (positionY <= 0){
			sens = 1;
		}else if(positionY >= courbe.getHeight()-100-hauteur){
			sens = -1;
		}
		positionY = positionY + sens*vitesse;
	}
	
	/**
	 * Méthode détermine si un point de coordonée x et y touche la cible (par le dessus).
	 * @param x et y les coordonnées du point
	 * @return booléen 
	 */
	 
	public boolean toucheCible(int x, int y){
		boolean b;
		b = (x >= positionX && x <= (positionX + this.largeur) && y >= positionY);
		return b;
	}
	
	public boolean toucheCoteCible(int x, int y){ // pas infaillible : si on tire en ligne droite sur le coin on gagne, si on tombe verticalement sur le coin on risque de perdre ; mais la proba est très faible
		boolean b;
		b = (x >= positionX && x <= (positionX + 10) && y >= positionY+5);	// 5 parce que la vitesse en expert est de 4
		return b;
	}
	
	/**
	 * Méthode détermine si un point de coordonée x et y touche l'obstacle.
	 * @param x et y les coordonnées du point
	 * @return booléen 
	 */
	 
	public boolean toucheObstacle(int x, int y){
		boolean b;
		b = (x > positionX && x < (positionX + this.largeur) && y >= positionY && y <= (positionY + hauteur));
		return b;
	}
		
	public void actionPerformed(ActionEvent e) {
		if(estUneCible){
			deplaceX();
		}else{
			deplaceY(); // pour que l'obstacle se déplace verticalement (expert)
		}
		courbe.repaint();
	}
	
	public void setVitesseCible(String s){
		switch (s){
        case "Débutant" :
			this.vitesse = 0;
			break;
        case "Intermédiaire":
			if (estUneCible){
				this.vitesse = 2;
			}else{
				this.vitesse = 0; // la cible bouge mais l'obstacle ne bouge pas
			}
            break;
        case "Expert":
			if (estUneCible){
				this.vitesse=4;
			}else{
				this.vitesse=2; // la cible bouge plus vite que l'obstacle
			}
            break;
        default :
            this.vitesse = 0;
            break;
        }
	}
	
	// geteurs et seteurs 
	public int getHauteurCible(){
		return this.hauteur;
	}
	
	public void setHauteurCible(int h){
		this.hauteur=h;
	}
	
	public int getLargeurCible(){
		return this.largeur;
	}
	
	public void setLargeurCible(int l){
		this.largeur=l;
	}
	
	public PanelTraj getCourbe(){
		return this.courbe;
	}
	
	public void setCourbe(PanelTraj c){
		this.courbe=c;
	}
	
	public int getPositionX(){
		return this.positionX;
	}
	
	public int getPositionY(){
		return this.positionY;
	}
	
	public void setPosition(){
		
		positionX=(int)(Math.random()*(courbe.getWidth()-largeur-courbe.getWidth()*0.01)); // le dernier terme correspond à la largeur des petits pics autour de la cible
		
		if(positionX<positionMin){ // pour éviter que la cible vienne contre l'origine, sinon c'est un peu trop facile de gagner
			positionX=positionMin;
		}
		
		if(!estUneCible && positionX>positionMax){
			positionX = positionMax;
		}
		
		if(!estUneCible){
			positionY=(int)(Math.random()*(courbe.getHeight()-hauteur-100));
		}else{
			positionY=courbe.getHeight()-hauteur;
		}	
	}
	
	public Timer getTimerCible(){
		return this.time;
	}
	
	public void setTimerCible(Timer t){
		this.time=t;
	}
	
	public int getVitesseCible(){
		return this.vitesse;
	}
	
	
}
