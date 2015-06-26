import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.File;

public class Board extends JPanel implements ActionListener {
    public static String goTo = "right";
    
    private Timer timer;
    private Score score;
    
    private boolean isGameOver = false;
    private boolean isPlaying = false;
    
    private Font font;
    private int sizeSnake = 2;
    
    Snake head;
    Fila cobra = new Fila();
    
    Comida fries;
    
    
    public Board() {

        addKeyListener(new TAdapter());
        
        setFocusable(true);        
        setDoubleBuffered(true);
        setBackground(Color.WHITE);

        score = new Score();
        add(score);       
        
        fries = new Comida();
        
        head = new Snake();
        add(head);
        
        timer = new Timer(250, this);
        timer.start();
        
    }

    public void actionPerformed(ActionEvent e) {
        
        if(!isGameOver){
            cobra.add(head);
            head = new Snake(head.getX(), head.getY());
            switch(goTo){
                case "left":
                    head.mover(-30, 0);
                    head.setImage(1);
                break;
                case "right":
                    head.mover(30,0);
                    head.setImage(0);
                break;
                case "up":
                    head.mover(0, -30);
                    head.setImage(2);
                break;
                
                case "down":            
                    head.mover(0,30);
                    head.setImage(3);
                break;
            }
           
            
            if(((head.getX() <= fries.getX()+15) 
                && (head.getX() >= fries.getX()-15)) 
                &&((head.getY() <= fries.getY()+15) 
                && (head.getY() >= fries.getY()-15))){    
                    score.addScore(10);
                    fries = new Comida();
                    sizeSnake++;
            }
            
            Snake aux = cobra.getSnake();
            while (aux != null ) {                
                if((aux.getX() == head.getX()) && (aux.getY() == head.getY())){
                    isGameOver= true;
                }
                aux = aux.getNext();
            }
            
            if(Fila.length > sizeSnake ) cobra.remove();
            
            // Bateu na parede
            if((head.getX()< 0) || (head.getX()> 790))
                isGameOver= true;
            if((head.getY()< 0 || head.getY() > 590))
                isGameOver= true;
            
            repaint();
        }
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        score.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;        
        
        // Desenha a comida
        g2d.drawImage(fries.getImage(), fries.getX(), fries.getY(), null);
        
        Snake aux = cobra.getSnake();
        while (aux != null ) {
            g2d.drawImage(aux.getBody(), aux.getX(), aux.getY(), null);
            aux = aux.getNext();
        }
        g2d.drawImage(head.getHead(), head.getX(), head.getY(), null);
        
        if(isGameOver == true){
            g2d.drawString("GAME OVER mate, sorry :(", 230, 180);
            g2d.drawString("Press ENTER to try again :D ",200, 300);
            cobra = new Fila();
            sizeSnake = 2;
           // trosoba de satan cobra.setNull();
        }
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
    }
    
    
    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            // Obtém o código da tecla
            int key =  e.getKeyCode();

            switch (key){
                case KeyEvent.VK_ENTER:
                    if(isGameOver == true){
                        isGameOver = false;
                        score = new Score();
                        head = new Snake();                    
                        fries = new Comida();                    
                        goTo = "right";                    
                    }
                break;
               
                case KeyEvent.VK_LEFT:
                    if(goTo == "right") {
                        break;
                    } else {
                        goTo = "left";
                        break;
                    }
                case KeyEvent.VK_RIGHT:
                    if(goTo == "left") {
                        break;
                    } else{
                        goTo = "right";
                        break;
                    }
                case KeyEvent.VK_UP:
                    if(goTo == "down"){
                       break;
                    } else {
                        goTo = "up";
                        break;
                    }
                case KeyEvent.VK_DOWN:
                    if(goTo == "up"){
                        break;
                    } else{
                        goTo = "down";                   
                        break;
                    }
            }
            
        }
    }
   
    public boolean isEmpty(){
        if(head == null)
            return true;
        else
            return false;
    }
   
}