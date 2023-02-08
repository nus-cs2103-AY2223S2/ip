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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class StageHandler {

    protected TaskList tasks;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/tiger.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/owl.jpg"));

    private ScrollPane scrollPane = new ScrollPane();
    private VBox dialogContainer = new VBox();
    private TextField userInput = new TextField();
    private Button sendButton = new Button("Send");
    private AnchorPane mainLayout = new AnchorPane();
    private Scene scene = new Scene(mainLayout);

    public StageHandler(Stage stage, TaskList tasks) {
        this.tasks = tasks;

        GuiSpecs.initSpecs(scrollPane, mainLayout, stage, scene, sendButton, userInput, dialogContainer);

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
            String response = command.execute();
            displayMessage(Profile.DUKE, response);
        } catch (CommandExecutionError e) {
            displayMessage(Profile.DUKE, "Couldn't execute command :/ \n" + e.toString());
        }
    }
}
