package messagequeue.java.version3;

// Subscriber implements Runnable class because we need multiple Subscriber threads that why need Runnable 
public  abstract class subscriber<T> implements Runnable{


    private messageBroker broker;
    private messageQueue queue ;
    protected String name;

    subscriber(messageBroker broker, String name){
        this.broker = broker;
        this.queue = broker.getMessageQueue(name);
        this.name = name;
    }

    public abstract void onMessageReceived(T message);


    @Override
    public void run(){
        try {
            
            while (broker.isRunning()) {
                T message = (T) queue.Subscribe();
                if(message != null){
                    // trigger the callback
                    onMessageReceived(message);                    
                }
                Thread.sleep(500);
            }
            System.out.println(name + " stopped Gracefully");
        } catch (InterruptedException e) {
            System.out.println("Exception occured in "+ name + " - " +e.getMessage());            
        }

    }
    
}
