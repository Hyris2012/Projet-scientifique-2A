import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.lang.String;

public class FenetreScientifique extends FenetreMere {
	
	private PanelTrajScienti courbe ; 
	private String gifChoisi;
	private JLabel gifPanel;
	
	private JSlider hauteurInit;
	private JSlider vitesseAffichage;
	private JSlider rayonChoisi;
	private JSlider graviteChoisie;
	
	protected JLabel equa; // va stocker la phrase "Equation de la trajectoire : "
	protected JLabel equationCourante; // va stocker l'équation affichée
    private JLabel historique;
    private JLabel histo1;
    private JLabel histo2;
    private JLabel histo3;
    
    private JLabel infoVitesseInitiale; // stocke la phrase "Vitesse initiale :"
    protected JLabel vInit; // stocke la valeur de la vitesse initiale
    private JLabel infoAngleInitial;
    protected JLabel aInit;
    private JLabel infoDistanceParcourue;
    protected JLabel distParcourue;
    private JLabel infoHauteurAtteinte;
    protected JLabel hAtteinte;
    
	protected JLabel info;
	
	public FenetreScientifique(){
		super();
		
		jouer.setText("Lancer !");
		
		courbe = new PanelTrajScienti(this, (int)(getWidth()*(10/29.7)),(int)(getHeight()*(2.5/21.0)),(int)(getWidth()*(18/29.7)),(int)(getHeight()*(14/21.0)));
		courbe.setLayout(null);
		courbe.setBackground(Color.white);
		
		choixGif();
		gifPanel = new JLabel();
		gifPanel.setBounds(0, 0, courbe.getWidth(), courbe.getHeight());
		gifPanel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(gifChoisi).getScaledInstance(courbe.getWidth(), courbe.getHeight(), Image.SCALE_DEFAULT)));
		courbe.add(gifPanel);
		
		equa = new JLabel("<html><u> Equation de la trajectoire :</u></html>") ;
		equa.setBounds(courbe.getX(),jouer.getY()-50,(int)courbe.getWidth()/2,jouer.getHeight()/2);
		equa.setBackground(Color.white); 
		equa.setFont(new Font("Arial",Font.BOLD,22));
		
		equationCourante = new JLabel("") ;
		equationCourante.setBounds(courbe.getX(),jouer.getY()-30,(int)courbe.getWidth()/2,jouer.getHeight());
		equationCourante.setBackground(Color.white); 
		equationCourante.setFont(new Font("Arial",Font.BOLD,22));
		
        historique = new JLabel("<html><u>Equations précédentes :</u></html>") ;
		historique.setBounds((courbe.getX() + (int)courbe.getWidth()/2),equa.getY(),(int)courbe.getWidth()/2,jouer.getHeight()/2);
		historique.setBackground(Color.white); 
		historique.setFont(new Font("Arial",Font.BOLD,22));
      
        
        histo1 = new JLabel("") ;
        histo1.setBounds((courbe.getX() + (int)courbe.getWidth()/2),historique.getY()+historique.getHeight(),(int)courbe.getWidth()/2,jouer.getHeight()/2);
        histo1.setBackground(Color.white); 
        histo1.setFont(new Font("Arial",Font.BOLD,22));

        histo2 = new JLabel("") ;
        histo2.setBounds((courbe.getX() + (int)courbe.getWidth()/2),histo1.getY()+histo1.getHeight(),(int)courbe.getWidth()/2,jouer.getHeight()/2);
        histo2.setBackground(Color.white); 
        histo2.setFont(new Font("Arial",Font.BOLD,22));

        histo3 = new JLabel("") ;
        histo3.setBounds((courbe.getX() + (int)courbe.getWidth()/2),histo2.getY()+histo2.getHeight(),(int)courbe.getWidth()/2,jouer.getHeight()/2);
        histo3.setBackground(Color.white); 
        histo3.setFont(new Font("Arial",Font.BOLD,22));
		
		JLabel hInit = new JLabel("<html><u>  Hauteur initiale (cm) :</u></html>"); //3 
		hInit.setFont(new Font("Arial",Font.BOLD,20)) ;
		hInit.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(1.3/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.2/21.0))) ; 
				
		hauteurInit = new JSlider(0,600,1) ; //2
		hauteurInit.setMajorTickSpacing(100);
		hauteurInit.setPaintLabels(true) ; 
		hauteurInit.setPaintTicks(true) ; 
		hauteurInit.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(2.6/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.2/21.0))) ;
		
		JLabel vitesse = new JLabel("<html><u> Vitesse d'affichage :</u></html>"); //3
		vitesse.setFont(new Font("Arial",Font.BOLD,20)) ;
		vitesse.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(3.9/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.2/21.0))) ; 
		
		vitesseAffichage = new JSlider(1,11,1) ; //4 
		vitesseAffichage.setMajorTickSpacing(2);
		vitesseAffichage.setPaintLabels(true) ;
		vitesseAffichage.setPaintTicks(true) ; 
		vitesseAffichage.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(5.2/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.2/21.0)));
		
		JLabel gravite = new JLabel("<html><u> Gravité :</u></html>"); //3
		gravite.setFont(new Font("Arial",Font.BOLD,20)) ;
		gravite.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(6.5/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.2/21.0))) ;
		
		graviteChoisie = new JSlider(1,26,1) ; //4 
		graviteChoisie.setMajorTickSpacing(5);
		graviteChoisie.setPaintLabels(true) ;
		graviteChoisie.setPaintTicks(true) ; 
		graviteChoisie.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(7.8/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.2/21.0))); 
		
		infoVitesseInitiale = new JLabel("<html><u> Vitesse Initiale :</u></html>") ;
        infoVitesseInitiale.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(9.1/21.0)),(int)(largeur*(12/29.7)),(int)(hauteur*(1.2/21.0)));
        infoVitesseInitiale.setBackground(Outils.FOND_BLEU); 
        infoVitesseInitiale.setFont(new Font("Arial",Font.BOLD,22));
		
		vInit = new JLabel("<html><center> ... </center></html>") ;
        vInit.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(10.1/21.0)),(int)(largeur*(12/29.7)),(int)(hauteur*(1/21.0)));
        vInit.setBackground(Outils.FOND_BLEU); 
        vInit.setFont(new Font("Arial",Font.BOLD,22));
        
        infoAngleInitial = new JLabel("<html><u> Angle Initial :</u></html>") ;
        infoAngleInitial.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(11.1/21.0)),(int)(largeur*(12/29.7)),(int)(hauteur*(1.2/21.0)));
        infoAngleInitial.setBackground(Outils.FOND_BLEU); 
        infoAngleInitial.setFont(new Font("Arial",Font.BOLD,22));
        
        aInit = new JLabel("<html><center> ... </center></html>") ;
        aInit.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(12.1/21.0)),(int)(largeur*(12/29.7)),(int)(hauteur*(1/21.0)));
        aInit.setBackground(Outils.FOND_BLEU); 
        aInit.setFont(new Font("Arial",Font.BOLD,22));
        
        infoDistanceParcourue = new JLabel("<html><u> Distance parcourue :</u></html>") ;
        infoDistanceParcourue.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(13.1/21.0)),(int)(largeur*(12/29.7)),(int)(hauteur*(1.2/21.0)));
        infoDistanceParcourue.setBackground(Outils.FOND_BLEU); 
        infoDistanceParcourue.setFont(new Font("Arial",Font.BOLD,22));
        
        distParcourue = new JLabel("<html><center> ... </center></html>") ;
        distParcourue.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(14.1/21.0)),(int)(largeur*(12/29.7)),(int)(hauteur*(1/21.0)));
        distParcourue.setBackground(Outils.FOND_BLEU); 
        distParcourue.setFont(new Font("Arial",Font.BOLD,22));
		
		infoHauteurAtteinte = new JLabel("<html><u> Hauteur Atteinte :</u></html>") ;
        infoHauteurAtteinte.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(15.1/21.0)),(int)(largeur*(12/29.7)),(int)(hauteur*(1.2/21.0)));
        infoHauteurAtteinte.setBackground(Outils.FOND_BLEU); 
        infoHauteurAtteinte.setFont(new Font("Arial",Font.BOLD,22));
        
        hAtteinte = new JLabel("<html><center> ... </center></html>") ;
        hAtteinte.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(16.1/21.0)),(int)(largeur*(12/29.7)),(int)(hauteur*(1/21.0)));
        hAtteinte.setBackground(Outils.FOND_BLEU); 
        hAtteinte.setFont(new Font("Arial",Font.BOLD,22));
        
        		
		FenPrinc.add(courbe);
		FenPrinc.add(hauteurInit);
		FenPrinc.add(vitesseAffichage);
		FenPrinc.add(hInit);
		FenPrinc.add(vitesse);
		FenPrinc.add(equa);
		FenPrinc.add(equationCourante);
        FenPrinc.add(historique);
        FenPrinc.add(histo1);
        FenPrinc.add(histo2);
        FenPrinc.add(histo3);
		FenPrinc.add(gravite);
		FenPrinc.add(graviteChoisie);
		FenPrinc.add(infoVitesseInitiale);
		FenPrinc.add(vInit);
		FenPrinc.add(infoAngleInitial);
		FenPrinc.add(aInit);
		FenPrinc.add(infoDistanceParcourue);
		FenPrinc.add(distParcourue);
		FenPrinc.add(infoHauteurAtteinte);
		FenPrinc.add(hAtteinte);
		
		this.add(FenPrinc);
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		
		if(e.getSource() == jouer && !enJeu){
			
			gifPanel.setVisible(false);
			
			enJeu = true;
			vInit.setText("<html><center> ... </center></html>");
			aInit.setText("<html><center> ... </center></html>");
			distParcourue.setText("<html><center> ... </center></html>");
			hAtteinte.setText("<html><center> ... </center></html>");
            histo3.setText(histo2.getText());
            histo2.setText(histo1.getText());
            
            if(courbe.balle!=null){
                histo1.setText(courbe.balle.getPolynome().toString());
            }
          
            courbe.departFleche.y = hauteurInit.getValue();
			courbe.pesanteurChoisie = graviteChoisie.getValue();
			courbe.setVitesseAffichage(vitesseAffichage.getValue());
			courbe.flecheSuitSouris = true;
			courbe.reInit();
			courbe.repaint();
		}
	}
    
    public void choixGif(){ // pour avoir un des 3 gif qui se met aléatoirement au lancement de fenetreScientifique
		switch ((int)(Math.random()*3+1)){
        case 1 :
			this.gifChoisi = "maths.gif";
			break;
        case 2:
            this.gifChoisi = "physique.gif" ;
            break;
        case 3:
            this.gifChoisi = "billNye.gif";
            break;
        default :
            this.gifChoisi = "physique.gif";
            break;
        }
		
	}

}
