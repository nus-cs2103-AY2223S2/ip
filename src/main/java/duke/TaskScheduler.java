package duke;

import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Schedueles recur tasks through the timeline class.
 * @author Muhammad Reyaaz
 * @version %I% %G%
 * @since 11
 */
public class TaskScheduler extends TaskList<Recur> {

    private final List<Timeline> recurResponse;
    private final VBox dialogContainer;
    private Image dukeImage;

    /**
     * Constructor to initialize the TaskScheduler with the input string, list of Timelines, VBox, and Duke's Image.
     * It calls the recurDialogContainer method if the input string contains the word "recur".
     * @param recurResponse  list of Timelines for the recurring tasks
     * @param dialogContainer VBox container to store the dialog boxes for the recurring tasks
     * @param dukeImage       Image of Duke to display in the dialog box
     * @param input           input string
     */
    TaskScheduler(List<Timeline> recurResponse,VBox dialogContainer, Image dukeImage, String input) {
        this.recurResponse = recurResponse;
        this.dialogContainer = dialogContainer;
        this.dukeImage = dukeImage;
        if (input.contains("recur")) {
            recurDialogContainer(input);
        }
    }

    /**
     * Constructor to initialize the TaskScheduler with the input string, list of Timelines, and VBox.
     * It calls the recurDialogContainerTest method if the input string contains the word "recur".
     * @param recurResponse  list of Timelines for the recurring tasks
     * @param dialogContainer VBox container to store the dialog boxes for the recurring tasks
     * @param input           input string
     */
    TaskScheduler(List<Timeline> recurResponse,VBox dialogContainer, String input) {
        this.recurResponse = recurResponse;
        this.dialogContainer = dialogContainer;
        if (input.contains("recur")) {
            recurDialogContainerTest(input);
        }
    }

    /**
     * Creates and schedules the dialog box for a recurring task.
     * @param input input string
     */
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
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }
    }

    /**
     * Creates and schedules the dialog box for a recurring task without timeline play for junit test
     * @param input input string
     */
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
            timeline.setCycleCount(Animation.INDEFINITE);
        }
    }
}
