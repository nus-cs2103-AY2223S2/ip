package duke;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GuiHandler extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userHappy = new Image(this.getClass().getResourceAsStream("/images/happy.png"));
    private Image userSmile = new Image(this.getClass().getResourceAsStream("/images/smile.png"));
    private Image userNeutral = new Image(this.getClass().getResourceAsStream("/images/neutral.png"));
    private Image userScared = new Image(this.getClass().getResourceAsStream("/images/scared.png"));
    private Image userCmi = new Image(this.getClass().getResourceAsStream("/images/cmi.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke2.png"));
    private Parser parser = new Parser();
    private CommandHandler commandHandler = new CommandHandler();
    private final Storage storage = new Storage("./data/duke.txt");
    private TaskList tasks = this.storage.loadData();
    private List<String> previousUserInputs = new ArrayList<>();
    private int upKeyCount = 0;
    //This is used to keep track of how many previous commands the user wants; ie:
    // how many times the user presses the up key. Resets to 0 once user inputs a valid command.



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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 2.1. Add Duke starting dialog
        dialogContainer.getChildren().add(DialogBox.getStartingDialog(duke));


        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        userInput.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.UP)) {
                handleUpKeyPress();
            }
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void handleUserInput() {
        Image user;
        int numTasks = tasks.size();
        if (numTasks <= 1) {
            user = userHappy;
        } else if (numTasks == 2) {
            user = userSmile;
        } else if (numTasks == 3) {
            user = userNeutral;
        } else if (numTasks == 4) {
            user = userScared;
        } else {
            user = userCmi;
        }

        Label userText = new Label("You: \n" + userInput.getText());
        Label dukeText = new Label("Duke: \n" + getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );

        previousUserInputs.add(0, userInput.getText());
        upKeyCount = 0;

        if (userInput.getText().equalsIgnoreCase("bye")) {
            Platform.exit();
            System.exit(0);
        }
        userInput.clear();
    }


    private String getResponse(String input) {
        Command command = this.parser.parseCommand(input);
        String response;
        response = this.commandHandler.handleCommand(command, tasks, storage);
        return response;
    }

    private void handleUpKeyPress() {
        userInput.clear();
        try {
            userInput.setText(previousUserInputs.get(upKeyCount));
            userInput.end();
            upKeyCount++;
        } catch (IndexOutOfBoundsException e) {
            //No further command history
            //No need to throw error
        }
    }

}
