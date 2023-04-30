package labirent;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Oyuncu extends Hucre {

    public Oyuncu(int x, int y) {
        super(x, y);

        initOyuncu();
    }

    private void initOyuncu() {

        ImageIcon iicon = new ImageIcon("src/resources/adam.png");
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
