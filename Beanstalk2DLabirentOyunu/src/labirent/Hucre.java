package labirent;

import java.awt.Image;

public class Hucre {

    private final int BOSLUK = 20;

    private int x;
    private int y;
    private Image resim;

    public Hucre(int x, int y) {
        
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return resim;
    }

    public void setImage(Image img) {
    	resim = img;
    }

    public int x() {
        
        return x;
    }

    public int y() {
        
        return y;
    }

    public void setX(int x) {
        
        this.x = x;
    }

    public void setY(int y) {
        
        this.y = y;
    }

    public boolean solTemasVarmi(Hucre hucre) {
        
        return x() - BOSLUK == hucre.x() && y() == hucre.y();
    }

    public boolean sagTemasVarmi(Hucre hucre) {
        
        return x() + BOSLUK == hucre.x() && y() == hucre.y();
    }

    public boolean ustTemasVarmi(Hucre hucre) {
        
        return y() - BOSLUK == hucre.y() && x() == hucre.x();
    }

    public boolean altTemasVarmi(Hucre hucre) {
        
        return y() + BOSLUK == hucre.y() && x() == hucre.x();
    }
}
