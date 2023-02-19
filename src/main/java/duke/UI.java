package duke;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

import java.util.ArrayList;

/**
 * Represents User Interface the deals with interaction with user
 */

public class UI extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/DaDuke.png"));

    private Parser parser;
    private Storage storage;
    private TaskList taskList;

    public UI() {
        this.parser = new Parser();
        this.storage = new Storage();
        ArrayList<Task> taskListArray = new ArrayList<>();
        this.storage.readStorage(taskListArray);
        this.taskList = new TaskList(taskListArray);

    }

    private String getIntroduction() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        return "Hello from\n" + logo + "\n What can I do for you?";
    }

    private void setStage(Stage stage) {
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }

    private void setScrollPane(ScrollPane scrollPane) {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    private void setAnchorPane(ScrollPane scrollPane, TextField userInput, Button sendButton) {

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * runs the user interface *
     * 
     */

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();

        setStage(stage);

        mainLayout.setPrefSize(400.0, 600.0);

        setScrollPane(scrollPane);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        setAnchorPane(scrollPane, userInput, sendButton);

        Label introText = new Label(this.getIntroduction());

        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(introText, new ImageView(duke)));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }

    private void handleUserInput() {
        String input = userInput.getText();
        Label userText = new Label(input);

        String response = this.parser.parseInput(input, this.taskList);

        this.storage.writeToFile(this.taskList);

        Label dukeText = new Label(response);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke)));

        if (response.equals("goodbye")) {
            Platform.exit();
        }
        userInput.clear();
    }

}
