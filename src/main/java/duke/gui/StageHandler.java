package duke.gui;

import duke.commands.Command;
import duke.commands.CommandInput;
import duke.exceptions.CommandExecutionError;
import duke.tasks.TaskList;
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
public class StageHandler {
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/tiger.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/owl.jpg"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    protected TaskList tasks;

    public StageHandler(Stage stage, TaskList tasks) {
        this.tasks = tasks;
    // The container for the content of the chat to scroll.
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

    // Formatting the window to look as expected
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

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
    
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    public void displayMessage(Profile profile, String message) {
        Label messageLabel = new Label(message);
        DialogBox dialogBox;
        switch(profile) {
        case USER:
            dialogBox = new DialogBox(messageLabel, new ImageView(userImage), false);
            break;
        case DUKE:
            dialogBox = new DialogBox(messageLabel, new ImageView(dukeImage), true);
            break;
        default:
            dialogBox = new DialogBox(messageLabel, new ImageView(dukeImage), true);
        }
        
        dialogContainer.getChildren().add(dialogBox);
    }

    private void handleUserInput() {
        String commandInput = userInput.getText();
        displayMessage(Profile.USER, commandInput);
        Command command = CommandInput.getCommandFromInput(commandInput, tasks);
        userInput.clear();
        try {
            command.execute();
            displayMessage(Profile.DUKE, command.getResponse());
        } catch (CommandExecutionError e) {
            displayMessage(Profile.DUKE, "Couldn't execute command :/ \n" + e.toString());
        }
    }
}