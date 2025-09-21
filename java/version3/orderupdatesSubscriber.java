package messagequeue.java.version3;

// Subscriber implements Runnable class because we need multiple Subscriber threads that why need Runnable 
public class orderupdatesSubscriber<T> extends subscriber<T>{


    orderupdatesSubscriber(messageBroker broker, String name){
        super(broker, name);
    }

    @Override
    public void onMessageReceived(T message){
        System.out.println(name + " custom received " + message);
    }
    
}
