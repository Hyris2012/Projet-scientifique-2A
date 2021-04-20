import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.lang.String;

public class FenetreScientifique extends FenetreMere {
	
	private PanelTrajScienti courbe ; 
	private JSlider hauteurInit;
	private JSlider vitesseAffichage;
	private JSlider rayonChoisi;
	private JSlider graviteChoisie;
	private JPanel paramG;
	private JButton changePar;
    private JLabel historique;
    private String histo1;
    private String histo2;
    private String histo3;
    
	
	
	public FenetreScientifique(){
		super();
		
		jouer.setText("Lancer !");
		
		courbe = new PanelTrajScienti(this, (int)(getWidth()*(10/29.7)),(int)(getHeight()*(2.5/21.0)),(int)(getWidth()*(18/29.7)),(int)(getHeight()*(14/21.0)));
		courbe.setLayout(null);
		courbe.setBackground(Color.white);
		
		info = new JLabel("Equation de la trajectoire : ") ;
		info.setBounds(courbe.getX(),jouer.getY()-65,(int)courbe.getWidth()/2,jouer.getHeight());
		info.setBackground(Color.white); 
		info.setFont(new Font("Arial",Font.BOLD,22));
		
        historique = new JLabel("<html>Equations précédentes : <br>"+histo1+"<br>"+histo2+"<br>"+histo3+"</html>") ;
		historique.setBounds((courbe.getX() + (int)courbe.getWidth()/2),jouer.getY()-65,(int)courbe.getWidth()/2,jouer.getHeight()*3);
		historique.setBackground(Color.white); 
		historique.setFont(new Font("Arial",Font.BOLD,22));
        texteHistorique();
        
		// on pourrait garder un JSlider pour moduler la vitesse à laquelle on voit la balle bouger ?
		
		JLabel hInit = new JLabel("Hauteur initiale :"); //3 
		hInit.setFont(new Font("Arial",Font.BOLD,20)) ;
		hInit.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(2/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ; 
		
		hauteurInit = new JSlider(0,courbe.getHeight(),45) ; //2
		hauteurInit.setMajorTickSpacing((int)(courbe.getHeight()/6));
		hauteurInit.setPaintLabels(true) ; 
		hauteurInit.setPaintTicks(true) ; 
		hauteurInit.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(4/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		
		JLabel vitesse = new JLabel("Vitesse d'affichage :"); //3
		vitesse.setFont(new Font("Arial",Font.BOLD,20)) ;
		vitesse.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(6/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ; 
		
		vitesseAffichage = new JSlider(1,11,1) ; //4 
		vitesseAffichage.setMajorTickSpacing(2);
		vitesseAffichage.setPaintLabels(true) ;
		vitesseAffichage.setPaintTicks(true) ; 
		vitesseAffichage.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(8/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0)));
		
		/*changePar = new JButton("changer parametres");
		changePar.setFont(new Font("Arial",Font.BOLD,20)) ;
		changePar.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(14.5/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		changePar.addActionListener(this);
		changePar.setBackground(Color.red);
		*/
		
		JLabel gravite = new JLabel("Gravité :"); //3
		gravite.setFont(new Font("Arial",Font.BOLD,20)) ;
		gravite.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(10/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		
		graviteChoisie = new JSlider(1,26,1) ; //4 
		graviteChoisie.setMajorTickSpacing(5);
		graviteChoisie.setPaintLabels(true) ;
		graviteChoisie.setPaintTicks(true) ; 
		graviteChoisie.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(12/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))); 
		/* choix du rayon inutile en mode scientifique : on pourrait le changer par un SLider masse, mais ça revient au même que la gravité
		JLabel rayonBalle = new JLabel("Rayon de l'objet :"); //3
		rayonBalle.setFont(new Font("Arial",Font.BOLD,20)) ;
		rayonBalle.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(14/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ; 
		
		rayonChoisi = new JSlider(0,90,45) ; //4 //JE SAIS PAS ENCORE QUELLE ECHELLE METTRE : est ce qu'on laisse tout en pixels ou en fait une sorte de conversion en metre 
		rayonChoisi.setMajorTickSpacing(15);
		rayonChoisi.setPaintLabels(true) ;
		rayonChoisi.setPaintTicks(true) ; 
		rayonChoisi.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(16/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0)));
		*/
		
		FenPrinc.add(courbe);
		FenPrinc.add(hauteurInit);
		FenPrinc.add(vitesseAffichage);
		FenPrinc.add(hInit);
		FenPrinc.add(vitesse);
		FenPrinc.add(info);
        FenPrinc.add(historique);
		FenPrinc.add(gravite);
		FenPrinc.add(graviteChoisie);
		//FenPrinc.add(rayonBalle);
		//FenPrinc.add(rayonChoisi);
		//FenPrinc.add(changePar);
		
		this.add(FenPrinc);		// est-ce que ce serait possible de le mettre dans FenetreMere
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource() == jouer){
            histo3=histo2;
            histo2=histo1;
            if(courbe.balle!=null){
                histo1=courbe.balle.getPolynome().toString();
            }
            texteHistorique();
			courbe.departFleche.y=hauteurInit.getValue();
			courbe.pesanteurChoisie=graviteChoisie.getValue();
			courbe.setVitesseAffichage(vitesseAffichage.getValue());
			courbe.flecheSuitSouris = true;
			courbe.reInit();
			courbe.repaint();
			
			if(!enJeu){
				enJeu = true;
				courbe.setVisible(true);
			
				courbe.flecheSuitSouris = true;
				courbe.reInit();
				courbe.repaint();	
			}		
			
		}
		if(e.getSource() == changePar){
			enJeu = false ;
		}
	}
    
    public void texteHistorique(){
        if(histo3!=null){
            historique.setText("<html>Equations précédentes : <br>"+histo1+"<br>"+histo2+"<br>"+histo3+"</html>") ;   
        }else if(histo2!=null){
            historique.setText("<html>Equations précédentes : <br>"+histo1+"<br>"+histo2+"</html>") ;
        }else if(histo1!=null){
            historique.setText("<html>Equations précédentes : <br>"+histo1+"</html>") ;
        }else{
            historique.setText("Equations précédentes :") ;
        }
    }

}
