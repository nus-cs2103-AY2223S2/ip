package main;

import java.io.IOException;

import command.Command;
import exception.DukeException;

import javafx.scene.layout.Region;
import javafx.scene.control.Label;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Main class for the project
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList list;
    private Parser parser;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/meme.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/damith2.png"));

    /**
     * Creates an instance of Duke
     * 
     * @param filePath the relative path for the storage to store the task
     */
    public Duke() {
        this.parser = new Parser();
        this.storage = new Storage("./data/duke.txt");
        this.list = new TaskList();
        this.ui = new Ui();
    }

    /**
     * starts the programme
     */
    public String run(String input) {
        try {
            Command command = this.parser.parse(input);
            command.execute(list, ui, storage);
            return ui.showLine();
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * 
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */

    // public void handleUserInput() {
    //     Label userText = new Label(userInput.getText());
    //     Label dukeText = new Label(getResponse(userInput.getText()));
    //     dialogContainer.getChildren().addAll(
    //         DialogBox.getUserDialog(userText, new ImageView(user)),
    //         DialogBox.getDukeDialog(dukeText, new ImageView(duke))
    // );
    // userInput.clear();
    // }

    public String getResponse(String input) {
        return this.run(input);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the GUI
     */
    // @Override
    // public void start(Stage stage) {
    //     // The container for the content of the chat to scroll.
    //     scrollPane = new ScrollPane();
    //     dialogContainer = new VBox();
    //     scrollPane.setContent(dialogContainer);

    //     userInput = new TextField();
    //     sendButton = new Button("Send");

    //     AnchorPane mainLayout = new AnchorPane();
    //     mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

    //     stage.setTitle("Duke");
    //     stage.setResizable(false);
    //     stage.setMinHeight(600.0);
    //     stage.setMinWidth(400.0);

    //     mainLayout.setPrefSize(400.0, 600.0);

    //     scrollPane.setPrefSize(385, 535);
    //     scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    //     scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

    //     scrollPane.setVvalue(1.0);
    //     scrollPane.setFitToWidth(true);

    //     // You will need to import `javafx.scene.layout.Region` for this.
    //     dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

    //     userInput.setPrefWidth(325.0);

    //     sendButton.setPrefWidth(55.0);

    //     AnchorPane.setTopAnchor(scrollPane, 1.0);

    //     AnchorPane.setBottomAnchor(sendButton, 1.0);
    //     AnchorPane.setRightAnchor(sendButton, 1.0);

    //     AnchorPane.setLeftAnchor(userInput, 1.0);
        // AnchorPane.setBottomAnchor(userInput, 1.0);

        // sendButton.setOnMouseClicked((event) -> {
        //     dialogContainer.getChildren().add(DialogBox.getDialogLabel(userInput.getText()));
        //     userInput.clear();
        // });

        // userInput.setOnAction((event) -> {
        //     dialogContainer.getChildren().add(DialogBox.getDialogLabel(userInput.getText()));
        //     userInput.clear();
        // });

        // // Scroll down to the end every time dialogContainer's height changes.
        // dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // // Part 3. Add functionality to handle user input.
        // sendButton.setOnMouseClicked((event) -> {
        //     handleUserInput();
        // });

        // userInput.setOnAction((event) -> {
        //     handleUserInput();
        // });

    //     scene = new Scene(mainLayout);

    //     stage.setScene(scene);
    //     stage.show();
    // }
}
