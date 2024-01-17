package gui;

import java.io.IOException;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A custom control using FXML.
 * This control represents a task box consisting of two ImageViews to represent the type of task and the mark,
 * and one label containing description of the task.
 */
public class TaskBox extends HBox {

    private static final Image TODO_IMAGE = new Image("/images/todo.png");
    private static final Image DEADLINE_IMAGE = new Image("/images/deadline.png");
    private static final Image EVENT_IMAGE = new Image("/images/event.png");
    private static final Image MARK_IMAGE = new Image("/images/mark.png");
    private static final Image UNMARK_IMAGE = new Image("/images/unmark.png");

    @FXML
    private Label description;
    @FXML
    private ImageView displayTask;
    @FXML
    private ImageView displayMark;
    private Task task;

    /**
     * Creates a TaskBox that contains the type, mark, and description of a task.
     *
     * @param descr the task description
     * @param task  the type of task
     * @param mark  the marking of task
     */
    private TaskBox(String descr, Image task, Image mark) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/TaskBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.description.setText(descr);
        this.displayTask.setImage(task);
        this.displayMark.setImage(mark);
    }

    public static TaskBox getTaskBox(Task task) {
        Image markImage = getMarkImage(task.isMark());
        if (task instanceof Todo) {
            return new TaskBox(task.toString(), TODO_IMAGE, markImage);
        } else if (task instanceof Deadline) {
            return new TaskBox(task.toString(), DEADLINE_IMAGE, markImage);
        } else {
            return new TaskBox(task.toString(), EVENT_IMAGE, markImage);
        }
    }

    private static Image getMarkImage(Boolean isMark) {
        return isMark ? TaskBox.MARK_IMAGE : TaskBox.UNMARK_IMAGE;
    }
}
