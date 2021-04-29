/**
 * Cette classe représente un conteneur permettant le déroulement d'une partie de Trajectory Manager en mode jeu.
 */
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
/**
 * Cette classe représente un conteneur permettant le déroulement d'une partie de Trajectory Manager en mode jeu.
 */

public class PanelTrajJeu extends PanelTraj{
	
	private FenetreJeu fenJ;
	private Decor espace;
	private Decor savane;
	private Decor jungle;
	private Decor bobLeponge;
	
	private final Image POMME_DOUCHE = T.getImage("pomme_de_douche.png");
	private final Image GIRAFE = T.getImage("girafe.png");
	private final Image BARBECUE = T.getImage("barbecue.png");
	private final Image ANANAS = T.getImage("ananas.png");
	private final Image PHOTOCOPIEUR = T.getImage("photocopieur.png");
	
	private final Image HARPE = T.getImage("harpe.png");
	private final Image BUS = T.getImage("bus_a_imperiale.png");
	private final Image GRILLE_PAIN = T.getImage("grille_pain.png");
	private final Image PHEDRE = T.getImage("phedre.png");
	
	private final Image PICS_DROITE;
	private final Image PICS_GAUCHE;
	
	private Image imageObj;
	private Image imageObstacle;
	private Cible cible;
    private Cible obstacle;
	
