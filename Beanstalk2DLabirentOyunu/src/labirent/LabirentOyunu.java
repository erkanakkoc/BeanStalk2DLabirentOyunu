package labirent;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class LabirentOyunu extends JFrame {


    public LabirentOyunu() {

        initUI();
    }

    private void initUI() {
        
        Sahne sahne = new Sahne();
        add(sahne);

        setTitle("Beanstalk 2D Labirent - Erkan Akkoç");
        
        
        setSize(500,500);
        
            
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            
            LabirentOyunu oyun = new LabirentOyunu();
            oyun.setVisible(true);
        });
    }
}
