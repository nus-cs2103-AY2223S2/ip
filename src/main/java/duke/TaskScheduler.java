package duke;

import java.util.List;

import java.util.concurrent.PriorityBlockingQueue;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


//Bealdung
public class TaskScheduler extends TaskList<Recur> {

    private PriorityBlockingQueue<Recur> priorityQueue;
    private final List<Timeline> recurResponse;
    private final VBox dialogContainer;
    private Image dukeImage;

    TaskScheduler(List<Timeline> recurResponse,VBox dialogContainer, Image dukeImage, String input) {
        this.recurResponse = recurResponse;
        this.dialogContainer = dialogContainer;
        this.dukeImage = dukeImage;
        if (input.contains("recur")) {
            recurDialogContainer(input);
        }
    }

    TaskScheduler(List<Timeline> recurResponse,VBox dialogContainer, String input) {
        this.recurResponse = recurResponse;
        this.dialogContainer = dialogContainer;
        if (input.contains("recur")) {
            recurDialogContainerTest(input);
        }
    }

    void recurDialogContainer(String input) {
        if (input.contains("delete")) {
        } else {
            String[] splitInput = input.split(" ");
            int delay = Integer.parseInt(splitInput[splitInput.length - 1]);
            Timeline timeline =
                    new Timeline(new KeyFrame(Duration.millis(delay), e -> dialogContainer.getChildren().addAll(
                            DialogBox.getDukeDialog(input, dukeImage)
                    )));
            recurResponse.add(timeline);
            timeline.setCycleCount(Animation.INDEFINITE); // loop forever
            timeline.play();
        }
    }

    //Tests without timeline play
    void recurDialogContainerTest(String input) {
        if (input.contains("delete")) {
        } else {
            String[] splitInput = input.split(" ");
            int delay = Integer.parseInt(splitInput[splitInput.length - 1]);
            Timeline timeline =
                    new Timeline(new KeyFrame(Duration.millis(delay), e -> dialogContainer.getChildren().addAll(
                            DialogBox.getDukeDialog(input, dukeImage)
                    )));
            recurResponse.add(timeline);
            timeline.setCycleCount(Animation.INDEFINITE); // loop forever
        }
    }



    //Threading way unused. Timeline is used.
    /*
    TaskScheduler () {
        priorityQueue = new PriorityBlockingQueue<>(100, Comparator.comparing(Recur::getMockRemainingTime));
        this(20, priorityQueue);
    }
    */

    /*
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
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
    }
    */
}
