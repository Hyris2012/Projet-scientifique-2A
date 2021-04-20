import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.lang.String;

public class FenetreJeu extends FenetreMere{
	
	private int score ;
	private int vie = 5 ;	
	private PanelTrajJeu courbe;	 
	private JComboBox difficulteJeu; 
	private JComboBox objet1 ;
	private JComboBox decor;
	private JLabel labelScore;
	private JLabel labelVie; 
	private JButton sonOnOff;
	private JPanel panel;
	
	public FenetreJeu () {
		super();	
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds((int)(getWidth()*(10/29.7)),(int)(getHeight()*(2.5/21.0)),(int)(getWidth()*(18/29.7)),(int)(getHeight()*(14/21.0)));
		panel.setBackground(Color.white);
		
		JLabel minion = new JLabel();
		minion.setBounds(0, 0, panel.getWidth(), panel.getHeight());
		minion.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("minions.gif").getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_DEFAULT)));
		panel.add(minion);
		
		
		JLabel objet = new JLabel("Choisissez l'objet :") ; //7
		objet.setFont(new Font("Arial",Font.BOLD,20)) ;
		objet.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(11.5/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		
		String obj1 = new String ("Girafe");	
		String obj2 = new String ("Ballon");			 
		String obj3 = new String ("Vaisseau spatial");	
		String obj4 = new String ("Balle");
		String [] choixObj = {obj1, obj2, obj3, obj4};
		objet1 = new JComboBox(choixObj) ; //8
		objet1.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(13.3/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1/21.0))) ;
		
		JLabel endroit = new JLabel("Choisissez l'endroit :") ; //9
		endroit.setFont(new Font("Arial",Font.BOLD,20)) ;
		endroit.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(14.6/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		
		String decor1 = new String ("Savane");	
		String decor2 = new String ("Jungle");			 
		String decor3 = new String ("Espace");	
		String decor4 = new String ("Bob l'eponge");	
		String [] choixDecor = {decor1, decor2, decor3, decor4};
		decor = new JComboBox(choixDecor) ; //10
		decor.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(16.4/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1/21.0))) ;
		
		labelScore = new JLabel(" Score : " + score) ;
		labelScore.setBounds(panel.getX(),jouer.getY(),(int) (panel.getWidth()/4),jouer.getHeight());
		labelScore.setBackground(Color.white); 
		labelScore.setFont(new Font("Arial",Font.BOLD,26));
		
		labelVie = new JLabel("Nombre de vies : "+vie) ;
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
		
		JLabel diff = new JLabel("Choisissez la difficulté") ; //7
		diff.setFont(new Font("Arial",Font.BOLD,20)) ;
		diff.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(5/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		
		String diff1 = new String ("Débutant");	
		String diff2 = new String ("Intermédiaire");			 
		String diff3 = new String ("Expert");	
		String [] choixDiff = {diff1, diff2, diff3};
		difficulteJeu = new JComboBox(choixDiff) ; //8
		difficulteJeu.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(7/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1/21.0))) ;
		
		FenPrinc.add(panel);
		FenPrinc.add(labelScore) ; //5
		FenPrinc.add(labelVie) ; //6
		FenPrinc.add(objet) ; //7
		FenPrinc.add(objet1) ; //8 
		FenPrinc.add(endroit) ; //9
		FenPrinc.add(decor) ; //10
		FenPrinc.add(sonOnOff);
		FenPrinc.add(diff);
		FenPrinc.add(difficulteJeu);
				
		this.add(FenPrinc);		// le mettre dans fenetreMere ?? meme si d'autres trucs sont ajoutés à FenPrinc APRES ??
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		
		if(e.getSource() == retourFenAccueil && enJeu){
			courbe.getFond().getMusiqueChoisie().stop();
		}
		
		if(e.getSource() == sonOnOff){
			String texteOnOff = sonOnOff.getText(); // parfaitement équivalent à ce que vous aviez écrit avant mais seulement cette maniere d'écrire marche , je sais pas pk
			if(texteOnOff.equals("Son:on")){
				courbe.getFond().getMusiqueChoisie().suspend();
				sonOnOff.setText("Son:off");
			}
			if(texteOnOff.equals("Son:off")){
				courbe.getFond().getMusiqueChoisie().resume();
				//etatSon = true;
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
	
	// méthodes pour une meilleure lisibilité du code
	
	public void panelPleinEcran(){
		//changement de la taille du PanelTraj pour l'afficher en plein écran
		panel.setVisible(false);
		courbe = new PanelTrajJeu(this, (int)(jouer.getX()),(int)(this.getHeight()*(2.5/21.0)),(this.getWidth()-2*jouer.getX()),(int)(this.getHeight()*(14/21.0))); // on pourrait mettre ça dans le constructeur pour éviter un temps de latence quand on clique sur jouer, ça changerait pas grand chose mais irait dans le sens de l'optimisation
		courbe.setLayout(null);
		courbe.setBackground(Color.red); // au cas où l'image mise en superposition n'apparaisse pas
		courbe.setVisible(true);
		FenPrinc.add(courbe);	
		labelScore.setBounds(courbe.getX(),jouer.getY(),(int) (courbe.getWidth()/4),jouer.getHeight());
		labelVie.setBounds((int) (courbe.getX()+0.75*courbe.getWidth()),jouer.getY(),(int) (courbe.getWidth()/4),jouer.getHeight());
	}
	
	public void gestionMusiqueEtDecor(){
		boolean b = courbe.getFond().getMusiqueChoisie() == null;
		if(!b){	//si la musique n'est pas nulle = il y en a une qui a déjà été choisie AVANT = qui est en train de tourner
			courbe.getFond().getMusiqueChoisie().suspend();
		}
		courbe.setDecor((String) decor.getSelectedItem());
		// test : la musique choisie n'a jamais encore été jouée (donc jamais interrompue)
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
	}
	
	// accesseurs en lecture
	
	public int getScore() {
		return score ; 
	}
	
	public int getVie() {
		return vie ; 
	}
	
	public JLabel getLabelScore(){
		return this.labelScore;
	}
	
	public JLabel getLabelVie(){
		return this.labelVie;
	}
	
	// accesseurs en écriture 
	
	public void setScore(int sco) {
		this.score = sco ;
	}
	
	public void setVie(int v) {
		this.vie = v ; 
	}
	
}
