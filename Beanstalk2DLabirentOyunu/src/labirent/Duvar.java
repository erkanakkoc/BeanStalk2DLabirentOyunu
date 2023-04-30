package labirent;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Duvar extends Hucre {

    private Image resim;
    
    public Duvar(int x, int y) {
        super(x, y);
        
        initDuvar();
    }
    
    private void initDuvar() {
        
        ImageIcon iicon = new ImageIcon("src/resources/duvar.png");
        resim = iicon.getImage();
        setImage(resim);
    }
}
