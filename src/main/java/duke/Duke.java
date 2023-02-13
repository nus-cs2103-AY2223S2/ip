package duke;

import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



/**
 * Class representing the Duke Application
 */
public class Duke extends Application {
    private TaskList tasks;
    private Scene scene;
    private Storage storage;
    private Ui ui;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));




    private AnchorPane createContainer() {
        //The container for the content of the chat to scroll. Designing layout.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        return mainLayout;
    }

    public void formatWindow(Stage stage, AnchorPane mainLayout) {
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
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        AnchorPane mainLayout = createContainer();
        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        // Set preferred size and title
        formatWindow(stage, mainLayout);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }


    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    @FXML
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        Circle userClip = new Circle(user.getWidth() / 2, user.getHeight() / 2, user.getWidth() / 2);
        Circle dukeClip = new Circle(duke.getWidth() / 2, duke.getHeight() / 2, duke.getWidth() / 2 );
        ImageView imageViewUser = new ImageView(user);
        ImageView imageViewDuke = new ImageView(duke);
//        imageViewUser.setClip(userClip);
//        imageViewDuke.setClip(dukeClip);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }


    public String getResponse(String input) {
        try {
            if (input.equals("bye")) {
                storage.save(tasks);
                return ui.showBye();
            }
            if (input.equals("list")) {
                return tasks.printList();
            }
            String[] parsedCommand = Parser.parseCommand(input);

            switch (parsedCommand[0]) {
                case "mark":
                    //Marks tasks as done
                    int index = Integer.parseInt(parsedCommand[1]) - 1;
                    String resMark = tasks.setDone(index);
                    storage.save(tasks);
                    return resMark;

                case "unmark":
                    //Marks tasks as done
                    int indexU = Integer.parseInt(parsedCommand[1]) - 1;
                    String resUnmark = tasks.setUndone(indexU);
                    storage.save(tasks);
                    return resUnmark;

                case "delete":
                    //Deletes tasks
                    int indexD = Integer.valueOf(parsedCommand[1]) - 1;
                    String resDel = tasks.deleteTask(indexD);
                    storage.save(tasks);
                    return resDel;

                case "find":
                    String keyword = parsedCommand[1];
                    return tasks.find(keyword);

                case "todo":
                    //Adds a new Todo to the list
                    if (parsedCommand.length == 1) {
                        throw new DukeException("The description of a todo cannot be empty");
                    }
                    Todo newT = new Todo(parsedCommand[1], false);
                    String resT = tasks.addTask(newT);
                    storage.save(tasks);
                    return resT;

                case "deadline":
                    if (parsedCommand.length == 1) {
                        throw new DukeException("The description of a deadline cannot be empty");
                    }
                    Task newD = Deadline.parseCommand(parsedCommand[1]);
                    String resD = tasks.addTask(newD);
                    storage.save(tasks);
                    return resD;

                case "event":
                    if (parsedCommand.length == 1) {
                        throw new DukeException("The description of an event cannot be empty");
                    }
                    Task newE = Event.parseCommand(parsedCommand[1]);
                    String resE = tasks.addTask(newE);
                    storage.save(tasks);
                    return resE;

                default:
                    throw new DukeException("Unknown Command");
            }
        } catch (DukeException | IOException e) {
            return (e.getMessage());
        }
//        return("Now you have " + tasks.getNumTask() + " task" + (tasks.getNumTask() > 1 ? "s " : " ") + "in the list");
    }

    public Duke(String filePath, String filename) {
        ui = new Ui();
        storage = new Storage(filePath, filename);
        try {
            tasks = new TaskList(storage.load());
            System.out.println(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
    }
}