package messagequeue.java.version1;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// This is the basic code interviewer expects for upto 2 year of experience , learn from youtube - LevelUpwithSuhail
/* Only Includes below two implementation 
    1 - There can be multiple Publisher and Subscribe Threads.(Achived using executorService and BlockingQueue for thead safety)
    2 - User should be able to stop the publisher subscriber. After that no, messages should be published and subscribed.
        (Achived using volatile keyword and running flag).
*/
public class main {

    public static void main(String[] args) {        

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        messageQueue queue = new messageQueue();
        publisher pub1 = new publisher(queue, "pub1");
        publisher pub2 = new publisher(queue, "pub2");
        subscriber sub1 = new subscriber(queue, "sub1");
        subscriber sub2 = new subscriber(queue, "sub2");

        executorService.execute(pub1);
        executorService.execute(pub2);
        executorService.execute(sub1);
        executorService.execute(sub2);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
            
        queue.stop();
        executorService.shutdown();
    }    
}
