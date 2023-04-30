package labirent.nesneler;

import java.awt.Image;

import javax.swing.ImageIcon;

import labirent.Kutu;

public class Kurek extends Kutu {


	@Override
	public int toInt() {
		return 1;
		
	}
	
	@Override
	public String toString() {
		return Integer.toString(toInt());
	}

	public Kurek(int x, int y) {
		super(x, y);
		initKurek();
	}

	private void initKurek() {
		ImageIcon iicon = new ImageIcon("src/resources/kurek.png");
		Image resim = iicon.getImage();
		setImage(resim);
	}

	public void move(int x, int y) {

		int dx = x() + x;
		int dy = y() + y;

		setX(dx);
		setY(dy);
	}

}
