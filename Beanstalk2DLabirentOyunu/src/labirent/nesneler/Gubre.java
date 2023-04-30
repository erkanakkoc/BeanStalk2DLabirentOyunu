package labirent.nesneler;

import java.awt.Image;

import javax.swing.ImageIcon;

import labirent.Kutu;

public class Gubre extends Kutu {


	@Override
	public int toInt() {
		return 3;
		
				
	}
	
	@Override
	public String toString() {
		return Integer.toString(toInt());
	}

	public Gubre(int x, int y) {
		super(x, y);
		initGubre();
	}

	private void initGubre() {
		ImageIcon iicon = new ImageIcon("src/resources/gubre.png");
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
