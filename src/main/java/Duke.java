import duke.Deadline;
import duke.DukeException;
import duke.Event;
import duke.Parser;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Todo;
import duke.Ui;

import javafx.application.Application;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.jpeg"));

    public Duke() {
        super();
    }

    @Override
    public void start(Stage stage) {
        ui = new Ui();
        storage = new Storage("src/data/duke.txt");
        tasks = new TaskList(storage.load());
        parser = new Parser();
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");

        setLayout(stage);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(ui.introduce(), new ImageView(duke))
        );

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    public void setLayout(Stage stage) {
        setStage(stage);
        setScrollPane();
        setUiElements();
        setAnchorPlane();
    }

    public void setScrollPane() {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    public void setStage(Stage stage) {
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);
    }

    public void setUiElements() {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
    }

    public void setAnchorPlane() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply.
     * Appends the boxes to the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );

        if (parser.checkEnd(userInput.getText())) {
            sendButton.setVisible(false);
            userInput.setDisable(true);
            userInput.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
            userInput.setText("Come back soon! Pearl misses you :(");
        } else {
            userInput.clear();
        }
    }

    /**
     * Generates the bot's response to the user's input
     *
     * @param userInput the input keyed in by the user
     * @return the bot's response as a String
     */
    private String getResponse(String userInput) {
        String response = "You said: " + userInput + "\n\n";

        if (!parser.checkEnd(userInput)) {
            if (parser.checkListRequest(userInput)) {
                response += tasks.printContents();
            } else if (parser.checkMarkRequest(userInput)) {
                String[] terms = userInput.split(" ");
                int itemNo = Integer.parseInt(terms[1]);
                response += tasks.markTask(itemNo);
            } else if (parser.checkUnmarkRequest(userInput)) {
                String[] terms = userInput.split(" ");
                int itemNo = Integer.parseInt(terms[1]);
                response += tasks.unmarkTask(itemNo);
            } else if (parser.checkDeleteRequest(userInput)) {
                String[] terms = userInput.split(" ");
                int itemNo = Integer.parseInt(terms[1]);
                response += tasks.deleteTask(itemNo);
            } else if (parser.checkFindRequest(userInput)) {
                String toBeFound = userInput.substring(5);
                response += tasks.find(toBeFound);
            } else if (parser.checkIncreaseRequest(userInput)) {
                int itemNo = Integer.parseInt(userInput.substring(9));
                response += tasks.increasePriorityOfTask(itemNo);
            } else if (parser.checkDecreaseRequest(userInput)) {
                int itemNo = Integer.parseInt(userInput.substring(9));
                response += tasks.decreasePriorityOfTask(itemNo);
            } else if (parser.checkSort(userInput)) {
                response += tasks.sortTasks();
            } else if (parser.checkHelpRequest(userInput)) {
                response += ui.instruct();
            } else {
                String[] terms = userInput.split(" ");
                response += addTask(userInput, terms);
            }
        } else {
            storage.addToFile(tasks);
            response += ui.terminate();
        }
        return response;
    }

    public String addTask(String userInput, String[] terms) {
        String response = "";
        if (terms[0].equals("todo")) {
            try {
                if (terms.length == 1) {
                    String error = "The description of a todo cannot be empty";
                    throw new DukeException(error);
                } else {
                    Task newTask = new Todo(userInput.substring(5));
                    response += tasks.addTask(newTask);
                }
            } catch (DukeException err) {
                response += (err + "\n");
            }
        } else if (terms[0].equals("deadline")) {
            String[] splitBySlash = userInput.split("/");
            try {
                if (splitBySlash.length != 2) {
                    throw new DukeException("Wrong format for deadline Task");
                }
                String description = splitBySlash[0].substring(9);
                String by = splitBySlash[1].substring(3);
                Task newTask = new Deadline(description, by);
                response += tasks.addTask(newTask);
            } catch (DukeException err) {
                response += (err + "\n");
            }

        } else if (terms[0].equals("event")) {
            String[] splitBySlash = userInput.split("/");
            try {
                if (splitBySlash.length != 3) {
                    throw new DukeException("Wrong format for event Task");
                }
                String description = splitBySlash[0].substring(6);
                String from = splitBySlash[1].substring(5);
                String to = splitBySlash[2].substring(3);
                Task newTask = new Event(description, from, to);
                response += tasks.addTask(newTask);
            } catch (DukeException err) {
                response += (err + "\n");
            }
        } else {
            try {
                throw new DukeException("I don't know what that means.");
            } catch (DukeException err) {
                response += (err + "\n");
            }
        }
        return response;
    }
}
