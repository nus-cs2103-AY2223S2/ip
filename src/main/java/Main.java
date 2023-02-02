import commands.Command;

import parser.Parser;

import storage.Storage;

import tasklist.TaskList;

import ui.Ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList tasklist;

    private boolean isExit = false;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Call upon the launch of Shao application.
     * 
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        initServices();
        setGUILayout(stage);
        ui.greetUser(dialogContainer, storage);

        storage.getFile(tasklist, parser, ui, storage, dialogContainer);
    }

    private void initServices() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
        tasklist = new TaskList();
    }

    /**
     * Initialise GUI components and user input events.
     * 
     * @param stage
     */
    private void setGUILayout(Stage stage) {
        // Step 1. Formatting the window to look as expected.
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

        // Step 2. Formatting the window to look as expected
        stage.setTitle("Shao");
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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (!userInput.getText().isBlank()) {
                    readCommand(stage);
                }
            }
        });

        userInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent k) {
                if (k.getCode().equals(KeyCode.ENTER)) {
                    if (!userInput.getText().isBlank()) {
                        readCommand(stage);
                    }
                }
            }
        });

    }

    /**
     * Read and parse user input to command until user terminates the application.
     * 
     * @param stage
     */
    public void readCommand(Stage stage) {
        String fullCommand = userInput.getText();
        assert fullCommand.trim().length() > 0 : "Input cannot be empty";

        userInput.setText("");

        ui.sendInput(dialogContainer, storage, fullCommand);
        Command c = parser.parseInput(fullCommand);
        c.execute(ui, parser, storage, tasklist, dialogContainer);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        isExit = c.isExit();

        if (isExit) {
            Platform.exit();
        }

    }

}
