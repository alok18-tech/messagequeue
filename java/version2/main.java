package messagequeue.java.version2;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// involve callback and different type of messages and messageQueue
/*
    1 - There can be multiple Publisher and Subscribe Threads.(Achived using executorService and BlockingQueue for thead safety)
    2 - User should be able to stop the publisher subscriber. After that no, messages should be published and subscribed.
        (Achived using volatile keyword and running flag).
    3 - There should be a provision to run the client code when message is published sucessfully and 
        message is received sucessully .(Callback)
    4 - There can be different types of message sent over this libray e.g. Order-Updates , Notification - updates etc.
        Order-Updates Should not be delayed if there is high inflow of Notification-Updates and vice versa.    

 */
public class main {

    public static void main(String[] args) {        

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        messageBroker broker = new messageBroker();
        publisher orderUpdatePublisher = new publisher(broker, "order-Update");
        publisher notificationUpdatePublisher = new publisher(broker, "notification-Update");
        subscriber orderUpdateSubscriber = new subscriber(broker, "order-Update");
        subscriber notificationUpdateSubscriber = new subscriber(broker, "notification-Update");

        executorService.execute(orderUpdatePublisher);
        executorService.execute(notificationUpdatePublisher);
        executorService.execute(orderUpdateSubscriber);
        executorService.execute(notificationUpdateSubscriber);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
            
        broker.stop();
        executorService.shutdown();
    }    
}
