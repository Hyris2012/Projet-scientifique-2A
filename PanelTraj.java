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
 import java.awt.*;
 import java.awt.Graphics;
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseListener;


public class PanelTraj extends JPanel implements ActionListener, MouseListener {
	
	
	private Balle balle;
	//private ImageIcon fond;
	private Timer time;
	private long tpsIni; // temps zéro pris pris à partir de l'heure system
	private ArrayList<Double> Xparcourus;
	private ArrayList<Double> Yparcourus;
	private Vecteur flecheInit; // définira le vecteur pour lancer la balle 
	
	public PanelTraj() {
		super();
		
		
		time = new Timer (1, this);
	
		balle = new Balle();
		
		Xparcourus = new ArrayList<Double>();
		Yparcourus = new ArrayList<Double>();
		
		addMouseListener(this);
		
	}
	
	public String toString(){
		return ("je suis une String");
		//juste pour le principe
	}
	
	public void lancerBalle(Balle balle){
		this.balle = balle;
		tpsIni=System.currentTimeMillis();
		time.start();
	}
	
	
	public void actionPerformed(ActionEvent e){//lié au timer
	
		if (e.getSource()==time){
			if(!atterrie()){
				Xparcourus.add(balle.getPolynome().getValeurX().get((int)(System.currentTimeMillis()-tpsIni)));
				Yparcourus.add(balle.getPolynome().getValeurY().get((int)(System.currentTimeMillis()-tpsIni)));
				repaint();
			}else{
				time.stop();
				new FenetreFinJeu("NOM FENETRE" , balle.getPolynome().toString());	// la fenetre affiche l'altitude max atteinte et la longueur parcourue
			}
		}
	}
	
	public void mouseExited(MouseEvent e){
		
	}
	
	public void mouseEntered(MouseEvent e){
		
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	public void mousePressed(MouseEvent e){
		
	}
	
	public void mouseClicked(MouseEvent e){
		APoint departFleche=new APoint(0,this.getHeight());
		APoint pointeFleche= new APoint(e.getX(),e.getY());
		flecheInit=new Vecteur (departFleche, pointeFleche);
		
		repaint(); //tout marche jusque là
		lancerBalle(new Balle(1.0,1.0,flecheInit));
		repaint();
	}
	
	public void drawArrowLine(Graphics g) {
		int x1= (int) flecheInit.getBase().x; // valeur abscisse base flèche 
		int y1= (int) flecheInit.getBase().y; // ordonnée base flèche
		int x2= (int) flecheInit.getPointe().x; // abscisse pointe
		int y2= (int) flecheInit.getPointe().y; // ordonnée pointe
		final int d=12, h=5; // hauteur et largeur de la petite pointe au bout de la flèche
		
		int dx = x2 - x1;
		int dy = y2 - y1;
		double xm = flecheInit.getModule() - d;
		double xn = xm, ym = h, yn = -h, var; // on les déclare à la suite les indices n et m servent à stocket les coordonnées de la flèche du bout pour pouvoir la remplir ensuite
		double sin = dy / flecheInit.getModule();
		double cos = dx / flecheInit.getModule();

		var = xm*cos - ym*sin + x1;
		ym = xm*sin + ym*cos + y1;
		xm = var;

		var = xn*cos - yn*sin + x1;
		yn = xn*sin + yn*cos + y1;
		xn = var;

		int[] xpoints = {x2, (int) xm, (int) xn};
		int[] ypoints = {y2, (int) ym, (int) yn};

		g.drawLine(x1, y1, x2, y2);
		g.fillPolygon(xpoints, ypoints, 3);
	}
	public void paint(Graphics g){
			super.paint(g);
		 // dessine la trajectoire jusqu'à --> tps 
			g.setColor(Color.black);
			Polynome p=new Polynome(-1,3,400);
			ArrayList<Double> YAffiches = new ArrayList<Double>();
			for(int i=0; i<p.getValeurY().size(); i++){ // on transforme les valeurs de y pour afficher la courbe dans le bon sens (et pas renversée)
				YAffiches.add(getHeight()-p.getValeurY().get(i));
			}
			g.drawPolyline(conversionTableau(p.getValeurX()), conversionTableau(YAffiches), p.getValeurX().size());
			//g.drawPolyline(conversionTableau(Xparcourus), conversionTableau(Yparcourus), Xparcourus.size());
			if(flecheInit!=null){
				drawArrowLine(g);
			}
		}
	
	public boolean atterrie(){
		boolean b = true;
		// doit déterminer si la balle a atteri 
		if(Xparcourus.size()>0){
			b = (Xparcourus.get(Xparcourus.size()-1) >= balle.getPolynome().getRacines()[1]);		
		}else{
			b=false;
		}
		return b; 
	}
	
	public int[] conversionTableau(ArrayList<Double> liste){
		
		int[] t = new int[liste.size()];
		for (int i = 0 ; i < t.length ; i ++){
			t[i] = liste.get(i).intValue();
		}
		return t;
	}
	
	
}






