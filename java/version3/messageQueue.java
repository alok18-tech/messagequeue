package messagequeue.java.version3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

// publisher implements Runnable class because we need multiple publisher threads that why need Runnable 
public class messageQueue {

    // BlockingQueue is thread safe , we dont have to use linkedlist queue and dont need to manage thread safety
    private BlockingQueue<Object> queue = new LinkedBlockingDeque<>();
    

    public void publish(Object message){
        queue.add(message);
    }    
    
    public Object Subscribe() throws InterruptedException{
        return queue.poll(20, TimeUnit.SECONDS);
    }

   


}
