import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Random;
public class Comida extends JPanel
{
    private int x;private int y;
   
    private Image image;
   
    private String potato = "images/fries.png";
   
    public Comida(){
       ImageIcon ii = new ImageIcon(this.getClass().getResource(potato));
       image = ii.getImage();
       x = randomXSpot();
       y = randomYSpot();
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int randomXSpot(){
        Random random = new Random();
        int randX = 5+random.nextInt(720);
        return randX;
    }
    
    public int randomYSpot(){
        Random random = new Random();
        int randY = 5+random.nextInt(540);
        return randY;
    }
    
    public Image getImage(){
        return image;
    }
    
}
