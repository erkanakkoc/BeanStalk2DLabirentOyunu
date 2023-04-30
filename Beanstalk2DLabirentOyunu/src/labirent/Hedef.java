package labirent;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Hedef extends Hucre {

    public Hedef(int x, int y) {
        super(x, y);
        
        initAlan();
    }
    
    private void initAlan() {

        ImageIcon iicon = new ImageIcon("src/resources/hedef.png");
        Image resim = iicon.getImage();
        setImage(resim);
    }
}
