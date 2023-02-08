import java.util.Objects;

import static javafx.application.Platform.exit;

import data.TaskFileReaderWriter;
import data.TaskManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ui.FXChatPane;
import ui.FXInputBox;
import ui.FXMainWindow;
import utils.Parser;


/**
 * GUI version of Duke with an UwU personality to help users keep track
 * of tasks such as events, deadlines and to-dos
 * @author Nicholas Lee
 */
public class UWUTaskmaster extends Application {

    private final TaskFileReaderWriter taskReaderWriter;
    private final Parser parser;
    private final TaskManager taskManager;
    private final FXMainWindow mainWindow;

    /**
     * Initialises the bot
     */
    public UWUTaskmaster() {

        // Create a TaskFileReaderWriter instance to read from the txt file
        this.taskReaderWriter = new TaskFileReaderWriter();
        this.taskManager = taskReaderWriter.loadDataFromFile();

        if (!taskReaderWriter.createTaskFile()) {
            System.out.println("Error creating data file");
        }

        // Create a Parser instance parse user input
        this.parser = new Parser(taskManager);

        Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/profile.jpg")));
        Image taskMasterImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/uwu.jpg")));

        // Instantiate UI components

        FXChatPane chatPane = FXChatPane.createChatPane(userImage, taskMasterImage);

        TextField userInput = new TextField();
        userInput.setPrefWidth(325);

        Button sendButton = new Button("Send");
        sendButton.setPrefWidth(55);

        sendButton.setOnMouseClicked((event) -> {
            this.handleUserInput(userInput.getText());
        });

        userInput.setOnAction((event) -> {
            this.handleUserInput(userInput.getText());
            userInput.clear();
        });

        FXInputBox inputBox = new FXInputBox(userInput, sendButton);
        this.mainWindow = FXMainWindow.getMainWindow(chatPane, inputBox);
        System.out.println("created chat pane");
    }


    @Override
    public void start(Stage stage) {

        stage.setTitle("UwU_TaskMaster");
        stage.setResizable(false);
        stage.setMinHeight(600);
        stage.setMinWidth(400);

        Scene scene = new Scene(mainWindow);
        stage.setScene(scene);
        stage.show();
    }

    /**
     Handles user input by checking for the keyword "bye" and if so, updates the task file and exits the application.
     If the input does not contain "bye", the input is processed by the parser to get a bot response.
     The user input and bot response are added to the main window.
     @param input The user input as a string.
     */
    private void handleUserInput(String input) {

        if (input.contains("bye")) {

            if (!taskReaderWriter.updateTaskFile(taskManager)) {
                System.out.println("Error updating data file");
            }
            exit();
            return;
        }

        String botResponse = parser.processInput(input);
        mainWindow.addDialogue(input, botResponse);
    }
}
