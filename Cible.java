/*
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
	private int sens=1;
	private int vitesse;
	private final int positionMin=142;
	private boolean estUneCible; // pour différencier la cible de l'obstacle
	private Image imageCible;
	
	
	public Cible(double h, double l, PanelTraj p, boolean b){
	
	/*public Cible(double h, double l, PanelTraj p, boolean b, String imageUrl){
		Toolkit T = Toolkit.getDefaultToolkit();
	  	imageCible = T.getImage(imageUrl).getScaledInstance(largeur, hauteur, Image.SCALE_DEFAULT);*/
	  	
		estUneCible=b;
		courbe = p;
		
		hauteur = (int) (h*courbe.getHeight());
		largeur = (int) (l*courbe.getWidth());
        
		positionX=(int)(Math.random()*(courbe.getWidth()-largeur-1));
		
		if(positionX<positionMin){ // pour éviter que la cible vienne contre l'origine, sinon c'est un peu trop facile de gagner
			positionX=positionMin;
		}
		
		if(!estUneCible){
			positionY=(int)(Math.random()*(courbe.getHeight()-hauteur-100));
		}else{
			positionY=courbe.getHeight()-hauteur;
		}	
	}
	
	/*public void dessine(Graphics g){
		g.drawImage(imageCible, positionX, positionY, null);
	}*/
	
	
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
	
	public boolean touche(int x, int y){
		boolean b;
		b = (x > positionX && x < (positionX + this.largeur) && y == positionY );		// si pb avec le '==' tester un encadrement mais entre positionY et positionY+vitesse ; on aura le problème juste dans l'angle...
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
	
	public void setPositionX(int x){
		this.positionX=x;
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
