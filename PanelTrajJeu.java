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
	
	private final Image ballon = T.getImage("ballon.png");
	private final Image girafe = T.getImage("girafe.png");
	private final Image vaisseau = T.getImage("vaisseau.png");
	private final Image balleTennis = T.getImage("balleTennis.png");
	
	private final Image harpe=T.getImage("harpe.png");
	private final Image bus=T.getImage("bus_a_imperiale.png");
	private final Image grillePain=T.getImage("grille_pain.png");
	private final Image phedre=T.getImage("phedre.png");
	
	private Image imageObj;
	private Image imageObstacle;
	private Cible cible;
    private Cible obstacle;
	
	public PanelTrajJeu(FenetreJeu fenJ, int x, int y, int l, int h){
		super(fenJ, x, y, l, h);
		this.fenJ = fenJ;
		
		cible = new Cible (0.05, 0.2, this, true);
        obstacle = new Cible (0.12, 0.08, this, false);
        
		//initialisation des decors
		espace = new Decor(new AePlayWave("space.wav"), Color.white, T.getImage("./espace.jpg").getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		jungle = new Decor(new AePlayWave("mowgli.wav"), Color.yellow, T.getImage("./jungle.jpg").getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		savane = new Decor(new AePlayWave("lion.wav"), Color.red, T.getImage("./savane.jpg").getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		bobLeponge = new Decor(new AePlayWave("eponge.wav"), Color.black, T.getImage("./bobLeponge.png").getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));	
	}
	
	/** Méthode permettant de déterminer si la balle a atterrie (= trajectoire atteint le bord inférieur du panel) ou non.
	 * @param aucun
	 * @return booléen
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
	/** Rédéfinition de la méthode paint héritée de JPanel. Elle dessine tous les objets graphiques dans le panel.
	 * @param g de type Graphics
	 * @return void
	 */
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.fillRect(cible.getPositionX(), (int) this.getHeight() - cible.getHauteurCible(), cible.getLargeurCible(), cible.getHauteurCible());		// dans l'idéal ça se passerait dans Cible ça non ? 
		
		if(cible.getVitesseCible()!=0){ // pour que l'obstacle ne s'affiche pas en mode débutant
			//g.fillRect(obstacle.getPositionX(),obstacle.getPositionY(), obstacle.getLargeurCible(), obstacle.getHauteurCible());
			g.drawImage(imageObstacle, obstacle.getPositionX(), obstacle.getPositionY(),null);
		}
		
		if(balle != null && XParcourus.size() > 0 && YParcourus.size() > 0 && fond !=null){
			g.drawImage(imageObj, X[X.length - 1]- imageObj.getWidth(null)/2, Y[Y.length - 1] - imageObj.getHeight(null)/2, null);
		}
	}
	
	/**
	 * Redéfinition de l'implémentation de l'interface ActionListener héritée de PanelTraj. 
	 * Gère les évènements du timer spécifiques au mode jeu : évolution des vies et du score en fonction de l'issue de la trajectoire de la balle.
	 * @param e de type ActionEvent
	 * @return void
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
				
			}else if(YParcourus.size() > 0){		 // condition anti-exception : les arraylist de la trajectoire ne doivent pas être nulles 
				
				// la balle touche l'obstacle
				if(cible.getVitesseCible()!=0 && obstacle.touche(dernierXAffiche, YParcourus.get((int)(dernierXAffiche/vitesseAffichage)-1).intValue())){		// tentative infructueuse pour que l'image s'arrête quand c'est le bas qui touche et non pas le centre : + imageObj.getHeight(null)/2
					fenJ.perdVies(1);
					fenJ.victoireOuDefaite();
					finDuLancer();
					return;	
                
                // la balle touche sol sans avoir touché la cible  
                }else if(atterrie()){
					fenJ.perdVies(1);
					fenJ.victoireOuDefaite();
					finDuLancer();
					return;
					
				// cas où le joueur GAGNE son lancer : il touche la cible
				}else if(cible.touche(dernierXAffiche, YParcourus.get((int)(dernierXAffiche/vitesseAffichage)-1).intValue())){		
					fenJ.mAjScore(300);
					fenJ.victoireOuDefaite();
					finDuLancer();
					return; 
				}
				
				// remarque : les return permettent de sortir de la méthode lorsqu'UNE des conditions et vérifiée sans tester les suivantes 
			}
		}	
	}
	
	/**
	 * Méthode exécutant toutes les réinitialisations nécessaires lorsqu'un lancer est considéré terminé et doit laisser place à un autre.
	 * @param aucun
	 * @return void
	 */
	
	public void finDuLancer(){
		time.stop(); // pour éviter que la trajectoir reparte en boucle	-> ne fonctionne pas manifestement 
		cible.getTimerCible().stop();
        obstacle.getTimerCible().stop();
		Outils.pause(1000);
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
	
	public void setVitesseAffichage(String s){
		switch (s){
        case "Débutant" :
			this.vitesseAffichage = 1;
			break;
        case "Intermédiaire":
            this.vitesseAffichage = 2;
            break;
        case "Expert":
            this.vitesseAffichage = 4;
            break;
        default :
            this.vitesseAffichage = 1;
            break;
        }
	}
	
	
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
    
    public void setObjet(String objet){
		switch (objet){
        case "Girafe" :
			imageObj = girafe.getScaledInstance((int) (this.getWidth()*0.1), (int) (this.getHeight()*0.18), Image.SCALE_DEFAULT);
			break;
        case "Ballon":
            imageObj = ballon.getScaledInstance((int) (this.getWidth()*0.1), (int) (this.getHeight()*0.17), Image.SCALE_DEFAULT);
            break;
        case "Vaisseau spatial":
            imageObj = vaisseau.getScaledInstance((int) (this.getWidth()*0.1), (int) (this.getHeight()*0.13), Image.SCALE_DEFAULT);
            break;
        case "Balle":
            imageObj = balleTennis.getScaledInstance((int) (this.getWidth()*0.1), (int) (this.getHeight()*0.17), Image.SCALE_DEFAULT);
            break;
        default :
            imageObj = balleTennis.getScaledInstance((int) (this.getWidth()*0.1), (int) (this.getHeight()*0.17), Image.SCALE_DEFAULT);
            break;
        }
	}
	
	public void setObstacle(String decor){
		switch (decor){
        case "Espace" :
			imageObstacle = harpe.getScaledInstance((int) (this.getWidth()*0.08), (int) (this.getHeight()*0.2), Image.SCALE_DEFAULT);
			break;
        case "Jungle":
            imageObstacle = bus.getScaledInstance((int) (this.getWidth()*0.1), (int) (this.getHeight()*0.18), Image.SCALE_DEFAULT);
            break;
        case "Bob l'eponge":
            imageObstacle = phedre.getScaledInstance((int) (this.getWidth()*0.07), (int) (this.getHeight()*0.21), Image.SCALE_DEFAULT);
            break;
        case "Savane":
            imageObstacle = grillePain.getScaledInstance((int) (this.getWidth()*0.1), (int) (this.getHeight()*0.18), Image.SCALE_DEFAULT);
            break;
        default :
            imageObstacle = harpe.getScaledInstance((int) (this.getWidth()*0.1), (int) (this.getHeight()*0.18), Image.SCALE_DEFAULT);
            break;
        }
	}
	
	// accesseurs en lecture
	
	public Cible getCible(){
		return this.cible;
	}
    
    public Cible getObstacle(){
		return this.obstacle;
	}
	
	public FenetreJeu getFenetreJeu(){
		return this.fenJ;
	}

}
