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
	
	protected JLabel equa;
    private JLabel historique;
    private JLabel histo1;
    private JLabel histo2;
    private JLabel histo3;
    
	protected JLabel info;
	
	public FenetreScientifique(){
		super();
		
		jouer.setText("Lancer !");
		
		courbe = new PanelTrajScienti(this, (int)(getWidth()*(10/29.7)),(int)(getHeight()*(2.5/21.0)),(int)(getWidth()*(18/29.7)),(int)(getHeight()*(14/21.0)));
		courbe.setLayout(null);
		courbe.setBackground(Color.white);
		
		equa = new JLabel("Equation de la trajectoire : ") ;
		equa.setBounds(courbe.getX(),jouer.getY()-50,(int)courbe.getWidth()/2,jouer.getHeight());
		equa.setBackground(Color.white); 
		equa.setFont(new Font("Arial",Font.BOLD,22));
		
        historique = new JLabel("Equations précédentes : ") ;
		historique.setBounds((courbe.getX() + (int)courbe.getWidth()/2),equa.getY(),(int)courbe.getWidth()/2,jouer.getHeight());
		historique.setBackground(Color.white); 
		historique.setFont(new Font("Arial",Font.BOLD,22));
      //  texteHistorique();
        
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
		
		JLabel hInit = new JLabel("Hauteur initiale :"); //3 
		hInit.setFont(new Font("Arial",Font.BOLD,20)) ;
		hInit.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(2/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ; 
		
		hauteurInit = new JSlider(0,courbe.getHeight(),45) ; //2
		hauteurInit.setMajorTickSpacing((int)(courbe.getHeight()/6));
		hauteurInit.setPaintLabels(true) ; 
		hauteurInit.setPaintTicks(true) ; 
		hauteurInit.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(3.5/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		
		JLabel vitesse = new JLabel("Vitesse d'affichage :"); //3
		vitesse.setFont(new Font("Arial",Font.BOLD,20)) ;
		vitesse.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(5/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ; 
		
		vitesseAffichage = new JSlider(1,11,1) ; //4 
		vitesseAffichage.setMajorTickSpacing(2);
		vitesseAffichage.setPaintLabels(true) ;
		vitesseAffichage.setPaintTicks(true) ; 
		vitesseAffichage.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(6.5/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0)));
		
		JLabel gravite = new JLabel("Gravité :"); //3
		gravite.setFont(new Font("Arial",Font.BOLD,20)) ;
		gravite.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(8/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))) ;
		
		graviteChoisie = new JSlider(1,26,1) ; //4 
		graviteChoisie.setMajorTickSpacing(5);
		graviteChoisie.setPaintLabels(true) ;
		graviteChoisie.setPaintTicks(true) ; 
		graviteChoisie.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(9.5/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(1.5/21.0))); 
		
		info = new JLabel("") ;
        info.setBounds((int)(largeur*(0.7/29.7)),(int)(hauteur*(11/21.0)),(int)(largeur*(8/29.7)),(int)(hauteur*(5/21.0)));
        info.setBackground(Outils.FOND_BLEU); 
        info.setFont(new Font("Arial",Font.BOLD,22));
		
		FenPrinc.add(courbe);
		FenPrinc.add(hauteurInit);
		FenPrinc.add(vitesseAffichage);
		FenPrinc.add(hInit);
		FenPrinc.add(vitesse);
		FenPrinc.add(equa);
        FenPrinc.add(historique);
        FenPrinc.add(histo1);
        FenPrinc.add(histo2);
        FenPrinc.add(histo3);
		FenPrinc.add(gravite);
		FenPrinc.add(graviteChoisie);
		FenPrinc.add(info);
		
		this.add(FenPrinc);
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		
		if(e.getSource() == jouer){
		
            histo3.setText(histo2.getText());
            histo2.setText(histo1.getText());
            
            if(courbe.balle!=null){
                histo1.setText(courbe.balle.getPolynome().toString());
            }
          //  texteHistorique();
            courbe.departFleche.y = hauteurInit.getValue();
			courbe.pesanteurChoisie = graviteChoisie.getValue();
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
	}
    
    /*public void texteHistorique(){
        if(histo3!=null){
            historique.setText("<html>Equations précédentes : <br>"+histo1+"<br>"+histo2+"<br>"+histo3+"</html>") ;   
        }else if(histo2!=null){
            historique.setText("<html>Equations précédentes : <br>"+histo1+"<br>"+histo2+"</html>") ;
        }else if(histo1!=null){
            historique.setText("<html>Equations précédentes : <br>"+histo1+"</html>") ;
        }else{
            historique.setText("Equations précédentes :") ;
        }
    }*/

}
