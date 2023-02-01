package duke;

import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;
    static protected final String DATA_DIR = "data/";
    static protected final String DATA_FILENAME = "duke.txt";
    static protected final String WELCOME_MESSAGE = "Hello! I'm Duke\n\tWhat can I do for you?";
    static protected final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    // For javaFX UI
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream(("/images/DaDuke.png")));
    public Duke() {
        this(DATA_DIR + DATA_FILENAME);
    }
    /**
     * Returns a Duke object.
     * @param filePath The file path where the data file is located.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the task bot on the command line.
     * [DEPRECATED]
     */
    public void run() {
        ui.showNormalMessage(WELCOME_MESSAGE);
        Scanner inputScanner = new Scanner(System.in);
        String inputStr = inputScanner.nextLine().trim();
        while (!inputStr.equals("bye")) {
            try {
                parser.parseString(inputStr, tasks, ui);
                storage.save(tasks);
            } catch (BadCommandException | DukeException e) {
                ui.showErrorMessage(e.getMessage());
            }
            inputStr = inputScanner.nextLine().trim();
        }
        inputScanner.close();
        ui.showNormalMessage(EXIT_MESSAGE);
    }

    @Override
    public void start(Stage stage) {
        // Setting up components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // Formatting window
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setHeight(600.0);
        stage.setWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setMinHeight(535);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Styling ScrollPane
        dialogContainer.setBackground(new Background(
                new BackgroundFill(
                        Color.rgb(40, 40, 40),
                        CornerRadii.EMPTY,
                        Insets.EMPTY)
                )
        );

        // Add functionality
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        // Scroll down to the end every time dialogContainer's height changes
        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));
    }

    /**
     * Handles the text input given by the user through the GUI.
     */
    private void handleUserInput() {
        String userInputStr = userInput.getText().trim();
        Label userText = new Label(userInputStr);
        String dukeResponseStr;
        try {
            dukeResponseStr = parser.parseString(userInputStr, tasks, ui);
            storage.save(tasks);
        } catch (BadCommandException | DukeException e) {
            dukeResponseStr = ui.showErrorMessage(e.getMessage());
        }
        Label dukeText = new Label(dukeResponseStr);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    public static void main(String[] args) {
        new Duke(DATA_DIR + DATA_FILENAME).run();
    }
}
