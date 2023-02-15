package duke;

import java.util.Objects;
import java.util.Scanner;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.DialogBox;
import duke.ui.Ui;

/**
 * Class to represent a Duke instance.
 * Members include a Storage object for file I/O, a TaskList object to store
 * current list of tasks and a Ui object for output to user.
 */
public class Duke extends Application {

    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    private Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.jpg")));
    private Image duke = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.jpg")));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Initializes new Duke instance with associated Storage, TaskList and Ui members.
     */
    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage();
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Performs ui operations according to parsed user inputs.
     * If user inputs "bye", store list of tasks and exit.
     * If user inputs "list", print current tasks.
     * If user inputs "mark NUMBER" or "unmark NUMBER", update the status of that task number.
     * If user inputs a task, add to current tasks.
     */
    public void run() {
        try {
            ui.greet();
            storage = new Storage();
            tasks = new TaskList(storage.load());
            Scanner sc = new Scanner(System.in);
            Parser parser = new Parser(sc.nextLine());
            while (!parser.getCommand().equals("bye")) {
                ui.printLine();

                if (parser.getCommand().equals("list")) {
                    ui.listTasks(tasks);
                } else {
                    try {
                        switch (parser.getCommand()) {
                        case "mark":
                            ui.markTask(tasks, parser);
                            break;
                        case "unmark":
                            ui.unmarkTask(tasks, parser);
                            break;
                        case "todo":
                            ui.addToDo(tasks, parser);
                            break;
                        case "deadline":
                            ui.addDeadline(tasks, parser);
                            break;
                        case "event":
                            ui.addEvent(tasks, parser);
                            break;
                        case "delete":
                            ui.deleteTask(tasks, parser);
                            break;
                        case "find":
                            ui.findAndListTasks(tasks, parser);
                            break;
                        default:
                            throw new DukeException("I'm sorry, but I don't know what that means :-(");
                        }
                    } catch (DukeException e) {
                        System.out.printf("     ☹ OOPS!!! %s%n", e.getMessage());
                    }
                }
                ui.printLine();
                parser.updateInput(sc.nextLine());
            }
            ui.farewell();
            storage.store(tasks);
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns string response for GUI (same rules as run()).
     *
     * @param input Command entered by user.
     * @return String response to display in GUI.
     */
    public String getResponse(String input) {
        assert storage != null : "Storage assertion error in Duke";
        assert tasks != null : "Tasklist assertion error in Duke";
        assert ui != null : "Ui assertion error in Duke";
        StringBuilder response = new StringBuilder();
        try {
            Parser parser = new Parser(input);
            if (parser.getCommand().equals("list")) {
                response.append(ui.listTasks(tasks));
            } else {
                switch (parser.getCommand()) {
                case "bye":
                    storage.store(tasks);
                    response.append(ui.getSaulBye());
                    break;
                case "mark":
                    response.append(ui.markTask(tasks, parser));
                    break;
                case "unmark":
                    response.append(ui.unmarkTask(tasks, parser));
                    break;
                case "todo":
                    response.append(ui.addToDo(tasks, parser));
                    break;
                case "deadline":
                    response.append(ui.addDeadline(tasks, parser));
                    break;
                case "event":
                    response.append(ui.addEvent(tasks, parser));
                    break;
                case "delete":
                    response.append(ui.deleteTask(tasks, parser));
                    break;
                case "find":
                    response.append(ui.findAndListTasks(tasks, parser));
                    break;
                default:
                    response.append("I'm sorry, but I don't know what that means.");
                }
            }
        } catch (DukeException e) {
            response.append("☹ OOPS!!! ");
            response.append(e.getMessage());
            e.printStackTrace();
        }
        return response.toString();
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );

        // code to exit on "bye". Not functioning unless within getResponse.
        if (userText.equals("bye")) {
            Platform.exit();
        }
        userInput.clear();
    }

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

        //Step 2. Formatting the window to look as expected
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
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }
}
