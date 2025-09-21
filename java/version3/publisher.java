package messagequeue.java.version3;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

// publisher implements Runnable class because we need multiple publisher threads that why need Runnable 
public abstract class publisher<T> implements Runnable{

    private messageBroker broker;
    private messageQueue queue ;    
    private Queue<T> publisherQueue;
    protected String name;

    publisher(messageBroker broker, String name){
        this.broker = broker;
        this.publisherQueue = new ConcurrentLinkedQueue<>();
        this.queue = broker.getMessageQueue(name);
        this.name = name;
    }

    public void publish(T message){
        if(broker.isRunning()){
            System.out.println(name + " publishing " + message);
            publisherQueue.add(message);
        }
        
    }
    public abstract void onMessagePublish(T message);

    @Override
    public void run(){
        try {
            while(broker.isRunning() || !publisherQueue.isEmpty()){
                T message = publisherQueue.poll();
                if(message != null){
                    // publish the message to the broker or to the actual queue
                    queue.publish(message);
                    // callback has to be triggered 
                    onMessagePublish(message);
                }
                Thread.sleep(400);
            }
        } catch (InterruptedException e) {
            System.out.println("Exception occured in " + name + e.getMessage());
        }

    }
    
}
