import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

public class OyunEkrani extends JFrame{

    public OyunEkrani(String string) {

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
    OyunEkrani MainScreen=new OyunEkrani("Uzay Oyunu");

    MainScreen.setResizable(false);
    MainScreen.setFocusable(false);
    MainScreen.setSize(800, 600);
    MainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        
    Oyun game=new Oyun();
    game.requestFocus();
    game.addKeyListener(game);
    
    game.setFocusable(true);
    game.setFocusTraversalKeysEnabled(false); 
    MainScreen.add(game);
    MainScreen.setVisible(true);
    }
}