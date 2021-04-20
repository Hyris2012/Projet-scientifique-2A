import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class FenetreFinJeu extends JFrame implements ActionListener {
	
	private JButton retourFenAccueil;
	
	public FenetreFinJeu(){
		setLayout(null);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(500, 400, 800, 300);
		
		JLabel etiquette = new JLabel("hum hum");
		etiquette.setBounds(20, 20, 700, 100);
		etiquette.setFont(new Font("Arial",Font.BOLD,18)) ;
		etiquette.setBackground(Outils.FOND_BLEU) ;
		
		JPanel texte = new JPanel();
		texte.setBounds(0, 0, 800, 300);
		texte.setLayout(null);
		texte.setBackground(Outils.FOND_BLEU);
		
		texte.add(etiquette);		
		add(texte) ;
		setVisible(true);
		
	}
	
	public FenetreFinJeu(String type, String affichage){
		super(type);
		setLayout(null);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(500, 400, 800, 300);
		this.setResizable(false);
		JPanel texte = new JPanel();
		texte.setBounds(0, 0, 800, 300);
		texte.setLayout(null);
		texte.setBackground(Outils.FOND_BLEU);
		
		JLabel coord = new JLabel(affichage);
		coord.setBounds(180, 20, 700, 100);
		coord.setFont(new Font("Arial",Font.BOLD,18)) ;
		coord.setBackground(Outils.FOND_BLEU) ;
		
		/*JLabel conv = new JLabel("Un pixel vaut approximativement 5.3*10^-6 m"); 
		conv.setBounds(100, 60, 700, 100);
		conv.setFont(new Font("Arial",Font.BOLD,18)) ;
		conv.setBackground(Outils.FOND_BLEU) ;
		*/
		
		retourFenAccueil = new JButton ("Retour");
		retourFenAccueil.setFont(new Font("Serif",Font.BOLD,20)) ;
		retourFenAccueil.setBounds(325,150,150,35);
		retourFenAccueil.setForeground(Color.white);
		retourFenAccueil.setBackground(new Color (90,90,90)); 
		retourFenAccueil.addActionListener(this);
		
		
		
		texte.add(coord);
		//texte.add(conv) ;
		add(texte);
		add(retourFenAccueil);
		setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == retourFenAccueil){
			this.setVisible(false);
			new FenetreAccueil();
		}
	}
	
}
