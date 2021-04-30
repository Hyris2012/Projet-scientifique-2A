/** 
 * Nom de la Classe : AePlayWave 
 * Classe dans laquelle sont définies et gérées toutes les musiques
 * Classe empruntée à @author ajmas, sur gitHub et adaptée aux besoins de notre code 
 */ 
 
import java.io.File; 
import java.io.IOException; 
import javax.sound.sampled.AudioFormat; 
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.DataLine; 
import javax.sound.sampled.FloatControl; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.SourceDataLine; 
import javax.sound.sampled.UnsupportedAudioFileException; 
 
public class AePlayWave extends Thread { 
 
    private String filename;
 
    private Position curPosition;
 
    private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb 
 
    enum Position { 
        LEFT, RIGHT, NORMAL
    };
    
    /**
 * Constructeur de la classe ne prenant en compte que le nom de la musique 
 * @param wavfile	nom de la musique jouée
 */
 
    public AePlayWave(String wavfile) { 
        filename = wavfile;
        curPosition = Position.NORMAL;
    } 
 
 /**
	* Constructeur de la classe prenant en compte le nom de la musique jouée et sa position
	* @param wavfile	nom de la musique jouée
	* @param p 		position de la musique jouée 
	*/
    public AePlayWave(String wavfile, Position p) { 
        filename = wavfile;
        curPosition = p;
    } 
 
  /**
	* Méthode run
	* Mise en route de la musique et répétition de celle-ci une fois qu'elle est terminée  
	* Ne prend en compte aucun paramètre et ne renvoie rien
	*/
    public void run() { 
		
		while (true){ // pour que la musique recommence en boucle 
 
			File soundFile = new File(filename);
			if (!soundFile.exists()) { 
				System.err.println("Wave file not found: " + filename);
				return;
			} 
	 
			AudioInputStream audioInputStream = null;
			try { 
				audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			} catch (UnsupportedAudioFileException e1) { 
				e1.printStackTrace();
				return;
			} catch (IOException e1) { 
				e1.printStackTrace();
				return;
			} 
	 
			AudioFormat format = audioInputStream.getFormat();
			SourceDataLine auline = null;
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
	 
			try { 
				auline = (SourceDataLine) AudioSystem.getLine(info);
				auline.open(format);
			} catch (LineUnavailableException e) { 
				e.printStackTrace();
				return;
			} catch (Exception e) { 
				e.printStackTrace();
				return;
			} 
	 
			if (auline.isControlSupported(FloatControl.Type.PAN)) { 
				FloatControl pan = (FloatControl) auline
						.getControl(FloatControl.Type.PAN);
				if (curPosition == Position.RIGHT) 
					pan.setValue(1.0f);
				else if (curPosition == Position.LEFT) 
					pan.setValue(-1.0f);
			} 
	 
			auline.start();
			int nBytesRead = 0;
			byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
	 
			try { 
				while (nBytesRead != -1) { 
					nBytesRead = audioInputStream.read(abData, 0, abData.length);
					if (nBytesRead >= 0) 
						auline.write(abData, 0, nBytesRead);
				} 
			} catch (IOException e) { 
				e.printStackTrace();
				return;
			} finally { 
				auline.drain();
				auline.close();
			} 	 
		} 
	}
} 

