package messagequeue.java.version1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

// publisher implements Runnable class because we need multiple publisher threads that why need Runnable 
public class messageQueue {

    // BlockingQueue is thread safe , we dont have to use linkedlist queue and dont need to manage thread safety
    private BlockingQueue<String> queue = new LinkedBlockingDeque<>();
    private volatile boolean running = true;

    public void publish(String message){
        queue.add(message);
    }    
    
    public String Subscribe() throws InterruptedException{
        return queue.poll(20, TimeUnit.SECONDS);
    }

    // trigerred by the user
    public void stop(){
        running = false;
    }

    public boolean isRunning(){
        return running;
    }


}
