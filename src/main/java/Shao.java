
import java.io.FileNotFoundException;

import commands.Command;
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
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class Shao extends Application {

    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList tasklist;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public static void main(String[] args) {
        new Shao().run(args);
    }

    @Override
    public void start(Stage stage) {
        initServices();
        setGUILayout(stage);
        ui.greetUser(dialogContainer, storage);
    }

    private void initServices() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
        tasklist = new TaskList();
    }

    private void setGUILayout(Stage stage) {
        // Step 1. Formatting the window to look as expected.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        // Load the chat bot and user image
        try {
            storage.loadAvatars();
        } catch (FileNotFoundException ex) {
            Label errorLabel = new Label("Something went wrong. Please restart the app.");
            errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
            dialogContainer.getChildren().add(errorLabel);
        }

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
        // sendButton.setOnAction(new EventHandler<ActionEvent>() {
        // @Override
        // public void handle(ActionEvent e) {
        // dialogContainer.getChildren().add(new Label("Hi"));
        // }
        // });
    }

    /** Run the program until it terminates */
    public void run(String... args) {
        initServices();

        storage.getFile(tasklist, parser, ui);

        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = parser.parseInput(fullCommand);
            ui.printRowDivider();
            c.execute(ui, parser, storage, tasklist);
            ui.printRowDivider();
            isExit = c.isExit();
        }
        ui.cleanUp();
    }

}
