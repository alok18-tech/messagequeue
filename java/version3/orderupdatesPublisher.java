package messagequeue.java.version3;

// publisher implements Runnable class because we need multiple publisher threads that why need Runnable 
public class orderupdatesPublisher<T> extends publisher<T>{


    orderupdatesPublisher(messageBroker broker, String name){
        super(broker, name);
    }

    @Override
    public void onMessagePublish(T message){
        System.out.println(name + " custom published "  + message);
    }
    
}
