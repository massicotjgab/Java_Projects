import java.awt.*;
import java.applet.*;

public class Chrono1 extends Applet implements Runnable {
	private Thread chronometre;
	int speed = 100;
	private int ds = 0; // dixi�me de seconde

	public void init() {
		setBackground(Color.white);
		setForeground(Color.blue);
		setFont(new Font("SansSerif", Font.BOLD, getSize().height));
	}

	public void start() {
// Au d�but de l'applet, cr�ation et d�marrage du chronom�tre
		if (chronometre == null) {
			chronometre = new Thread(this);
			chronometre.start();
		}
	}

	public void run() {
		while (chronometre != null) {
			repaint();
			ds++;
			try {
				chronometre.sleep(speed);
			} catch (InterruptedException e) {
			}
		}
	}

	public void stop() {
// A la fin de l'applet, arr�t du chronometre
		chronometre.stop();
		chronometre = null;
	}

	public void paint(Graphics g) {
// Dessine le temps �coul� sous forme de h:mm:ss:d
		String S = ds / 36000 + ":" + (ds / 6000) % 6 + (ds / 600) % 10 + ":" + (ds / 100) % 6 + (ds / 10) % 10 + ":"
				+ ds % 10;
//affichage centr�
		FontMetrics fm = g.getFontMetrics();

		int largeur = fm.stringWidth(S);
		int hauteur = fm.getHeight();
		int x = (getSize().width - largeur) / 2;
		int y = (getSize().height - hauteur) / 2;
		g.drawString(S, x, y + hauteur - fm.getDescent());
	}
}