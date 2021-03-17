/*
 *
 * qq problèmes :
 * histoire d'échelle pour que tout soit toujours contenu entier dans le panel
 * 
 * il faut avoir accès aux arraylist de la balle -> passer en public ou écrire un get 
 * il faut connaitre la racine positive du polynôme, càd là où elle touche le sol (attribut nommé 'atterissage' ici en attendant)
 * 
 * 
 * 
 * 
 */


public class PanelTraj extends JPanel implements ActionListener {
	
	private Balle balle;
	//private ImageIcon fond;
	public Timer time;
	public int tps;
	public ArrayList<Double> Xparcourus;
	public ArrayList<Double> Yparcourus;
	
	public PanelTraj(Balle balle) {
		super();
		setLayout(null);
		setBounds(50, 50, 500, 700);
		setBackground(Color.black);
		
		time = new Timer (100, this);
	
		balle = new Balle();
		
		Xparcourus = new <Double> ArrayList();
		Yparcourus = new <Double> ArrayList();
		
	}
	
	public String toString(){
		//juste pour le principe
	}
	
	public void lancerBalle(Balle balle){
		this.balle = balle;
		time.start();
	}
	
	public void paint(Graphics g){
		
		// appelé lors de l'appui sur le bouton 'lancer' -> càd dans actionPerformed de FenetreJeu 
		
		// dessine la trajectoire jusqu'à --> tps 
		g.setColor(Color.white);
		drawPolyline(balle.valeurX.toArray(), balle.valeurY.toArray());
		
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(!aterrie()){
			Xparcourus.add(balle.getValeurX().get(tps)
			paint();
		}else{
			new FenetreFinJeu(balle.aterrissage, balle.max);	// en supposant que la fenetre affiche l'altitude max atteinte et la longueur parcourue, qui seraient tout deux attributs de balle 
		}
		
	}
	
	public boolean atterrie(){
		// doit déterminer si la balle a atteri 
		boolean b = ((Xparcourus.get(Xparcourus.size()-1) >= balle.atterrissage);
		return b; 
		
	}
}

