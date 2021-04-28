import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;


public class PanelTraj extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

	// import des images 
	Toolkit T =  Toolkit.getDefaultToolkit();
	protected Decor fond;
	protected FenetreMere fen;
	
	protected Balle balle;
	
	protected Timer time;
	protected int dernierXAffiche; // l'abscisse de l'extrémité de la courbe affichée à un instant donné
	protected ArrayList<Double> XParcourus;
	protected ArrayList<Double> YParcourus;
	protected Vecteur flecheInit; // définira le vecteur pour lancer la balle 
	protected APoint departFleche=new APoint(0,0);
	protected int[] X;
	protected int[] Y;
	protected int vitesseAffichage;
	protected boolean flecheSuitSouris = false;
	protected double pesanteurChoisie=9.81;
	
	
	// constructeur 
	
	public PanelTraj(FenetreMere fen, int x, int y, int l, int h) {
		super();
		this.fen = fen;
		setBounds(x, y, l, h);
		
		vitesseAffichage=1;
		
		time = new Timer (1, this);
		
		XParcourus = new ArrayList<Double>();
		YParcourus = new ArrayList<Double>();
		
		addMouseListener(this);
		
		addMouseMotionListener(this);
		fond = new Decor();
	
	}
	
	public String toString(){
		return ("Je suis le JPanel d'affichage de la trajectoire du splendide projet 'Trajectory Manager' :) ");
		//juste pour le principe
	}
	
	// méthode appelée à l'appui du bouton 'Jouer' ; initialise la balle et lance la trajectoire 
	public void lancerBalle(Balle balle){
		this.balle = balle;
		dernierXAffiche = 0;
		time.start();
	}
	
	// déterminé si la balle a touché le sol ; pour arrêter le timer 
	public boolean atterrie(){
		boolean b = false; 
		if(XParcourus.size() > 0){
			b = (XParcourus.get(XParcourus.size()-1) >= balle.getPolynome().getRacines()[1]);		
		}	
		return b; 
	}
	
	// conversion d'arrayList Double en tableau de int pour les besoins de la méthode drawPolyline
	public int[] conversionTableau(ArrayList<Double> liste){
		
		int[] t = new int[liste.size()];
		for (int i = 0 ; i < t.length ; i ++){
			t[i] = liste.get(i).intValue();
		}
		return t;
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g); 
		
		// dessin de la portion de trajectoire où on en est 
		
		g.drawImage(fond.getImageChoisie(), 0, 0, null);
		g.setColor(fond.getCouleurChoisie());
		
	
 		if(balle != null && fond !=null && XParcourus.size() > 0 && YParcourus.size() > 0 ){
			X = conversionTableau(XParcourus);
			Y = conversionTableau(YParcourus);
			g.drawPolyline(X, Y, X.length);
		}
		
		if(flecheInit != null && fond!=null){
			drawArrowLine(g);
		}
		
	}
	
	
	// ************* méthodes utiles au paint *******************
	
	
	public void drawArrowLine(Graphics g) {
		
		final int d = 12, h = 10; 
		
		// sommet bas : pointe du vecteur qui part de la pointe de flecheInit, fait un angle de PI/2, et avance de la moitié de la largeur 
		APoint sommetBas = (new Vecteur(flecheInit.getPointe(), h/2, flecheInit.getArgument() - Math.PI/2)).getPointe();	
		APoint sommetHaut = (new Vecteur(flecheInit.getPointe(), h/2, flecheInit.getArgument() + Math.PI/2)).getPointe();	
		APoint sommetPointe = (new Vecteur(flecheInit.getPointe(), d, flecheInit.getArgument())).getPointe();


		int[] xpoints = {(int)sommetBas.x, (int)sommetHaut.x, (int) sommetPointe.x};
		int[] ypoints = {(int) (this.getHeight() - sommetBas.y), (int) (this.getHeight() - sommetHaut.y), (int) (this.getHeight() - sommetPointe.y)};

		g.drawLine((int) flecheInit.getBase().x, (int) (this.getHeight() - flecheInit.getBase().y), (int) flecheInit.getPointe().x, (int) (this.getHeight() - flecheInit.getPointe().y));
		g.fillPolygon(xpoints, ypoints, 3);
	}
	
	
	public void actionPerformed(ActionEvent e){ 
	
		if (e.getSource() == time){
			if(dernierXAffiche < this.getWidth()){
				 
				if(!atterrie()){
				//	for(int i=0; i<vitesseAffichage; i++){
						XParcourus.add(balle.getPolynome().getValeurX().get(dernierXAffiche));
						YParcourus.add(balle.getPolynome().getValeurY().get(dernierXAffiche));
						dernierXAffiche=dernierXAffiche+vitesseAffichage;
						repaint();
				//	}
				}else{
					time.stop();

					//new FenetreFinJeu("L'OBJET A ATTERRI" , balle.toString());	// la fenetre affiche l'altitude max atteinte et la longueur parcourue
				}
			}else{
				time.stop();
				//new FenetreFinJeu("Rate, essaie encore !" , "L'objet a atteri trop loin.");
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
		if(flecheSuitSouris){ // si flecheSuitSouris est true, la trajectoire est "libre", si on a cliqué le vecteur reste fixe
			if (fond != null) {	// normalement on devrait pouvoir l'enlever, puisque la condition est déjà dans le paint
				reInit();
				//APoint pointeFleche = new APoint(e.getX(),this.getHeight()-e.getY());
				//flecheInit = new Vecteur (departFleche, pointeFleche);
				
				lancerBalle(new Balle(1.0, 1.0, flecheInit, this.getWidth(), this.getHeight(), pesanteurChoisie));
				//reInit();
				flecheSuitSouris = false;
				
				//repaint();	// on le fait dans les classes filles our faire une condition sur imageObj dans PanelTrajJeu
			}
		}
	}
	
	public void reInit(){
		YParcourus.clear();
		XParcourus.clear();
		dernierXAffiche = 0;
	}
	
	public void mouseMoved(MouseEvent e) {
		if(flecheSuitSouris){
			APoint pointeFleche;
			if(departFleche.y-(this.getHeight() - e.getY())<0){ // pour bloquer l'affigage du vecteur et faire en sorte qu'il n'aille pas vers le bas 
			pointeFleche = new APoint(e.getX(),this.getHeight() - e.getY());
			
			}else{
			pointeFleche = new APoint(e.getX(),departFleche.y);
			}
		
			flecheInit = new Vecteur (departFleche, pointeFleche);
			repaint();
		}
		
	}
	public void mouseDragged(MouseEvent e) {
		
	}
	
	//accesseur en lecture de panelTraj
	public Decor getFond(){
		return fond ;
	}
}






