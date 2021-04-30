/** 
 * Nom de la classe : PanelTraj
 * Permet la création du Panel dans lequel s'affichent les trajecoires dans les classes filles : PanelTrajJeu et PanelTrajScienti 
*/
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class PanelTraj extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

	// import des images 
	Toolkit T =  Toolkit.getDefaultToolkit();
	protected Decor fond;
	protected FenetreMere fen;
	
	// objet lancé
	protected Balle balle;
	
	protected Timer time;
	protected int dernierXAffiche; // l'abscisse de l'extrémité de la courbe affichée à un instant donné
	protected ArrayList<Double> XParcourus;
	protected ArrayList<Double> YParcourus;
	protected Vecteur flecheInit; // définira le vecteur pour lancer la balle 
	protected APoint departFleche=new APoint(0,0);
	protected int[] X; // Stocke les abscisses de la tarjectoire
	protected int[] Y; // Stocke les ordonnées de la trajectoire
	protected int vitesseAffichage;
	protected boolean flecheSuitSouris = false;
	protected double pesanteurChoisie=9.81;
	
	
	/**
	 *  Constructeur du PanelTraj dans lequel s'effectue la trajectoire 
	 * @param fen 	de type FenetreMere permet d'agir sur la fenetre mère qui lui est associé
	 * @param x 	int position en x du PanelTraj 
	 * @param y 	int position en y du PanelTraj
	 * @param l 	int largeur du PanelTraj
	 * @param h 	int hauteur du PanelTraj
	*/ 
	public PanelTraj(FenetreMere fen, int x, int y, int l, int h) {
		
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
	
	/**
	 * Méthode qui initialise la balle et lance la trajectoire 
	 * Ne renvoie rien
	 * Name : lancerBalle 
	 * @param balle 	de type Balle caractérise l'objet choisi à faire évoluer dans le panel 
	*/
	public void lancerBalle(Balle balle){
		this.balle = balle;
		dernierXAffiche = 0;
		time.start();
	}
	
	/**
	 * Méthode qui détermine si la balle a touché le sol et arrête le Timer  
	 * Name : atterrie 
	 * Ne prend pas de paramètre 
	 * @return b 		boolean true si la balle a atterri
	*/
	public boolean atterrie(){
		boolean b = false; 
		if(XParcourus.size() > 0){
			b = (XParcourus.get(XParcourus.size()-1) >= balle.getPolynome().getRacines()[1]);		
		}	
		return b; 
	}
	
	/**
	 * Méthode qui convertit une ArrayList de double en tableau de int   
	 * Name : conversionTableau
	 * @param liste 	ArrayList de double à convertir 
	 * @return t 		tableau de int utilisable par la méthode drawPolyline 
	*/
	public int[] conversionTableau(ArrayList<Double> liste){
		
		int[] t = new int[liste.size()];
		for (int i = 0 ; i < t.length ; i ++){
			t[i] = liste.get(i).intValue();
		}
		return t;
	}
	
	/**
	 *  Méthode redéfinissant paintComponent de JPanel 
	 * Permet d'afficher les éléments du panel
	 * Ne renvoie rien 
	 * Name : paintComponent 
	 * @param g		objet graphique permettant de représenter la trajectoire 
	*/
	public void paintComponent(Graphics g){
		super.paintComponent(g); 
				
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
		
	/* ************ Méthodes utiles au paintComponent ******************* */
	
	/**
	 * Méthode qui permet de créer et dessiner le vecteur pour viser
	 * Ne renvoie rien  
	 * Name : drawArrowLine
	 * @param g		objet de type Graphics 
	*/
	public void drawArrowLine(Graphics g) {
		
		final int D = 12;
		final int H = 10; 
		
		APoint sommetBas = (new Vecteur(flecheInit.getPointe(), H/2, flecheInit.getArgument() - Math.PI/2)).getPointe();	
		APoint sommetHaut = (new Vecteur(flecheInit.getPointe(), H/2, flecheInit.getArgument() + Math.PI/2)).getPointe();	
		APoint sommetPointe = (new Vecteur(flecheInit.getPointe(), D, flecheInit.getArgument())).getPointe();

		int[] xpoints = {(int)sommetBas.x, (int)sommetHaut.x, (int) sommetPointe.x};
		int[] ypoints = {(int) (this.getHeight() - sommetBas.y), (int) (this.getHeight() - sommetHaut.y), (int) (this.getHeight() - sommetPointe.y)};

		g.drawLine((int) flecheInit.getBase().x, (int) (this.getHeight() - flecheInit.getBase().y), (int) flecheInit.getPointe().x, (int) (this.getHeight() - flecheInit.getPointe().y));
		g.fillPolygon(xpoints, ypoints, 3);
	}
	
	/**
	 * Implémentation de l'interface ActionListener
	 * Remplit les listes de coordonnées de la trajectoire
	 * Ne renvoie rien 
	 * Name : actionPerformed 
	 * @param  e 	ActionEvent
	*/
	public void actionPerformed(ActionEvent e){ 
	
		if (e.getSource() == time){
			if(dernierXAffiche < this.getWidth()){				 
				if(!atterrie()){									
						XParcourus.add(balle.getPolynome().getValeurX().get(dernierXAffiche));
						YParcourus.add(balle.getPolynome().getValeurY().get(dernierXAffiche));
						dernierXAffiche=dernierXAffiche+vitesseAffichage;
						repaint();
				}else{
					time.stop();
				}
			}else{
				time.stop();
			}
		}
	}
	
	/**
	 * Redéfinission de la méthode mouseClicked de MouseListener
	 * Permet de créer un vecteur utilisé pour initialisé la trajectoire de l'objet 
	 * Ne renvoie rien  
	 * Name : mouseClicked 
	 * @param e		de type MouseEvent  
	*/
	public void mouseClicked(MouseEvent e){
		if(flecheSuitSouris){
			if (fond != null) {
				reInit();
				lancerBalle(new Balle(flecheInit, this.getWidth(), this.getHeight(), pesanteurChoisie));
				flecheSuitSouris = false; // on bloque le vecteur le temps du lancer
			}
		}
	}
	
	/**
	 * Méthode qui réinitialise les listes de points lorsqu'on débute un nouveau lancer 
	 * Ne prend pas de paramètre et ne renvoie rien
	 * Name : reInit 
	*/
	public void reInit(){
		YParcourus.clear();
		XParcourus.clear();
		dernierXAffiche = 0;
	}
	
	/**
	 * Redéfinission de la méthode mouseMoved de MouseMotionListener
	 * Crée un vecteur à chaque mouvement de la souris 
	 * Ne renvoie rien  
	 * Name : mouseMoved
	 * @param e		MouseEvent
	*/
	public void mouseMoved(MouseEvent e) {
		if(flecheSuitSouris){
			APoint pointeFleche = new APoint(e.getX(),this.getHeight() - e.getY());
			
			if(departFleche.y - (this.getHeight() - e.getY()) >= 0){ // pour bloquer l'affigage du vecteur et faire en sorte qu'il n'aille pas vers le bas 
			pointeFleche.y = departFleche.y;
			
			}if((departFleche.x - e.getX()) >= 0){ // pour éviter qu'on fasse un vecteur à 90°
			pointeFleche.x = pointeFleche.x+0.01;
			}
			
			flecheInit = new Vecteur (departFleche, pointeFleche);
			repaint();
		}		
	}	
		
	/**
	 * Accesseur en lecture du fond actif dans la fenetre 
	 * Ne prend pas de paramètre en compte  
	 * @return Decor  		décor choisi pour la partie  
	 */	
	public Decor getFond(){
		return fond ;
	}
	
	// Ci-après : méthodes non redéfinies des interfaces MouseListener et MouseMotionListener
	public void mouseExited(MouseEvent e){
	}
	
	public void mouseEntered(MouseEvent e){
	}
	
	public void mouseReleased(MouseEvent e){		
	}
	
	public void mousePressed(MouseEvent e){		
	}
	
	public void mouseDragged(MouseEvent e) {		
	}	
}






