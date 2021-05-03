/**
 * Nom de la classe : PanelTrajJeu, héritée de PanelTraj
 * Panel dans lequel sont affichés les éléments du jeu 
 */
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class PanelTrajJeu extends PanelTraj{
	
	private FenetreJeu fenJ;
	
	private Decor espace;
	private Decor savane;
	private Decor jungle;
	private Decor bobLeponge;
	
	private final Image POMME_DOUCHE = T.getImage("Images/pomme_de_douche.png");
	private final Image GIRAFE = T.getImage("Images/girafe.png");
	private final Image BARBECUE = T.getImage("Images/barbecue.png");
	private final Image ANANAS = T.getImage("Images/ananas.png");
	private final Image PHOTOCOPIEUR = T.getImage("Images/photocopieur.png");
	
	private final Image HARPE = T.getImage("Images/harpe.png");
	private final Image BUS = T.getImage("Images/bus_a_imperiale.png");
	private final Image GRILLE_PAIN = T.getImage("Images/grille_pain.png");
	private final Image PHEDRE = T.getImage("Images/phedre.png");
	
	private final Image PICS_DROITE;
	private final Image PICS_GAUCHE;
	
	private Image imageObj;
	private Image imageObstacle;
	private Cible cible;
    private Cible obstacle;
	
	/**
	 * Constructeur de la classe créant le PanelTrajJeu 
	 * @param fenJ 	fenetreJeu qui est liée à la partie en cours 
	 * @param x 	position en x du PanelTrajJeu
	 * @param y 	position en y du PanelTrajJeu
	 * @param l 	largeur du PanelTrajJeu
	 * @param h 	hauteur du PanelTrajJeu
	 */	
	public PanelTrajJeu(FenetreJeu fenJ, int x, int y, int l, int h){
		super(fenJ, x, y, l, h);
		this.fenJ = fenJ;
		
		//initialisation de la cible et de l'obstacle
		cible = new Cible (0.05, 0.2, this, true);
        obstacle = new Cible (0.12, 0.08, this, false);
        
        //initialisation des pics sur la cible
        PICS_DROITE = T.getImage("Images/pics_cible_droite.png").getScaledInstance((int) (this.getWidth()*0.01), cible.getHauteurCible(), Image.SCALE_DEFAULT);
        PICS_GAUCHE = T.getImage("Images/pics_cible_gauche.png").getScaledInstance((int) (this.getWidth()*0.01), cible.getHauteurCible(), Image.SCALE_DEFAULT);
        
		//initialisation des decors
		espace = new Decor(new AePlayWave("Audios/space.wav"), Color.white, T.getImage("./Images/espace.jpg").getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		jungle = new Decor(new AePlayWave("Audios/mowgli.wav"), Color.yellow, T.getImage("./Images/jungle.jpg").getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		savane = new Decor(new AePlayWave("Audios/lion.wav"), Color.red, T.getImage("./Images/savane.jpg").getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		bobLeponge = new Decor(new AePlayWave("Audios/eponge.wav"), Color.black, T.getImage("./Images/bobLeponge.png").getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));	
	}

	/**********************************  PAINT  **************************************/
	
	/** Rédéfinition de la méthode paintComponent héritée de PanelTraj 
	 *  Elle dessine la cible et l'obstacle
	 *  Ne renvoie rien 
	 *  Name : paintComponent 
	 *  @param g		objet de type Graphics 
	 */	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.fillRect(cible.getPositionX(), (int) this.getHeight() - cible.getHauteurCible(), cible.getLargeurCible(), cible.getHauteurCible());		// dans l'idéal ça se passerait dans Cible ça non ? 
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
	 * Redéfinition de l'implémentation d' ActionListener héritée de PanelTraj 
	 * Gère les évènements du timer spécifiques au mode jeu : évolution des vies et du score en fonction de l'issue de la trajectoire de la balle.
	 * Ne renvoie rien 
	 * Name : actionPerformed 
	 * @param  e 	ActionEvent
	 */	
	public void actionPerformed(ActionEvent e){ 
		super.actionPerformed(e);
		
		if (e.getSource() == time){
			
			// cas où le joueur PERD son lancer
			
			// la balle sort de la fenetre en largeur
			if(dernierXAffiche >= this.getWidth()){	
				fenJ.perdVies(1);
				fenJ.victoireOuDefaite();
				finDuLancer();
				
			}else if(YParcourus.size() > 0 && dernierXAffiche > 0){	 // condition anti-exception : les arraylist de la trajectoire ne doivent pas être nulles 
				
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
	 * Méthode exécutant toutes les réinitialisations nécessaires lorsqu'un lancer est terminé
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
	
	/**
	 * Redéfinission de la méthode mouseClicked héritée de PanelTraj
	 * Ne renvoie rien  
	 * Name : mouseClicked 
	 * @param e		de type MouseEvent  
	*/
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		if (imageObj == null) {
			repaint();
		}
	}	
	
	/**
	 * Méthode définissant la vitesse d'affichage en fonction de la difficulté de jeu choisie 
	 * Ne renvoie rien 
	 * Name : setVitesseAffichage
	 * @param s 	difficulte choisie par l'utilisateur de type String 
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
	 * Méthode permttant de choisir l'image de fond du décor  
	 * Ne renvoie rien 
	 * Name : setDecor
	 * @param decor 	de type String défini le choix du fond du décor 
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
	  * Name : setObjet
	  * @param objet 	de type String, défini le choix de l'objet 
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
	  * Méthode qui gère le choix de l'obstacle en fonction du fond choisi 
	  * Ne renvoie rien 
	  * Name : setObstacle 
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
	 * @return cible  		de type Cible  
	 */	
	public Cible getCible(){
		return this.cible;
	}
    
    /**
	 * Accesseur en lecture des caractéristiques de l'obstacle
	 * Ne prend pas de paramètre en compte 
	 * Name : getObstacle 
	 * @return obstacle  	de type Cible   
	 */	
    public Cible getObstacle(){
		return this.obstacle;
	}
	
	/**
	 * Accesseur en lecture de la fenetreJeu active 
	 * Ne prend pas de paramètre en compte 
	 * Name : getFenetreJeu 
	 * @return fenJ 	de type FenetreJeu, fenetre de jeu associée au PanelTrajJeu
	 */	
	public FenetreJeu getFenetreJeu(){
		return this.fenJ;
	}
}
