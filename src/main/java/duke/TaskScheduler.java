package duke;

import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;


//Bealdung
public class TaskScheduler {

    private PriorityBlockingQueue<Recur> priorityQueue;

    TaskScheduler (int poolSize, PriorityBlockingQueue<Recur> priorityQueue) {
        ExecutorService priorityJobPoolExecutor = Executors.newFixedThreadPool(poolSize);
        this.priorityQueue = priorityQueue;
        //priorityQueue = new PriorityBlockingQueue<>(100, Comparator.comparing(Recur::getMockRemainingTime));
        ExecutorService priorityJobScheduler = Executors.newSingleThreadExecutor();
        priorityJobScheduler.execute(() -> {
            while (true) {
                try {
                    Recur nextScheduledEvent = priorityQueue.take();
                    priorityJobPoolExecutor.execute(nextScheduledEvent);
                    System.out.println(nextScheduledEvent.getDescription());
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
    }

    /*
    TaskScheduler () {
        priorityQueue = new PriorityBlockingQueue<>(100, Comparator.comparing(Recur::getMockRemainingTime));
        this(20, priorityQueue);
    }
    */

    //Change to accept Recur event later
    void addRecurringEvent(Recur recurEvent) {
        priorityQueue.add(recurEvent);
        /*
        priorityQueue.add(new Recur("First zoom meeting","Monday","Monday",1000));
        priorityQueue.add(new Recur("Second zoom meeting","Tuesday","Tuesday",2000));
        priorityQueue.add(new Recur("Third zoom meeting","Wednesday","Wednesday",3000));
        priorityQueue.add(new Recur("Fourth zoom meeting","Thursday","Thursday",4000));
        */
    }





}
