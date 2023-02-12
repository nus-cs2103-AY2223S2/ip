package duke;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.PriorityBlockingQueue;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class TaskSchedulerTest {
    private TaskScheduler taskScheduler;
    private VBox dialogContainer;
    private String input;
    private List<Timeline> recurResponse;

    @BeforeEach
    public void setUp() {
        recurResponse = new ArrayList<>();
        dialogContainer = new VBox();
        input = "recur 10000";
        taskScheduler = new TaskScheduler(recurResponse, dialogContainer, input);
    }

    @Test
    public void testRecurDialogContainer() {
        taskScheduler.recurDialogContainerTest(input);
        assertEquals(2, recurResponse.size());
        Timeline timeline = recurResponse.get(0);
        assertEquals(Animation.INDEFINITE, timeline.getCycleCount());
        assertEquals(Duration.millis(10000), timeline.getKeyFrames().get(0).getTime());
    }

    @Test
    public void testRecurDialogContainerWithDelete() {
        String deleteInput = "recur delete";
        taskScheduler.recurDialogContainerTest(deleteInput);
        assertEquals(1, recurResponse.size());
    }

    @Test
    public void testConstructorWithInputContainingRecur() {
        taskScheduler = new TaskScheduler(recurResponse, dialogContainer, input);
        assertEquals(2, recurResponse.size());
    }

    /*
    @Test
    public void testConstructorWithInputNotContainingRecur() {
        input = "not recur input 1";
        taskScheduler = new TaskScheduler(recurResponse, dialogContainer, dukeImage, input);
        assertEquals(0, recurResponse.size());
    }
     */
}
