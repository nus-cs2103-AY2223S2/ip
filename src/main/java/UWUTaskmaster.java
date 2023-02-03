import data.TaskFileReaderWriter;
import data.TaskManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import ui.FXChatPane;

import java.util.Objects;

import static javafx.application.Platform.exit;


public class UWUTaskmaster extends Application {


    private TextField userInput;
    private final TaskFileReaderWriter taskReaderWriter;
    private final Parser parser;
    private final TaskManager taskManager;
    private final FXChatPane chatPane;


    public UWUTaskmaster() {

        this.taskReaderWriter = new TaskFileReaderWriter();
        this.taskManager = taskReaderWriter.loadDataFromFile();

        if (!taskReaderWriter.createTaskFile()) {
            System.out.println("Error creating data file");
        }

        this.parser = new Parser(taskManager);

        Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/profile.jpg")));
        Image taskMasterImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/uwu.jpg")));

        this.chatPane = FXChatPane.createChatPane(userImage, taskMasterImage);
        System.out.println("created chat pane");

    }


    @Override
    public void start(Stage stage) {

        stage.setTitle("UwU_TaskMaster");
        stage.setResizable(false);
        stage.setMinHeight(600);
        stage.setMinWidth(400);

        userInput = new TextField();
        userInput.setPrefWidth(325);

        Button sendButton = new Button("Send");
        sendButton.setPrefWidth(55);


        AnchorPane mainLayout = new AnchorPane();
        AnchorPane.setTopAnchor(chatPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        mainLayout.getChildren().addAll(chatPane, userInput, sendButton);
        mainLayout.setPrefSize(385, 535);

        Scene scene = new Scene(mainLayout);
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private void handleUserInput() {

        String input = userInput.getText();

        if (input.contains("bye")) {

            if (!taskReaderWriter.updateTaskFile(taskManager)) {
                System.out.println("Error updating data file");
            }
            exit();
            return;
        }

        String botResponse = parser.processInput(input);

        chatPane.addChatToChatPane(input, botResponse);
        userInput.clear();
    }
}
