package labirent.nesneler;

import java.awt.Image;

import javax.swing.ImageIcon;

import labirent.Kutu;

public class Su extends Kutu {


	@Override
	public int toInt() {
		return 4;
		
	}
	
	@Override
	public String toString() {
		return Integer.toString(toInt());
	}

	public Su(int x, int y) {
		super(x, y);
		initSu();
	}

	private void initSu() {
		ImageIcon iicon = new ImageIcon("src/resources/su.png");
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
