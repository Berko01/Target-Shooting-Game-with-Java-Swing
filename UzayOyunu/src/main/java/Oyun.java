
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class Fire {

    private int x;
    private int y;

    public Fire(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}





public class Oyun extends JPanel implements KeyListener, ActionListener {

    Timer timer = new Timer(5, this);

    private int timeSpent = 0;
    private int fireSpent = 0;
    private BufferedImage image;

    private ArrayList<Fire> fires = new ArrayList<Fire>();
    private int fireMoveY = 5;
    private int ballX = 0;
    private int ballMoveX = 2;
    private int shipX = 0;
    private int shipMoveX = 20;

    public Oyun() {
        Timer timer = new Timer(1, this);
        try {
            image = ImageIO.read(new FileImageInputStream(new File("ship.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }

        setBackground(Color.black);

        timer.start();

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();

        if (k == KeyEvent.VK_A) {
            if (shipX > 0) {
                shipX -= shipMoveX;
            }
        } else if (k == KeyEvent.VK_D) {
            if (shipX < 730) {
                shipX += shipMoveX;
            }
        } else if (k == KeyEvent.VK_CONTROL) {
            fires.add(new Fire(shipX + 15, 490));
            fireSpent++;
        }

    }

    public void deleteFire() {
        for (Fire fire : fires) {
            if (fire.getY() < -10) {
                fires.remove(fire);
            }
        }
    }

    public void firedUp() {
        for (Fire fire : fires) {
            fire.setY(fire.getY() - fireMoveY);
        }

    }

    public void moveBall() {
        ballX += ballMoveX;
        if (ballX >= 800) {
            ballMoveX = -ballMoveX;
        } else if (ballX <= 0) {
            ballMoveX = -ballMoveX;
        }
    }
    
    public boolean controlntersection(){
    for(Fire fire : fires){
        if(new Rectangle(fire.getX(),fire.getY(),10,20).intersects(new Rectangle(ballX,0,20,20)))
            return true;
        }
    return false;
    }
    


    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

       
        firedUp();
        moveBall();
        

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        deleteFire();
        super.paint(g);

        g.setColor(Color.RED);
        g.fillOval(ballX, 0, 20, 20);
        g.drawImage(image, shipX, 490, image.getWidth() / 10, image.getHeight() / 10, this);

        g.setColor(Color.BLUE);
        for (Fire fire : fires) {
            g.fillRect(fire.getX(), fire.getY(), 10, 20);
        }
        
        if(controlntersection() == true){
            String message = "Tebrikler hedefi vurdun oyun bitti.";
            timer.stop();
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
    }

    @Override
    public void repaint() {
        super.repaint();
    }

}
