package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.awt.*;

/**
 * <h1>Duke task checklist</h1>
 * The Duke program helps keep track of your ongoin task.
 * <p>
 *
 * @author Chin Jun An
 * @version 1.0
 * @since 2023
 */
public class Duke extends Application{
    /**
     * Represents a Duke program.
     */
    private Art ar;
    private Storage st;
    private Functions fn;
    private UI ui;

    private Scene scene;
    /**
     * Constructor for a Duke instance. Load tasks previously saved.
     *
     * @param fp Indicate the file path to save task scheduled
     */
    public Duke() {

    }
    public Duke(String fp) {
        this.ar = new Art();
        try {
            st = new Storage(fp);
            TaskList tl = st.load();
            this.fn = new Functions(tl, st);
        } catch (Exception e) {
            System.out.println(e);
        }
        this.ui = new UI(fn);
    }

    @Override
    public void start(Stage stage) {
        //pane to hold 3 buttons for each task
        Button todoButton = new Button("To do");
        Button deadlineButton = new Button("Deadline");
        Button eventButton = new Button("Event");
        todoButton.wrapTextProperty().setValue(true);
        deadlineButton.wrapTextProperty().setValue(true);
        eventButton.wrapTextProperty().setValue(true);
        HBox buttonLayout = new HBox();
        buttonLayout.getChildren().addAll(todoButton, deadlineButton, eventButton);

        //pane to gather user input
        Pane inputLayout = new Pane();
            //layout depending on button pressed
        todoButton.setOnMouseClicked((event) -> {
            inputLayout.getChildren().clear();
            inputLayout.getChildren().add(getTodoInput());
        });
        deadlineButton.setOnMouseClicked((event) -> {
            inputLayout.getChildren().clear();
            inputLayout.getChildren().add(getDeadlineInput());
        });
        eventButton.setOnMouseClicked((event) -> {
            inputLayout.getChildren().clear();
            inputLayout.getChildren().add(getEventInput());
        });

        //pane to display all task
        ScrollPane taskViewLayout = new ScrollPane();
            //pane to hold list of tasks
        Pane taskListPane = new Pane();

        taskViewLayout.setContent(taskListPane);
        taskViewLayout.setContent(taskListPane);
        taskViewLayout.setPrefSize(385, 200);
        taskViewLayout.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        taskViewLayout.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        taskViewLayout.setVvalue(1.0);
        taskViewLayout.setFitToWidth(true);







        //main layout
        VBox mainLayout = new VBox();
        mainLayout.getChildren().addAll(buttonLayout, inputLayout, taskViewLayout);
        mainLayout.setPrefSize(400.0, 600.0);
        scene = new Scene(mainLayout);

        //set stage
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

    }

    private VBox getTodoInput() {
        VBox vbox = new VBox();
        Label des = new Label("Task Description:");
        TextField desTextField = new TextField();
        Button addTaskButton = new Button("Add Task");
        vbox.getChildren().addAll(des, desTextField, addTaskButton);
        return vbox;
    }
    private VBox getDeadlineInput() {
        VBox vbox = new VBox();
        Label des = new Label("Task Description:");
        TextField desTextField = new TextField();
        Label end = new Label("Deadline:");
        TextField endTextField = new TextField();
        Button addTaskButton = new Button("Add Task");
        vbox.getChildren().addAll(des, desTextField, end, endTextField, addTaskButton);
        return vbox;
    }
    private VBox getEventInput() {
        VBox vbox = new VBox();
        Label des = new Label("Task Description:");
        TextField desTextField = new TextField();
        Label start = new Label("Start:");
        TextField startTextField = new TextField();
        Label end = new Label("End:");
        TextField endTextField = new TextField();
        Button addTaskButton = new Button("Add Task");
        vbox.getChildren().addAll(des, desTextField, start, startTextField, end, endTextField, addTaskButton);
        return vbox;
    }

    /**
     * Method to run the Duke program
     */
    public void run() {
        ar.show();
        boolean flag = true;
        while (flag) {
            ui.getInput();
            //could use enums here to check user input before going into switch case
            try {
                flag = ui.action();
            } catch (DukeException e) {
                System.out.println(e);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input...");
            }
        }
    }
}
