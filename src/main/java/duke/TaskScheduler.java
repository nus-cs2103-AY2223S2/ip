package duke;

import java.io.*;
import java.math.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.prefs.*;
import java.util.regex.*;
import java.util.stream.*;

//Bealdung
public class TaskScheduler {

    private final PriorityBlockingQueue<Recur> priorityQueue;

    TaskScheduler (int poolSize) {
        ExecutorService priorityJobPoolExecutor = Executors.newFixedThreadPool(poolSize);
        priorityQueue = new PriorityBlockingQueue<>(100, Comparator.comparing(Recur::getMockRemainingTime));
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

    //Change to accept Recur event later
    void addRecurringEvent() {
        priorityQueue.add(new Recur("First zoom meeting","Monday","Monday",1000));
        priorityQueue.add(new Recur("Second zoom meeting","Tuesday","Tuesday",2000));
        priorityQueue.add(new Recur("Third zoom meeting","Wednesday","Wednesday",3000));
        priorityQueue.add(new Recur("Fourth zoom meeting","Thursday","Thursday",4000));
    }





}
