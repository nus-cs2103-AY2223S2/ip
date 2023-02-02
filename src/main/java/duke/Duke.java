package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.commands.CommandInput;
import duke.exceptions.CommandExecutionError;
import duke.gui.DialogBox;
import duke.tasks.TaskList;
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
import javafx.stage.Stage;

/**
 * Main class where duke is initialized and runs.
 */
public class Duke extends Application {
    private TaskList tasks;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/tiger.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/owl.jpg"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Duke() {
        tasks = new TaskList();
        try {
            Storage.loadFromFile(tasks);
        } catch (IOException e) {
            // Ui.showLoadingError();
        }
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
       dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

       userInput.setPrefWidth(325.0);

       sendButton.setPrefWidth(55.0);

       AnchorPane.setTopAnchor(scrollPane, 1.0);

       AnchorPane.setBottomAnchor(sendButton, 1.0);
       AnchorPane.setRightAnchor(sendButton, 1.0);

       AnchorPane.setLeftAnchor(userInput , 1.0);
       AnchorPane.setBottomAnchor(userInput, 1.0);

       dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
    
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userTextLabel = new Label(userInput.getText());
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(userTextLabel, new ImageView(user)));

        Command command = CommandInput.getCommandFromInput(userInput.getText(), tasks);
        userInput.clear();
        try {
            command.execute(this);
        } catch (CommandExecutionError e) {
            sendResponse("Couldn't execute command :/ \n" + e.toString());
        }
    }

    public void sendResponse(String dukeResponse) {
        Label dukeTextLabel = new Label(dukeResponse);
        dialogContainer.getChildren().add(
            DialogBox.getDukeDialog(dukeTextLabel, new ImageView(duke)));
    }
}
