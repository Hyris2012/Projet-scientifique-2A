/** Classe de JButton avec fond image personnalisé 
 * 
 */

import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class BoutonPersonnalise extends JButton {
	
	Toolkit T = Toolkit.getDefaultToolkit();
	private Image fond;
	private int h;
	private int l;
	
	public BoutonPersonnalise (String text, int x, int y, int l, int h, String imageUrl) {
		super(text);
		setBounds(x, y, l, h);
		this.h = h;
		this.l = l;
		
		fond = T.getImage(imageUrl).getScaledInstance(l, h, Image.SCALE_DEFAULT);
		repaint();
	}
	
	public BoutonPersonnalise (int x, int y, int l, int h, String imageUrl) {
		this("", x, y, h, l, imageUrl);
	}
	
	public void paint(Graphics g){
		g.drawImage(fond, 0, 0, null);
	}
	
	public void setFond(String imageUrl){
		fond = T.getImage(imageUrl).getScaledInstance(l, h, Image.SCALE_DEFAULT);
		repaint();
	}
	
	public void setFond(Image image){
		fond = image.getScaledInstance(l, h, Image.SCALE_DEFAULT);
		repaint();
	}
	
}

