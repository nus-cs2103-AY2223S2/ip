package duke;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * {@code GUI} class that manages the graphic user interface
 * of Duke program
 */
public class GUI extends UI {

    /**
     * Application's window
     */
    private ScrollPane scrollPane;

    /**
     * Container for Node Objects
     */
    private VBox dialogContainer;

    /**
     * Wraps around input from user
     */
    private TextField userInput;

    /**
     * Allows user to press send button to send
     * command
     */
    private Button sendButton;

    /**
     * Scene used to perform on stage
     */
    private Scene scene;

    /**
     * User's icon
     */
    private Image user = new Image(this.getClass().getResourceAsStream("/DaUser.png"));

    /**
     * Duke's icon
     */
    private Image duke = new Image(this.getClass().getResourceAsStream("/DaDuke.png"));

    /**
     * dukeProgram to access and modify taskList and storage
     */
    private Duke dukeProgram = new Duke(System.getProperty("user.dir"));

    /**
     * Sets up the required components for Duke program
     * @param stage Default stage provided to run application
     */
    public void startUpProgram(Stage stage) {
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

        formatWindow(stage, mainLayout);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    /**
     * Formats the application window
     * @param stage Default stage provided to run application
     * @param pane Application's main window
     */
    private void formatWindow(Stage stage, Pane pane) {
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        pane.setPrefSize(400.0, 600.0);

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
    }

    /**
     * performs necessary actions when user presses the send button
     * @param taskList keeps tracks of a list of tasks
     * @param storage stores data of tasks in a file
     */
    public void runEvent(TaskList taskList, Storage storage) {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(taskList, storage);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(taskList, storage);
        });

    }

    /**
     * handles inputs from user and performs the necessary
     * actions
     * @param taskList keeps tracks of a list of tasks
     * @param storage stores data of tasks in a file
     */
    public void handleUserInput(TaskList taskList, Storage storage) {
        String text = userInput.getText();
        Label userText = new Label(text);
        String dukeReply = getResponse(text, taskList, storage);
        Label dukeText = new Label(dukeReply);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );

        userInput.clear();
    }

    /**
     * Returns response to user's input
     * @param input user's input
     * @param taskList keeps tracks of a list of tasks
     * @param storage stores data of tasks in a file
     * @return output for duke
     */
    private String getResponse(String input, TaskList taskList, Storage storage) {
        try {
            Parser parser = new Parser(input);
            Command cmdHandler = parser.parseArgs();
            String output = cmdHandler.execArgs(taskList);

            if (output.equals("bye")) {
                Platform.exit();
                System.exit(0);
            }

            storage.editFile(taskList.loadTaskList());
            return output;
        } catch (DukeException err) {
            String errMsg = UI.BORDERLINE
                    + err.errorMessage.trim() + "\n"
                    + UI.BORDERLINE;
            return errMsg;
        }
    }
}
