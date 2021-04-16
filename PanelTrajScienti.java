import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class PanelTrajScienti extends PanelTraj {
	FenetreScientifique fenS;
	
	public PanelTrajScienti(FenetreScientifique fenS, int x, int y, int l, int h){
		super(fenS, x, y, l, h);
		this.fenS = fenS;
		
		// initialisation de l'unique fond de ce mode scientifique
		fond = new Decor(null, Color.black, T.getImage("einstein.jpg").getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
	}
	
	public void lancerBalle(Balle balle){
		super.lancerBalle(balle);
		
		fenS.info.setText("Equation de la trajectoire : " + balle.getPolynome().toString());
		
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		repaint();
	}

}