	/**
 * Constructeur de la classe qui permet de créer le conteneur de la fenetreJeu  
 * @param fenJ 	fenetreJeu qui est liée à la partie en cours 
 * @param x 	position en x dans le Panel au début du jeu
 * @param y 	position en y dans le Panel au début du jeu
 * @param l 	largeur du PanelTraj choisie 
 * @param h 	hauteur du PanelTraj choisie
 */	
	public PanelTrajJeu(FenetreJeu fenJ, int x, int y, int l, int h){
		super(fenJ, x, y, l, h);
		this.fenJ = fenJ;
		
		cible = new Cible (0.05, 0.2, this, true);
        obstacle = new Cible (0.12, 0.08, this, false);
        
        //initialisation des pics sur la cible
        PICS_DROITE = T.getImage("pics_cible_droite.png").getScaledInstance((int) (this.getWidth()*0.01), cible.getHauteurCible(), Image.SCALE_DEFAULT);
        PICS_GAUCHE = T.getImage("pics_cible_gauche.png").getScaledInstance((int) (this.getWidth()*0.01), cible.getHauteurCible(), Image.SCALE_DEFAULT);
        
		//initialisation des decors
		espace = new Decor(new AePlayWave("space.wav"), Color.white, T.getImage("./espace.jpg").getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		jungle = new Decor(new AePlayWave("mowgli.wav"), Color.yellow, T.getImage("./jungle.jpg").getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		savane = new Decor(new AePlayWave("lion.wav"), Color.red, T.getImage("./savane.jpg").getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		bobLeponge = new Decor(new AePlayWave("eponge.wav"), Color.black, T.getImage("./bobLeponge.png").getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));	
	}
	
	/** Méthode permettant de déterminer si la balle a atterrie 
 *  Trajectoire atteint le bord inférieur du panel ou non
 *  Ne prend pas de parametre en compte et ne renvoie rien 
 *  name : atterrie 
 */
	
	public boolean atterrie(){
		boolean b = super.atterrie();
		// tentative infructueuse pour que l'image s'arrête quand c'est le bas qui touche et non pas le centre
		/*boolean b = false; 
		if(XParcourus.size() > 0){
			//b = (XParcourus.get(XParcourus.size()-1) >= (balle.getPolynome().getRacines()[1] - imageObj.getHeight(null)/2));
			b = (YParcourus.get(YParcourus.size()-1) >= (this.getHeight() - imageObj.getHeight(null)/2));
		}*/	
		
		return b;
	}
	
	
	/**********************************  PAINT  **************************************/
	/** Rédéfinition de la méthode paint héritée de PanelTraj 
 *  Elle dessine tous les objets graphiques dans le panel : la cible et l'obstacle
 *  Ne renvoie rien 
 *  name : paintComponent 
 *  @param g		objet graphique permettant de représenter tous les éléments graphiques voulus 
 */
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.fillRect(cible.getPositionX(), (int) this.getHeight() - cible.getHauteurCible(), cible.getLargeurCible(), cible.getHauteurCible());		// dans l'idéal ça se passerait dans Cible ça non ? 
		//cible.dessine(g);
		g.drawImage(PICS_GAUCHE, cible.getPositionX() - (int) (this.getWidth()*0.01), cible.getPositionY(),null);
		g.drawImage(PICS_DROITE, cible.getPositionX() + cible.getLargeurCible(), cible.getPositionY(),null);
		
		if(cible.getVitesseCible()!=0){ // pour que l'obstacle ne s'affiche pas en mode débutant
			g.drawImage(imageObstacle, obstacle.getPositionX(), obstacle.getPositionY(),null);
		}
		
		if(balle != null && XParcourus.size() > 0 && YParcourus.size() > 0 && fond !=null){
			g.drawImage(imageObj, X[X.length - 1]- imageObj.getWidth(null)/2, Y[Y.length - 1] - imageObj.getHeight(null)/2, null);
		}
	}
	
	/**
 * Redéfinition de l'implémentation de l'interface ActionListener héritée de PanelTraj. 
 * Gère les évènements du timer spécifiques au mode jeu : évolution des vies et du score en fonction de l'issue de la trajectoire de la balle.
 * Ne renvoie rien 
 * name : actionPerformed 
 * @param  e 	ActionEvent elle est déclenchée dès qu'une action précise est réalisée 
 */
	
	public void actionPerformed(ActionEvent e){ //lié au timer
		super.actionPerformed(e);
		
		if (e.getSource() == time){
			
			// cas où le joueur PERD son lancer
			
			// la balle sort de la fenetre en largeur
			if(dernierXAffiche >= this.getWidth()){	
				fenJ.perdVies(1);
				fenJ.victoireOuDefaite();
				finDuLancer();
				
			}else if(YParcourus.size() > 0 && dernierXAffiche > 0){		 // condition anti-exception : les arraylist de la trajectoire ne doivent pas être nulles 
				
				// la balle touche l'obstacle
				if(cible.getVitesseCible()!=0 && obstacle.toucheObstacle(dernierXAffiche, YParcourus.get((int)(dernierXAffiche/vitesseAffichage)-1).intValue())){		// tentative infructueuse pour que l'image s'arrête quand c'est le bas qui touche et non pas le centre : + imageObj.getHeight(null)/2
					fenJ.perdVies(1);
					fenJ.victoireOuDefaite();
					finDuLancer();
					return;
					
				// cas où le joueur touche le côté de la cible au lieu du dessus
				}else if(cible.toucheCoteCible(dernierXAffiche, YParcourus.get((int)(dernierXAffiche/vitesseAffichage)-1).intValue())){		
					fenJ.perdVies(1);
					fenJ.victoireOuDefaite();
					finDuLancer();
					
				// cas où le joueur GAGNE son lancer : il touche la cible
				}else if(cible.toucheCible(dernierXAffiche, YParcourus.get((int)(dernierXAffiche/vitesseAffichage)-1).intValue())){		
					fenJ.mAjScore(300);
					cible.setPosition();
					obstacle.setPosition();
					fenJ.victoireOuDefaite();
					finDuLancer();
					
				// la balle touche sol sans avoir touché la cible  
                }else if(atterrie()){
					fenJ.perdVies(1);
					fenJ.victoireOuDefaite();
					finDuLancer();	
				}
				// remarque : les return permettent de sortir de la méthode lorsqu'UNE des conditions et vérifiée sans tester les suivantes 
			}
		}	
	}
	
	/**
 * Méthode exécutant toutes les réinitialisations nécessaires lorsqu'un lancer est considéré terminé et doit laisser place à un autre
 * Ne prend en compte aucun parametre et ne renvoie rien 
 */
	
	public void finDuLancer(){
		time.stop(); 
		cible.getTimerCible().stop();
        obstacle.getTimerCible().stop();
		Outils.pause(750);
		this.cible.getTimerCible().start();
        this.obstacle.getTimerCible().start();
		this.flecheSuitSouris = true;
		this.reInit();
		this.repaint();
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
		super.mouseClicked(e);
		if (imageObj == null) {	// normalement on devrait pouvoir l'enlever, puisque la condition est déjà dans le paint
			repaint();
		}
	}	
	
	/**
 * Methode permettant de définir la vitesse d'affichage en fonction de la difficulté de jeu choisie 
 * différenciation des différents cas par l'utilisation d'un switch/case 
 * Ne renvoie rien 
 * name : setVitesseAffichage
 * @param s 	difficulté choisie par l'utilisateur 
 */
	public void setVitesseAffichage(String s){
		switch (s){
        case "Débutant" :
			this.vitesseAffichage = 2;
			break;
        case "Intermédiaire":
            this.vitesseAffichage = 3;
            break;
        case "Expert":
            this.vitesseAffichage = 4;
            break;
        default :
            this.vitesseAffichage = 2;
            break;
        }
	}
	
	/**
 * Permet de choisir l'image de fond du décor  
 * Ne renvoie rien 
 * name : setDecor
 * @param decor 	défini le choix du fond du décor 
 */
	public void setDecor(String decor){
        switch (decor){
        case "Espace" :
			fond = espace;
			break;
        case "Jungle":
            fond = jungle;
            break;
        case "Savane":
            fond = savane;
            break;
        case "Bob l'eponge":
            fond = bobLeponge;
            break;
        default :
            fond = espace;
            break;
        }
    }
    
    /**
  * Permet de choisir la balle associée au décor 
  * Ne renvoie rien 
  * name : setObjet
  * @param objet 	défini le choix de l'objet 
 */
    public void setObjet(String objet){
		switch (objet){
        case "Girafe" :
			imageObj = GIRAFE.getScaledInstance((int) (this.getWidth()*0.1), (int) (this.getHeight()*0.18), Image.SCALE_DEFAULT);
			break;
        case "Pommeau de douche":
            imageObj = POMME_DOUCHE.getScaledInstance((int) (this.getWidth()*0.08), (int) (this.getHeight()*0.17), Image.SCALE_DEFAULT);
            break;
        case "Barbecue":
            imageObj = BARBECUE.getScaledInstance((int) (this.getWidth()*0.07), (int) (this.getHeight()*0.18), Image.SCALE_DEFAULT);
            break;
        case "Ananas":
            imageObj = ANANAS.getScaledInstance((int) (this.getWidth()*0.05), (int) (this.getHeight()*0.20), Image.SCALE_DEFAULT);
            break;
        case "Photocopieur":
            imageObj = PHOTOCOPIEUR.getScaledInstance((int) (this.getWidth()*0.08), (int) (this.getHeight()*0.16), Image.SCALE_DEFAULT);
         break;
        default :
            imageObj = GIRAFE.getScaledInstance((int) (this.getWidth()*0.1), (int) (this.getHeight()*0.18), Image.SCALE_DEFAULT);
            break;
        }
	}
	
	/**
  * Gère le choix de l'obstacle en fonction du fond choisi 
  * Ne renvoie rien 
  * name : setObstacle 
  * @param decor 	fond qui a été choisi par l'utilisateur 
 */
	public void setObstacle(String decor){
		switch (decor){
        case "Espace" :
			imageObstacle = HARPE.getScaledInstance((int) (this.getWidth()*0.07), (int) (this.getHeight()*0.21), Image.SCALE_DEFAULT);
			break;
        case "Jungle":
            imageObstacle = BUS.getScaledInstance((int) (this.getWidth()*0.1), (int) (this.getHeight()*0.18), Image.SCALE_DEFAULT);
            break;
        case "Bob l'eponge":
            imageObstacle = PHEDRE.getScaledInstance((int) (this.getWidth()*0.07), (int) (this.getHeight()*0.21), Image.SCALE_DEFAULT);
            break;
        case "Savane":
            imageObstacle = GRILLE_PAIN.getScaledInstance((int) (this.getWidth()*0.1), (int) (this.getHeight()*0.18), Image.SCALE_DEFAULT);
            break;
        default :
            imageObstacle = HARPE.getScaledInstance((int) (this.getWidth()*0.1), (int) (this.getHeight()*0.18), Image.SCALE_DEFAULT);
            break;
        }
	}
	
	/**
 * Accesseur en lecture des caractéristiques de la cible
 * Ne prend pas de paramètre en compte 
 * name : getCible  
 * @return Cible  		caractéristiques de la cible  
 */	
	public Cible getCible(){
		return this.cible;
	}
    
    /**
 * Accesseur en lecture des caractéristiques de l'obstacle
 * Ne prend pas de paramètre en compte 
 * name : getObstacle 
 * @return Cible  		caractéristiques de l'obstacle   
 */	
    public Cible getObstacle(){
		return this.obstacle;
	}
	
	/**
 * Accesseur en lecture de la fenetreJeu active 
 * Ne prend pas de paramètre en compte 
 * name : getFenetreJeu 
 * @return FenetreJeu 	fenetre de jeu associée au PanelTrajJeu
 */	
	public FenetreJeu getFenetreJeu(){
		return this.fenJ;
	}
}
