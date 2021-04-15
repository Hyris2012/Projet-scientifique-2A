
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;


public class PanelTrajJeu extends PanelTraj{
	
	FenetreJeu fenJ;
	public Decor espace;
	public Decor savane;
	public Decor jungle;
	public Decor bobLeponge;
	
	private final Image ballon = T.getImage("ballon.png");
	private final Image girafe = T.getImage("girafe.png");
	private final Image vaisseau = T.getImage("vaisseau.png");
	private final Image balleTennis = T.getImage("balleTennis.png");
	
	private Image imageObj;
	
	public Cible cible;
	
	public PanelTrajJeu(FenetreJeu fenJ, int x, int y, int l, int h){
		super(fenJ, x, y, l, h);
		this.fenJ = fenJ;
		
		cible = new Cible (0.05, 0.2, this);
		//initialisation des decors
		espace = new Decor(new AePlayWave("space.wav"), Color.white, T.getImage("./espace.jpg").getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		jungle = new Decor(new AePlayWave("mowgli.wav"), Color.yellow, T.getImage("./jungle.jpg").getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		savane = new Decor(new AePlayWave("lion.wav"), Color.red, T.getImage("./savane.jpg").getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		bobLeponge = new Decor(new AePlayWave("eponge.wav"), Color.black, T.getImage("./bobLeponge.png").getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));	
		
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
			imageObj = girafe.getScaledInstance((int) (this.getWidth()*0.1), (int) (this.getHeight()*0.1), Image.SCALE_DEFAULT);
			break;
        case "Ballon":
            imageObj = ballon.getScaledInstance((int) (this.getWidth()*0.1), (int) (this.getHeight()*0.1), Image.SCALE_DEFAULT);
            break;
        case "Vaisseau spatial":
            imageObj = vaisseau.getScaledInstance((int) (this.getWidth()*0.1), (int) (this.getHeight()*0.1), Image.SCALE_DEFAULT);
            break;
        case "Balle":
            imageObj = balleTennis.getScaledInstance((int) (this.getWidth()*0.1), (int) (this.getHeight()*0.1), Image.SCALE_DEFAULT);
            break;
        default :
            imageObj = balleTennis.getScaledInstance((int) (this.getWidth()*0.1), (int) (this.getHeight()*0.1), Image.SCALE_DEFAULT);
            break;
        }
	}
	
	public boolean atterrie(){
		boolean b = super.atterrie();
		
		if(b){
			this.cible.time.stop();
			Outils.pause(2000);
			this.cible.time.start();
			this.flecheSuitSouris = true;
			this.reInit();
			this.repaint();	
		}
		return b;
	}
	
	/**********************************  PAINT  **************************************/
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.fillRect(cible.positionX, (int) this.getHeight() - cible.hauteur, cible.largeur, cible.hauteur);
		
		if(balle != null && XParcourus.size() > 0 && YParcourus.size() > 0 && fond !=null){
			g.drawImage(imageObj, X[X.length - 1]- imageObj.getWidth(null)/2, Y[Y.length - 1] - imageObj.getHeight(null)/2, null);
		}
	}
	
	
	
	public void actionPerformed(ActionEvent e){ //lié au timer
		super.actionPerformed(e);
		
		if (e.getSource() == time){
			if(dernierXAffiche < this.getWidth()){
				 
				if(YParcourus.size() > 0){		// condition anti-exception : les arraylist de la trajectoire ne sont pas nulles 
					
					if(cible.touche(dernierXAffiche, YParcourus.get(dernierXAffiche-1).intValue())){		

						fenJ.setScore(fenJ.getScore() + 300); 				// = fenJ.score + 300;
						fenJ.labelScore.setText("Score : " + fenJ.getScore());
						// ci-dessus : résumer sous une méthode de fenJ 'miseAJourScore' 
						time.stop();
						cible.time.stop(); 
						Outils.pause(2000);
						this.cible.time.start();
						this.flecheSuitSouris = true;
						this.reInit();
						this.repaint();		
						return;
					}
				}
				
			}else{		// si on sort de la fenetre en largeur
				cible.time.stop();
				Outils.pause(2000);
				this.cible.time.start();
				this.flecheSuitSouris = true;
				this.reInit();
				this.repaint();	
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
		super.mouseClicked(e);
		if (imageObj == null) {	// normalement on devrait pouvoir l'enlever, puisque la condition est déjà dans le paint
			repaint();
	    }
	}	

}
