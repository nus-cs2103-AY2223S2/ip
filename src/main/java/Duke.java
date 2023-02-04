import command.Command;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import task.TaskManager;
import ui.WelcomeUI;
import util.DukeException;
import util.FileManager;
import java.util.Scanner;
import util.Parser;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Represents the main logic flow of the chatbot.
 */
public class Duke extends Application {
    private Scanner input;
    private TaskManager taskManager;
    private FileManager fileManager;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/ryo.png"));


    public Duke() {}

    /**
     * Initialises the Scanner to recieve user input
     * and the WelcomeUI to be displayed upon start up.
     * @param input
     * @param welcomeUI
     */
    public Duke(Scanner input, WelcomeUI welcomeUI) {
        System.out.println(welcomeUI);
        this.input = input;
        this.taskManager = new TaskManager();
        this.fileManager = new FileManager();
        this.fileManager.loadDataToArrayList(this.taskManager);
        this.parser = new Parser(fileManager);
    }

    /**
     * Driver method to run the chatbot.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                Command command = parser.parse(input);
                command.executeCommand(taskManager);
                isExit = command.isExit();
            } catch (DukeException e) {
                System.out.println(e);
            } catch (NullPointerException e) {
                isExit = false;
            }
        }
    }

    public static void main(String[] args) {
        WelcomeUI welcome = new WelcomeUI();
        Scanner input = new Scanner(System.in);
        Duke chat = new Duke(input, welcome);

        chat.run();
        input.close();
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
        stage.setMinHeight(500.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 500.0);

        scrollPane.setPrefSize(stage.getWidth(), stage.getHeight() - 60);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        dialogContainer.setSpacing(50.0);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Part 3. Add functionality to handle user input.
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
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
