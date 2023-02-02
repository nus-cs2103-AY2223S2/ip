package duke;

import java.util.Scanner;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** Handles input/output for Duke. */
public class Ui {

    // commonly-displayed elements
    public static final String NEWL = "\n";
    public static final String LOGO = " ____        _        \n" + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";

    // duke components
    private Duke duke;
    private StringBuilder message;

    // javafx ui components
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Initializes a Ui object.
     *
     * @param duke The Duke instance that this Ui object belongs to
     */
    public Ui(Duke duke) {
        this.duke = duke;
        this.message = new StringBuilder();
    }

    /**
     * Initializes the main Scene to be displayed on the Stage.
     *
     * @param stage The stage to display the created scene on
     */
    public void initializeStage(Stage stage) {
        // create components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        userInput = new TextField();
        sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();

        // add and position children
        scrollPane.setContent(dialogContainer);
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // adjust stage
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        // configure size for components
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

        // display scene
        this.scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        this.initializeHandlers();
    }

    /** Prints a welcome message. */
    public void showWelcome() {
        this.addToMessage("Hello from");
        this.addToMessage(LOGO);
        this.addToMessage("Hello! I'm Duke.");
        this.addToMessage("What can I do for you?");
        this.displayMessage();
    }

    /**
     * Prints the stored message to the user.
     */
    public void displayMessage() {
        DialogBox newChild = DialogBox.makeDukeDialog(this.message.toString());
        this.dialogContainer.getChildren().add(newChild);
        this.clearMessage();
    }

    /** Clears the stored message. */
    public void clearMessage() {
        this.message.setLength(0);
    }

    /**
     * Appends a string to the stored message.
     *
     * @param toAdd The string to be appended to the stored message
     */
    public void addToMessage(String toAdd) {
        this.message.append(toAdd);
        this.message.append(NEWL);
    }

    /** Initialize all JavaFX event handlers */
    private void initializeHandlers() {
        // on-click handler for sendButton
        this.sendButton.setOnMouseClicked((event) -> {
            this.handleUserInput();
        });

        // "enter" on-press handler for userInput
        this.userInput.setOnAction((event) -> {
            this.handleUserInput();
        });

        // dialogContainer handler to auto-scroll
        this.dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    /** Handles the event where the user inputs a command into userInput */
    private void handleUserInput() {
        String userText = userInput.getText();
        // don't handle if user gives a blank input
        if (userText.equals("")) {
            return;
        }
        DialogBox newChild = DialogBox.makeUserDialog(userText);
        this.dialogContainer.getChildren().add(newChild);
        this.duke.run(userText);
        this.userInput.clear();
    }
}
