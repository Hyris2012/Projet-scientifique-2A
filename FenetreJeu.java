/** 
 * Nom de la classe : FenetreJeu
 * Crée une fenêtre gérant le mode Jeu, classe descendante de la classe fenetreMere
 */
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.lang.String;

public class FenetreJeu extends FenetreMere{	
	
	// attributs pour la partie de jeu 
	private int score ;
	private int vie;
	private int combo;
		
	// éléments d'IHM 
	private JComboBox difficulteJeu; 
	private JComboBox objet1 ;
	private JComboBox decor;
	private JLabel labelScore;
	private JLabel labelVie; 
	private JButton sonOnOff;
	private JPanel panel;
	
	// panel dans lequel la trajectoire est gérée
	private PanelTrajJeu courbe;
	
	/**
	 * Constructeur par défaut et seul constructeur de la classe
	 * Ne prend en compte aucun paramètre 
	 * Crée une fenêtre de jeu 
	 */
	public FenetreJeu () {				
		score = 0;
		vie = 7;
		panel = new JPanel(); //1
		panel.setLayout(null);
		panel.setBounds((int)(getWidth()*(10/29.7)),(int)(getHeight()*(2.5/21.0)),(int)(getWidth()*(18/29.7)),(int)(getHeight()*(14/21.0)));
		panel.setBackground(Color.white);
		
		JLabel minion = new JLabel(); //2
		minion.setBounds(0, 0, panel.getWidth(), panel.getHeight());
		minion.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("Images/minions.gif").getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_DEFAULT)));
				
		JLabel diff = new JLabel("<html><u>Choisissez la difficulté</u></html>") ; //3
		diff.setFont(new Font("Arial",Font.BOLD,20)) ;
		diff.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(3/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		
		String diff1 = new String ("Débutant");	
		String diff2 = new String ("Intermédiaire");			 
		String diff3 = new String ("Expert");	
		String [] choixDiff = {diff1, diff2, diff3};
		difficulteJeu = new JComboBox(choixDiff) ; //4
		difficulteJeu.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(5/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1/21.0))) ;
		
		JLabel objet = new JLabel("<html><u>Choisissez l'objet :</u></html>") ; //5
		objet.setFont(new Font("Arial",Font.BOLD,20)) ;
		objet.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(8/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		
		String obj1 = new String ("Girafe");	
		String obj2 = new String ("Pommeau de douche");			 
		String obj3 = new String ("Barbecue");	
		String obj4 = new String ("Ananas");
		String obj5 = new String ("Photocopieur");
		String [] choixObj = {obj1, obj2, obj3, obj4,obj5};
		objet1 = new JComboBox(choixObj) ; //6
		objet1.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(10/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1/21.0))) ;
		
		JLabel endroit = new JLabel("<html><u>Choisissez l'endroit :</u></html>") ; //7
		endroit.setFont(new Font("Arial",Font.BOLD,20)) ;
		endroit.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(13/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		
		String decor1 = new String ("Espace");	
		String decor2 = new String ("Jungle");			 
		String decor3 = new String ("Savane");	
		String decor4 = new String ("Bob l'eponge");	
		String [] choixDecor = {decor1, decor2, decor3, decor4};
		decor = new JComboBox(choixDecor) ; //8
		decor.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(15/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1/21.0))) ;
		
		labelScore = new JLabel(" Score : " + score) ; //9
		labelScore.setBounds(panel.getX(),jouer.getY(),(int) (panel.getWidth()/4),jouer.getHeight());
		labelScore.setBackground(Color.white); 
		labelScore.setFont(new Font("Arial",Font.BOLD,26));
		
		labelVie = new JLabel("Nombre de vies : "+vie) ; //10
		labelVie.setBounds((int) (panel.getX()+0.7*panel.getWidth()),jouer.getY(),(int) (panel.getWidth()/3),jouer.getHeight());
		labelVie.setBackground(Color.white); 
		labelVie.setFont(new Font("Arial",Font.BOLD,26));
		
		sonOnOff = new JButton ("Son:on");
		sonOnOff.setFont(new Font("Serif",Font.BOLD,20)) ;
		sonOnOff.setBounds((int)(largeur*(4/29.7)),(int)(hauteur*(0.5/21.0)),(int)(largeur*(2/29.7)),(int)(hauteur*(1/21.0)));
		sonOnOff.setForeground(Color.white);
		sonOnOff.setBackground(new Color (90,90,90)); 
		sonOnOff.addActionListener(this);
		sonOnOff.setVisible(false);
		
		panel.add(minion); //2				
		fenPrinc.add(panel); //1
		fenPrinc.add(diff); //3
		fenPrinc.add(difficulteJeu); //4
		fenPrinc.add(objet); //5
		fenPrinc.add(objet1); //6
		fenPrinc.add(endroit); //7
		fenPrinc.add(decor); //8
		fenPrinc.add(labelScore); //9
		fenPrinc.add(labelVie); //10
		fenPrinc.add(sonOnOff); //11		
				
		this.add(fenPrinc);	
		setVisible(true);		
	}
	
	/**
	 * Implémentation de l'interface ActionListener 
	 * Gère les interactions avec les éléments d'IHM branchés
	 * ne renvoie rien 
	 * Name : actionPerformed 
	 * @param  e 	ActionEvent
	*/	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		
		if(e.getSource() == retourFenAccueil && enJeu){ // on arrête la musique si on clique sur retour et qu'on a déjà cliqué sur jouer!
			courbe.getFond().getMusiqueChoisie().stop();
		}
		
		if(e.getSource() == sonOnOff){ // on allume ou éteint la musique
			String texteOnOff = sonOnOff.getText();
			if(texteOnOff.equals("Son:on")){
				courbe.getFond().getMusiqueChoisie().suspend();
				sonOnOff.setText("Son:off");
			}
			if(texteOnOff.equals("Son:off")){
				courbe.getFond().getMusiqueChoisie().resume();
				sonOnOff.setText("Son:on");
			}
		}		
		
		if(e.getSource() == jouer){
			// pour qu'on ne puisse pas changer de décor en cours de partie : ci après 
			if(!enJeu){
				enJeu = true ;
				
				panelPleinEcran();
				
				objet1.setVisible(false);
				decor.setVisible(false);
				jouer.setVisible(false);
				difficulteJeu.setVisible(false);
				
				courbe.getCible().getTimerCible().start();
                courbe.getObstacle().getTimerCible().start();				
				courbe.getCible().setVitesseCible((String) difficulteJeu.getSelectedItem());
                courbe.getObstacle().setVitesseCible((String) difficulteJeu.getSelectedItem());
				courbe.setVitesseAffichage((String) difficulteJeu.getSelectedItem());
				
				gestionMusiqueEtDecor();
				
				courbe.setVisible(true);
				sonOnOff.setVisible(true);
				courbe.flecheSuitSouris = true;
				courbe.reInit();
				courbe.repaint();							
			}			
		}		
	}
	
	/**
	 * Méthode permettant d'afficher le panel de jeu sur toute la fenetre lors du clic sur le bouton "jouer"
	 * Name : panelPleinEcran
	 * Ne prend en compte aucun paramètre et ne renvoie rien 
	 */	
	public void panelPleinEcran(){		
		panel.setVisible(false);
		courbe = new PanelTrajJeu(this, (int)(jouer.getX()),(int)(this.getHeight()*(2.5/21.0)),(this.getWidth()-2*jouer.getX()),(int)(this.getHeight()*(14/21.0))); // on pourrait mettre ça dans le constructeur pour éviter un temps de latence quand on clique sur jouer, ça changerait pas grand chose mais irait dans le sens de l'optimisation
		courbe.setLayout(null);
		courbe.setBackground(Color.red); // au cas où l'image mise en superposition n'apparaisse pas
		courbe.setVisible(true);
		fenPrinc.add(courbe);	
		labelScore.setBounds(courbe.getX(),jouer.getY(),(int) (courbe.getWidth()/4),jouer.getHeight());
		labelVie.setBounds((int) (courbe.getX()+0.75*courbe.getWidth()),jouer.getY(),(int) (courbe.getWidth()/4),jouer.getHeight());
	}
	
	/**
 * Méthode gérant le lancement des musiques en fonction du décor choisi
 * name : gestionMusiqueEtDecor 
 * Ne prend pas de paramètre en compte et ne renvoie rien 
 */
	 
	public void gestionMusiqueEtDecor(){
		boolean b = courbe.getFond().getMusiqueChoisie() == null;
		if(!b){	//si la musique n'est pas nulle = il y en a une qui a déjà été choisie AVANT = qui est en train de tourner
			courbe.getFond().getMusiqueChoisie().suspend();
		}
		courbe.setDecor((String) decor.getSelectedItem());
		// on teste si la musique choisie n'a jamais encore été jouée (donc jamais interrompue)
		b = !courbe.getFond().getMusiqueChoisie().isAlive();		//isAlive = a déjà été starté 
		if(b){
			courbe.getFond().getMusiqueChoisie().start();
		}
		// sinon, elle a déjà été starté donc interrupted, il faut utiliser resume()
		else{
			courbe.getFond().getMusiqueChoisie().resume();
		}	
		// méthode changerDecor qui s'occuper aussi de changer la musique 
		courbe.setObjet((String) objet1.getSelectedItem());	
		courbe.setObstacle((String) decor.getSelectedItem());
	}
	
	/** Méthode permettant de déterminer si la partie est perdue ou gagnée
	 * Elle crée alors une nouvelle fenêtre Restart 
	 * Name : victoireOuDefaite
	 * Ne prend pas en compte de paramètre et ne renvoie rien
	 */	
	public void victoireOuDefaite(){
		if(vie <= 0){
			new Restart(this, "<html><center> Tu as perdu ! <br> Tu peux tenter à nouveau ta chance <br> ou quitter le jeu !</center></html>");
			courbe.getFond().getMusiqueChoisie().stop();
			this.setVisible(false); // sinon on peut recliquer dans la fenêtre et continuer à jouer
				
		}else if(score >= 3000){
			new Restart(this, "<html><center> Gagné! Bravo ! <br> Tu peux recommencer une partie <br> ou explorer le mode scientifique </center></html>");
			courbe.getFond().getMusiqueChoisie().stop();
			this.setVisible(false);			
		}
	}
	
	/**
	 * Méthode qui gère les pertes de vies du joueur
	 * Name : perdVies
	 * Ne renvoie rien
	 * @param perte 	enlève une vie à chaque lancer râté
	 */	
	public void perdVies(int perte){
		combo = 0;
		vie = vie - 1; 			
		labelVie.setText("Nombre de vies: " + vie);
	}
	
	/**
	 * Méthode qui gère le score du joueur et les éventuels combos
	 * Lorsque le joueur réussi trois lancers d'affilée, son score est multiplié par 2 s'il réussi le 4e lancer
	 * Name : mAjScore 
	 * Ne renvoie rien 
	 * @param ajout 	nombre de points au score 
	 */	
	public void mAjScore(int ajout){
		if(combo < 3){
			score = score + ajout; 
			combo++;
		}else{ // tant qu'on continue à faire des combos on a un ajout*2
			score = score + ajout*2;
		}				
		labelScore.setText("Score : " + score);
	}
	
	/**
	 * Accesseur en lecture du score  
	 * Ne prend pas de paramètre en compte  
	 * @return score 		score actuel du joueur   
	 */	
	public int getScore() {
		return score ; 
	}
	
	/**
	 * Accesseur en lecture du nombre de vies  
	 * Ne prend pas de paramètre en compte  
	 * @return vie 		nombre de vies actuelles du joueur   
	 */
	public int getVie() {
		return vie ; 
	}
	
	/**
	 * Accesseur en lecture du JLabel de score  
	 * Ne prend pas de paramètre en compte  
	 * @return JLabel 		état actuel du labelScore   
	 */	
	public JLabel getLabelScore(){
		return this.labelScore;
	}
	
	/**
	 * Accesseur en lecture du JLabel de vies  
	 * Ne prend pas de paramètre en compte  
	 * @return JLabel 		état actuel du labelVie  
	 */	
	public JLabel getLabelVie(){
		return this.labelVie;
	}	
}
