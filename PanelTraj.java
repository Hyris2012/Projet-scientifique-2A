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
	private int dernierXAffiche; // l'abscisse de l'extrémité de la courbe affichée à un instant donné
	private ArrayList<Double> XParcourus;
	private ArrayList<Double> YParcourus;
	private Vecteur flecheInit; // définira le vecteur pour lancer la balle 
	
	public PanelTraj() {
		super();
		
		
		time = new Timer (1, this);
		
		XParcourus = new ArrayList<Double>();
		YParcourus = new ArrayList<Double>();
		
		addMouseListener(this);
		
	}
	
	public String toString(){
		return ("je suis une String");
		//juste pour le principe
	}
	
	public void lancerBalle(Balle balle){
		this.balle = balle;
		dernierXAffiche=0;
		//dernierXAfficheIni=System.currentTimeMillis();
		time.start();
	}
	
	
	public void actionPerformed(ActionEvent e){//lié au timer
	
		if (e.getSource()==time){
			if(dernierXAffiche<909){
				if(!atterrie()){
					XParcourus.add(balle.getPolynome().getValeurX().get(dernierXAffiche));
					YParcourus.add(balle.getPolynome().getValeurY().get(dernierXAffiche));
					dernierXAffiche++;
					repaint();
				}else{
					time.stop();
					new FenetreFinJeu("NOM FENETRE" , balle.getPolynome().toString());	// la fenetre affiche l'altitude max atteinte et la longueur parcourue
				}
			}else{
				time.stop();
				new FenetreFinJeu("Raté, essaie encore" , "la balle a atteri trop loin");
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
		APoint departFleche=new APoint(0,0);
		APoint pointeFleche= new APoint(e.getX(),this.getHeight()-e.getY());
		flecheInit=new Vecteur (departFleche, pointeFleche);
		System.out.println(getWidth());
		//repaint(); //tout marche jusque là
		lancerBalle(new Balle(1.0,1.0,flecheInit,this.getWidth()));
		YParcourus.clear();
		dernierXAffiche=0;
		repaint();
	}
	
	public void drawArrowLine(Graphics g) {
		int x1= (int) flecheInit.getBase().x; // valeur abscisse base flèche 
		int y1= (int) (this.getHeight()-flecheInit.getBase().y); // ordonnée base flèche
		int x2= (int) flecheInit.getPointe().x; // abscisse pointe
		int y2= (int) (this.getHeight()-flecheInit.getPointe().y); // ordonnée pointe
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
		 // dessine la trajectoire jusqu'à --> dernierXAffiche 
			g.setColor(Color.black);
			//Polynome p=new Polynome(-0.2,42,0);
			if(balle!=null){
				
				int[] YAffiches = new int[/*(int)balle.getPolynome().getRacines()[1]+1*/YParcourus.size()];
				for(int i=0; i</*(int)balle.getPolynome().getRacines()[1]+1*/YParcourus.size(); i++){ // on transforme les valeurs de y pour afficher la courbe dans le bon sens (et pas renversée)
					YAffiches[i]=(this.getHeight()-YParcourus.get(i).intValue());
				}
				int[] XAffiches = new int[YAffiches.length];
				for(int i=0; i<YAffiches.length; i++){ 
					XAffiches[i]=i;
				}
				
				g.drawPolyline(XAffiches, YAffiches, YAffiches.length);
				/*System.out.println("la hauteur de la fenetre est "+this.getHeight());
				for(int i=0; i<YAffiches.length; i++){
					System.out.println(YAffiches[i]);
				}*/
				//g.drawPolyline(conversionTableau(XParcourus), conversionTableau(YParcourus), XParcourus.size());
			}
			if(flecheInit!=null){
				drawArrowLine(g);
			}
		}
	
	public boolean atterrie(){
		boolean b = true;
		// doit déterminer si la balle a atteri 
		if(XParcourus.size()>0){
			b = (XParcourus.get(XParcourus.size()-1) >= balle.getPolynome().getRacines()[1]);		
		}else{
			b=false;
		}
		return b; 
	}
	
	/*public int[] conversionTableau(ArrayList<Double> liste){
		
		int[] t = new int[liste.size()];
		for (int i = 0 ; i < t.length ; i ++){
			t[i] = liste.get(i).intValue();
		}
		return t;
	}*/
	
	
}






