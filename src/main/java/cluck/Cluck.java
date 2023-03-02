package cluck;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import cluck.commands.Command;
import cluck.commands.ExitCommand;
import cluck.exceptions.CluckException;
import cluck.parser.Parser;
import cluck.storage.Storage;
import cluck.tasklist.TaskList;
import cluck.ui.Ui;


/**
 * Cluck class is the main Class and module for Cluck.
 */
public class Cluck extends Application {
    private final TaskList taskList;
    private final Ui ui;
    private boolean isRunning = true;
    private final Storage storage;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    /**
     * Instantiates a new Cluck with no arguments for JavaFx Application use.
     */
    public Cluck() {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage("C:/Users/User/OneDrive - National University of Singapore/"
                + "NUS/Y2S2/ip/data/CluckSave.txt");
    }

    /**
     * Cluck class contains and instance of TaskList, Ui, and Storage.
     * These classes are the building blocks of Cluck.
     *
     * @param filePath path of saved path as String
     */
    public Cluck(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = storage.readSave();
    }

    /**
     * Starts cluck instance such that it loads saved data and
     * begins taking in user commands.
     */
    public void run() {
        ui.greetUser();
        String userInput;
        String response;
        Command currCommand;

        while (isRunning) {
            userInput = ui.readInput();
            try {
                currCommand = Parser.commandFactory(userInput);
                response = currCommand.execute(taskList);
                if (currCommand instanceof ExitCommand) {
                    isRunning = false;
                }
            } catch (CluckException e) {
                response = e.getMessage();
            }
            ui.printResponse(response);
        }
        storage.writeSave(taskList);
    }

    @Override
    public void start(Stage stage) {
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
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

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
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Cluck("C:/Users/User/OneDrive - National University of Singapore/"
                + "NUS/Y2S2/ip/data/CluckSave.txt").run();
    }
}
