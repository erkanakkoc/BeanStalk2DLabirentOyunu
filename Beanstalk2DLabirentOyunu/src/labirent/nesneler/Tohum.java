package labirent.nesneler;

import java.awt.Image;

import javax.swing.ImageIcon;

import labirent.Kutu;

public class Tohum extends Kutu {


	@Override
	public int toInt() {
		return 2;
		
	}
	
	@Override
	public String toString() {
		return Integer.toString(toInt());
	}

	public Tohum(int x, int y) {
		super(x, y);
		initTohum();
	}

	private void initTohum() {
		ImageIcon iicon = new ImageIcon("src/resources/tohum.png");
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
