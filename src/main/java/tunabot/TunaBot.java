package tunabot;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tunabot.exceptions.InputException;


/**
 * Main class for TunaBot
 */
public class TunaBot extends Application {
    private static final Scanner s = new Scanner(System.in);
    private static TaskList tasks;
    private static boolean toExit = false;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private final Storage storage;
    private final Ui ui;

    /**
     * Initializes a TunaBot with the given save path.
     */
    public TunaBot() {

        Path savePath = Paths.get("data", "save.txt");
        ui = new Ui();
        storage = new Storage(savePath);
        try {
            tasks = storage.load();
        } catch (IOException e) {
            ui.saveFileProblem();
        }
        assert tasks != null : "Storage should have loaded either an existing "
            + "task list from save file or initialised a new task list";
    }

    /**
     * Runs TunaBot
     */
    public void run() {
        ui.greeting();
        while (!toExit) {
            try {
                String input = s.nextLine();
                ui.line();
                Parser.parse(input, tasks);
                toExit = input.equals("bye");
            } catch (InputException e) {
                ui.printErrorMessage(e);
            } catch (DateTimeParseException e) {
                ui.printDateTimeFormatError();
            }
            ui.line();
        }
        storage.save(tasks);
    }

    /**
     * Main class to initialize and run TunaBot
     */
    public static void main(String[] args) {
        new TunaBot().run();
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
        stage.setTitle("TunaBot");
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
        Label welcome = new Label(Ui.greeting());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcome, new ImageView(duke)));
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }
    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        Label userText = new Label(input);
        Label dukeText = new Label(getResponse(input));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
        if (input.equals("bye")) {
            storage.save(tasks);
            Platform.exit();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        try {
            return Parser.parse(input, tasks);
        } catch (InputException e) {
            return e.getMessage();
        }
    }
}
