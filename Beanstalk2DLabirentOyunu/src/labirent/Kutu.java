package labirent;


public class Kutu extends Hucre {

	public Kutu(int x, int y) {
		super(x, y);

	}

	public void move(int x, int y) {

		int dx = x() + x;
		int dy = y() + y;

		setX(dx);
		setY(dy);
	}

	public int toInt() {
		return 0;
	}
}
