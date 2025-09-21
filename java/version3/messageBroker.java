package messagequeue.java.version3;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Whenever we have to brought down publisher and subscriber model , we brought down broker not queue . 
// that why taking out running flag from message queue to messageBroker class
public class messageBroker {
    
    private Map<String , messageQueue> queueMap = new ConcurrentHashMap<>();
    private volatile boolean running = true;

    public messageQueue getMessageQueue(String name){
        
        return queueMap.computeIfAbsent( name,x -> new messageQueue());
    }
    // trigerred by the user
    public void stop(){
        running = false;
    }

    public boolean isRunning(){
        return running;
    }

}
