/** Classe permettant la création du Panel dans lequel notre jeu va se dérouler
 * correspond au Panel mis en place dans les deux modes de jeu, qu'il faudra différencier dans les classes filles 
 * Classe mère des classes PanelTrajJeu et PanelTrajScienti
 * 
 * 
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
	
	
	/** Constructeur du PanelTraj dans lequel la trajectoire et les différentes options complémentaires, suivant le mode de jeu choisi, vont évoluer 
 * @param fen 	permet d'agir sur la fenetre mère qui lui est associé
 * @param x 	position en x dans le Panel au début du jeu 
 * @param y 	position en y dans le Panel au début du jeu 
 * @param l 	largeur du PanelTraj choisie 
 * @param h 	hauteur du PanelTraj choisie 
*/ 
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
	
	/**Initialise la balle et lance la trajectoire 
 * Méthode appelée à l'appui du bouton 'Jouer'  
 * Ne renvoie rien
 * name : lancerBalle 
 * @param balle 	caractérise l'objet choisi à faire évoluer dans le panel 
*/
	public void lancerBalle(Balle balle){
		this.balle = balle;
		dernierXAffiche = 0;
		time.start();
	}
	
	/**Détermine si la balle a touché le sol et que la trajectoire est terminée 
 * Méthode permettant d'arrêter le timer   
 * name : atterrie 
 * @param balle 	caractérise l'objet choisi à faire évoluer dans le panel 
 * @return b 		représente le fait que la balle soit au niveau du sol
*/
	public boolean atterrie(){
		boolean b = false; 
		if(XParcourus.size() > 0){
			b = (XParcourus.get(XParcourus.size()-1) >= balle.getPolynome().getRacines()[1]);		
		}	
		return b; 
	}
	
/**Conversion d'arrayList Double en tableau de int
 * Méthode mise en place pour l'utilisation de la méthode drawPolyline   
 * name : conversionTableau
 * @param liste 	liste de double à convertir 
 * @return t 		liste de int utilisable par la méthode drawPolyline 
*/
	public int[] conversionTableau(ArrayList<Double> liste){
		
		int[] t = new int[liste.size()];
		for (int i = 0 ; i < t.length ; i ++){
			t[i] = liste.get(i).intValue();
		}
		return t;
	}
	
	/** Méthode permettant de dessiner la portion de trajectoire où la balle se situe  
 * Ne renvoie rien 
 * name : paintComponent 
 * @param g		objet graphique permettant de représenter la trajectoire 
*/
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
	
	/**Permet de créer la ligne se terminant par une flèche servant à viser
 * Ne renvoie rien  
 * name : drawArrowLine
 * @param g		objet graphique permettant de représenter la trajectoire 
*/
	public void drawArrowLine(Graphics g) {
		
		final int D = 12;
		final int H = 10; 
		
		// sommet bas : pointe du vecteur qui part de la pointe de flecheInit, fait un angle de PI/2, et avance de la moitié de la largeur 
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
 * Permet de créer les listes de points avec leurs coordonnées an abscisses et en ordonnées 
 * On ajoute un point à la liste suivant x et y a chaque intervalle de temps du timer et tant que la trajectoire n'est pas terminée 
 * ne renvoie rien 
 * name : actionPerformed 
 * @param  e 	ActionEvent elle est déclenchée dès qu'une action précise est réalisée 
*/
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
				}
			}else{
				time.stop();
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
	
	/**Créée une balle et initialise une trajectoire avec les paramètres choisis et la taille du vecteur dessiné par l'utilisateur 
 * Ne renvoie rien  
 * name : mouseClicked 
 * @param e		MouseEvent est un évènement qui se déclenche dès qu'une action précise est réalisée, ici dès qu'on clic avec la souris  
*/
	public void mouseClicked(MouseEvent e){
		if(flecheSuitSouris){ // si flecheSuitSouris est true, la trajectoire est "libre", si on a cliqué le vecteur reste fixe
			if (fond != null) {	// normalement on devrait pouvoir l'enlever, puisque la condition est déjà dans le paint
				reInit();
				//APoint pointeFleche = new APoint(e.getX(),this.getHeight()-e.getY());
				//flecheInit = new Vecteur (departFleche, pointeFleche);
				
				lancerBalle(new Balle(flecheInit, this.getWidth(), this.getHeight(), pesanteurChoisie));
				//reInit();
				flecheSuitSouris = false;
			}
		}
	}
	
	/**Permet de réinitialiser les listes de points lorsqu'on va débuter un nouveau lancer 
 * Ne prend pas de paramètre en compte et ne renvoie rien
 * name : reInit 
*/
	public void reInit(){
		YParcourus.clear();
		XParcourus.clear();
		dernierXAffiche = 0;
	}
	
	/**Permet de créer le vecteur fictif qui serait dessiné lors du clic 
 * Ne renvoie rien  
 * name : mouseMoved
 * @param e		MouseEvent est un évènement qui se déclenche dès qu'une action précise est réalisée, ici dès qu'on déplace la souris 
*/
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
		
/**
 * Accesseur en lecture du fond actif dans la fenetre 
 * Ne prend pas de paramètre en compte  
 * name : getFond 
 * @return Decor  		décor choisi pour la partie  
 */	
	public Decor getFond(){
		return fond ;
	}
}






