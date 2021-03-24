/*
 *
 * qq problèmes :
 * histoire d'échelle pour que tout soit toujours contenu entier dans le panel
 * 
 * il faut avoir accès aux arraylist de la balle -> passer en public ou écrire un get 
 * il faut connaitre la racine positive du polynôme, càd là où elle touche le sol (attribut nommé 'atterissage' ici en attendant)
 * 
 * 
 * 
 * 
 */

 
 import java.awt.event.*;
 import java.awt.Color;
 import java.util.ArrayList;
 import javax.swing.*;
 import java.awt.Graphics;


public class PanelTraj extends JPanel implements ActionListener {
	
	
	private Balle balle;
	//private ImageIcon fond;
	private Timer time;
	private int tps;
	private ArrayList<Integer> Xparcourus;
	private ArrayList<Integer> Yparcourus;
	
	public PanelTraj(Balle balle) {
		super();
		setLayout(null);
		setBounds(50, 50, 500, 700);
		setBackground(Color.black);
		
		time = new Timer (100, this);
	
		balle = new Balle();
		
		Xparcourus = new <Double> ArrayList();
		Yparcourus = new <Double> ArrayList();
		
	}
	
	public String toString(){
		//juste pour le principe
	}
	
	public void lancerBalle(Balle balle){
		this.balle = balle;
		time.start();
	}
	
	public void paint(Graphics g){
		
		// appelé lors de l'appui sur le bouton 'lancer' -> càd dans actionPerformed de FenetreJeu 
		
		// dessine la trajectoire jusqu'à --> tps 
		g.setColor(Color.black);
		drawPolyline(balle.getValeurX().toArray(), balle.getValeurY().toArray());
		
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(!atterrie()){
			Xparcourus.add((int) balle.getValeurX().get(tps));
			repaint();
		}else{
			time.stop();
			new FenetreFinJeu(balle.aterrissage, balle.max);	// en supposant que la fenetre affiche l'altitude max atteinte et la longueur parcourue, qui seraient tout deux attributs de balle 
		}
		
	}
	
	public boolean atterrie(){
		boolean b = true;
		// doit déterminer si la balle a atteri 
		// b = ((Xparcourus.get(Xparcourus.size()-1) >= balle.atterrissage));		// attribut aterrissage supposé 
		return b; 
		
	}
	
}






