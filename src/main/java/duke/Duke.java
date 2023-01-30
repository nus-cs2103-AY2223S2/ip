package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import duke.exception.DukeException;


/**
 * Duke class.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor for Duke object.
     * @param filePath Filepath of where txt file is write into.
     * @param dirPath Directory path of where txt file is put into.
     */
    /**
    public Duke(String filePath, String dirPath) {
        ui = new Ui();
        storage = new Storage("src/main/data/duke.txt", "src/main/data");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } // change to DukeException bruh
    }**/

    /**
     * Runs the main program.
     */
    public void run() {
        ui.greet();

        while (true) {
            try {
                String answer = ui.readCommand();
                Parser parser = new Parser(answer);
                int length = tasks.getLength();
                if (answer.equals("bye")) {
                    ui.sayGoodbye();
                    try {
                        storage.store(tasks);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                if (answer.equals("list")) {
                    tasks.list();
                    continue;
                }

                if (answer.startsWith("find ")) {
                    String keyword = parser.getFindKeyword();
                    tasks.find(keyword);
                    continue;
                }
                if (answer.startsWith("mark ")) {
                    Integer index = parser.getMarkIndex(length);
                    ui.showMarked(tasks.mark(index));
                    continue;
                }
                if (answer.startsWith("unmark ")) {
                    Integer index = parser.getUnmarkIndex(length);
                    ui.showUnmarked(tasks.unmark(index));
                    continue;
                }
                if (answer.startsWith("delete ")) {
                    Integer index = parser.getDeleteIndex(length);
                    ui.showDeleted(tasks.delete(index));
                    continue;
                }
                if (answer.startsWith("todo ")) {
                    String description = parser.getTodoDescription();
                    ui.showAddTask(tasks.addTodo(answer));
                    continue;
                }
                if (answer.startsWith("deadline ")) {
                    String description = parser.getDeadlineDescription();
                    ui.askBy();
                    String askBy = ui.readCommand();
                    LocalDateTime by = parser.getDeadlineBy(askBy);
                    ui.showAddTask(tasks.addDeadline(description, by));
                    continue;
                }
                if (answer.startsWith("event ")) {
                    String description = parser.getEventDescription();
                    ui.askFrom();
                    String askFrom = ui.readCommand();
                    LocalDateTime from = parser.getEventFrom(askFrom);
                    ui.askTo();
                    String askTo = ui.readCommand();
                    LocalDateTime to = parser.getEventTo(askTo);
                    ui.showAddTask(tasks.addEvent(description, from, to));
                    continue;
                }
                throw new DukeException("I don't know that one!");
            } catch (DukeException e) {
                ui.showError(e.toString());
            }

        }


    }

    /**
     * Main method of Duke, creates a new Duke object and runs the program.
     * @param args User input arguments.
     */
    /**
    public static void main(String[] args) {
        new Duke("src/main/data/duke.txt", "src/main/data").run();

    }**/


    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // more code to be added here later
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
}

