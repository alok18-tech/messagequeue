package messagequeue.java.version2;
import java.util.*;

// publisher implements Runnable class because we need multiple publisher threads that why need Runnable 
public class publisher implements Runnable{

    messageBroker broker;
    messageQueue queue ;
    String name;

    publisher(messageBroker broker, String name){
        this.broker = broker;
        this.queue = broker.getMessageQueue(name);
        this.name = name;
    }

    public void publish(String message){
        if(broker.isRunning()){
            System.out.println(name + " publishing " + message);
            queue.publish(message);
        }
        
    }
    
    @Override
    public void run(){
        try {
            for(int i = 0 ;i<=5;i++){
                String message = name +  " Message " + i;
                publish(message);
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            System.out.println("Exception occured in " + name + e.getMessage());
        }

    }
    
}
