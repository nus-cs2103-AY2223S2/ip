package duke;

import duke.command.Command;

import duke.exception.DukeException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** Class that encapsulates the Duke chatbot */
public class Duke extends Application {

    /** Relative path to the data directory used for storing tasks */
    private static final String dirPath = "./data/";
    /** Storage object to interact with storage */
    private Storage storage;
    /** TaskList object to store tasks */
    private TaskList tasks;
    /** UI object to display user interface and read user input */
    private Ui ui;
    private Image user = new Image(this.getClass().getResourceAsStream("resources/images/DaUser.png"));
    private Image colette = new Image(this.getClass().getResourceAsStream("resources/images/Colette_Neutral.png"));

    private boolean isExit = false;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructs a Duke object with a specified path
     * to the data directory used for storing tasks.
     *
     * @param dirPath Relative path to the data directory
     *                used for storing tasks.
    */
    public Duke(String dirPath) {
        this.ui = new Ui();
        this.storage = new Storage(dirPath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showErrorMessage(e);
            this.tasks = new TaskList();
        }
    }

    public Duke() {

    }

    @Override
    public void start(Stage stage) {
        //new Duke(Duke.dirPath).run();
        /*
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        helloWorld.setFont(new Font("Arial", 50));
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
        */
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        Duke duke = new Duke(Duke.dirPath);
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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        
        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(duke);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(duke);
        });

        //duke.run();
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput(Duke duke) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText(), duke));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(colette))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input, Duke duke) {
        try {
            Command c = Parser.parseCommand(input);
            this.isExit = c.isExit();
            return c.execute(duke.tasks, duke.ui, duke.storage);
        } catch (DukeException e) {
            return duke.ui.showErrorMessage(e);
        }
    }

}
