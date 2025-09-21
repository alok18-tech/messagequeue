package messagequeue.java.version2;

// Subscriber implements Runnable class because we need multiple Subscriber threads that why need Runnable 
public class subscriber implements Runnable{


    messageBroker broker;
    messageQueue queue ;
    String name;

    subscriber(messageBroker broker, String name){
        this.broker = broker;
        this.queue = broker.getMessageQueue(name);
        this.name = name;
    }

    @Override
    public void run(){
        try {
            
            while (broker.isRunning()) {
                String message = queue.Subscribe();
                if(message != null){
                    System.out.println(name + " consumed " +message);
                }
                Thread.sleep(50);
            }
            System.out.println(name + " stopped Gracefully");
        } catch (InterruptedException e) {
            System.out.println("Exception occured in "+ name + " - " +e.getMessage());
            // TODO: handle exception
        }

    }
    
}
