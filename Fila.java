
public class Fila
{
    private Snake snake;
    public static int length;
    
    public Fila() {
        this.snake = null;
        length = 0;
    }        
    
    public void add(Snake node) {
        if( isEmpty() ){
        	snake = node;
        	snake.setNext(null);
        } else {
            node.setNext(snake);
            snake = node;
        }
        length++;
    }
    
    public void  remove() {
    	Snake aux = snake;
        if( aux.getNext() == null ){
        	snake = null;
            return;
        }
        while( aux.getNext() != null ){
            if( aux.getNext().getNext() == null ) {
                aux.setNext(null);
                break;
            }
            aux = aux.getNext();
        }
        length--;
    }
    
    
    public Snake getSnake() {
		return snake;
	}

    private boolean isEmpty() {
        if(snake == null){
            return true;
        }else{
            return false;
        }
    }
}
