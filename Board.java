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
    
    Snake head;
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
        
        Snake aux = head;
        while( aux.getNext() != null){
            add(aux);
            aux = aux.getNext();
        }
        
        add(fries);
        
        timer = new Timer(180, this);
        timer.start();
    }


    public void paint(Graphics g) {
        super.paint(g);
        score.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;        

        int size = snakeSize();
        
        Snake aux = head;
        for(int i = 0; i < size; i++){
            g2d.drawImage(aux.getImage(),aux.getX(),aux.getY(),this);
            aux = aux.getNext();
        }
        
        g2d.drawImage(fries.getImage(),fries.getX(),fries.getY(),this);
        
        if(isGameOver == true){
            g2d.drawString("GAME OVER mate, sorry :(", 230, 180);
            g2d.drawString("Press ENTER to try again :D ",200, 300); 
        }
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
    }
    
    public void movesLikeJagger(int _x, int _y, int size){// v
        int currentX = _x, currentY = _y, x, y;
        Snake aux = head;
        
        for(int i =1; i< size; i++){
            aux.getNext();
            x = aux.getX();
            y = aux.getY();
            aux.goTo(currentX, currentY);
            currentX = x;
            currentY = y;
        }
        
    }
    
   
    public void actionPerformed(ActionEvent e) {
        
        if(!isGameOver){// V        
            int size = snakeSize(), currentX, currentY;
            switch(goTo){
                case "left":
                currentX = head.getX();
                currentY = head.getY();
                head.mover(-30, 0);
                movesLikeJagger(currentX, currentY, size);
                break;
                
                case "right":
                currentX = head.getX();
                currentY = head.getY();
                head.mover(30, 0);
                movesLikeJagger(currentX, currentY,size);
                break;
                
                case "up":
                currentX = head.getX();
                currentY = head.getY();
                head.mover(0, -30);
                movesLikeJagger(currentX, currentY, size);
                break;
                
                case "down":
                currentX = head.getX();
                currentY = head.getY();
                head.mover(0,30);
                movesLikeJagger(currentX,currentY, size);
                break;
                               
            }
        
            if(((head.getX() <= fries.getX()+15) 
                && (head.getX() >= fries.getX()-15)) 
                &&((head.getY() <= fries.getY()+15) 
                && (head.getY() >= fries.getY()-15))){    
                    score.addScore(10);
                    fries = new Comida();
                    ate();
            }
            
            if((head.getX()< 0) || (head.getX()> 790))
                isGameOver= true;
            if((head.getY()< 0 || head.getY() > 590))
                isGameOver= true;
            //**
            Snake aux;
            if(size > 3){
                aux = head.getNext();
                for(int i=1; i < size; i++){
                    if(((head.getX() <= aux.getX()+5) && (head.getX() >= aux.getX()-5)) &&
                       ((head.getY() <= aux.getY()+5) && (head.getY() >= aux.getY()-5)     ) ){
                            isGameOver = true;
                    }
                    aux = aux.getNext();
                }
               
            }
            repaint();
        }
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
                if(goTo == "right")
                    break;
                else{
                    head.setImage("images/headLeftSide.png");
                    goTo = "left";
                    break;
                }
                    
                case KeyEvent.VK_RIGHT:
                if(goTo == "left")
                    break;
                else{
                    head.setImage("images/headRightSide.png");
                    goTo = "right";
                    break;
                }
                    
                case KeyEvent.VK_UP:
                if(goTo == "down")
                    break;
                else{
                    head.setImage("images/headUpside.png");
                    goTo = "up";
                    break;
                }
                    
                case KeyEvent.VK_DOWN:
                if(goTo == "up")    
                    break;
                else{
                    head.setImage("images/headDownside.png");
                    goTo = "down";
                    break;
                }
            }
            
        }
    }
    
    public int snakeSize(){
        int positions;
        
        if(isEmpty() == true){
            positions = 0;
            return positions;
        }else{
            positions = 1;
        }
        
        Snake aux = head;
        
        if(aux.getNext() == null){
            positions = 1;
        }
        else{
            do{
                aux = aux.getNext();
                positions++;
            }while(aux.getNext() != null);
        }
        
        return positions;
                  }
    
    public boolean isEmpty(){
        if(head == null)
            return true;
        else
            return false;
    }
    
    public void ate(){
        Snake aux = head;
        while(aux.getNext() != null){
            aux = aux.getNext();
        }
        aux.setNext(new Snake((aux.getX() +28),aux.getY()));
    }
    
}