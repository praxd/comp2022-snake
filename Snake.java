import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake extends JPanel
{
    private String snake = "images/headRightSide.png";
    private String body = "images/body.png";
    
    Snake next;
    
    private int x;
    private int y;
    
    private Image image;
    
    //Referente a cabeça
    public Snake() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(snake));
        image = ii.getImage();
        x = 40;
        y = 60;
    }
    //Referente ao corpo
    public Snake(int dx, int dy){
        ImageIcon ii = new ImageIcon(this.getClass().getResource(body));
        image = ii.getImage();
        x= dx;
        y= dy;
    }
    
    public void mover(int dx, int dy) {
        x += dx;
        y += dy;
    }
    
    public void goTo(int dx, int dy){
        x = dx;
        y = dy;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }
    
    public Snake getNext(){
        return this.next;
    }
    
    public void setNext(Snake _next){
        this.next = _next;
    }
    
    public void setImage(String head){
        ImageIcon ii = new ImageIcon(this.getClass().getResource(head));
        image = ii.getImage();
    }
    
}
