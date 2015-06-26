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
    private String headImageR = "images/headRightSide.png";
    private String headImageL = "images/headLeftSide.png";
    private String headImageU = "images/headUpside.png";
    private String headImageD = "images/headDownside.png";
    private String bodyImage = "images/body.png";
    
    Snake next;
    
    private int x;
    private int y;
    
    private Image body, head,image;
    
    //Referente a cabeça
    public Snake() {
        x = 40;
        y = 60;
        carregarImagem();
        
    }
    //Referente ao corpo
    public Snake(int dx, int dy){
        x= dx;
        y= dy;
        carregarImagem();
    }
    
    public void carregarImagem(){
        ImageIcon ii = new ImageIcon(this.getClass().getResource(bodyImage));
        body = ii.getImage();
        ii = new ImageIcon(this.getClass().getResource(headImageR));
        head = ii.getImage();
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

    public Image getBody() {
        return body;
    }
    
    public Image getHead() {
        return head;
    }
    
    public Snake getNext(){
        return this.next;
    }
    
    public void setNext(Snake _next){
        this.next = _next;
    }
    
    public void setImage(String head) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(head));
        image = ii.getImage();
    }

    
}
