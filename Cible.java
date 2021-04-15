/*
 * 
 * 
 */
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Cible implements ActionListener {	//extends JPanel
	
	public int hauteur;
	public int largeur; 
	public PanelTraj courbe;
	public int positionX = 0;
	public Timer time = new Timer(1, this);
	int sens;
	
	
	public Cible(double h, double l, PanelTraj p){
		//super();
		//setBounds(0,0, p.getWidth(), p.getHeight());
		//setLayout(null);
		courbe = p;
		hauteur = (int) (h*courbe.getHeight());
		largeur = (int) (l*courbe.getWidth());
		
	}
	//paint
	/*public void paint(Graphics g){
		g.fillRect(positionX, (int)courbe.getHeight()-hauteur,largeur, hauteur);
		
		
	}*/
	//deltaX = la vitesse de deplacement de la cible
	
	public void deplaceX(){
		
		if (positionX == 0){
			sens = 1;
		}else if(positionX == courbe.getWidth()-largeur){
			sens = -1;
		}
		positionX = positionX + sens*4;
	}
	
	public boolean touche(int x, int y){
		boolean b;
		b = (x >= positionX && x <= (positionX + this.largeur) && y>= (courbe.getHeight()-this.hauteur) && y < courbe.getHeight());
		return b;
	}
		
	public void actionPerformed(ActionEvent e) {
		deplaceX();
		courbe.repaint();
		
	}
}
