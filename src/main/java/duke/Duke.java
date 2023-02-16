package duke;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Class encapsulating the entire Duke application.
 */
public class Duke extends Application {
    protected static final String DATA_DIR = "data/";
    protected static final String DATA_FILENAME = "duke.txt";
    protected static final String WELCOME_MESSAGE = "Hello! I'm Duke!\nWhat can I do for you?";
    protected static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    protected static final String EXIT_COMMAND = "bye";
    private static final double STAGE_WIDTH = 500;
    private static final double STAGE_HEIGHT = 750;
    private static final double SEND_BUTTON_WIDTH = 55;
    private final Storage storage;
    private TaskList tasks;
    private final Parser parser;
    private final Ui ui;

    // For javaFX UI
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/cartoon_user.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream(("/images/cartoon_robot.png")));

    public Duke() {
        this(DATA_DIR + DATA_FILENAME);
    }

    /**
     * Returns a Duke object.
     *
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
        assert tasks != null : "tasks should not be null";
    }

    @Override
    public void start(Stage stage) {
        // Setting up components
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

        // Formatting window
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setHeight(STAGE_HEIGHT);
        stage.setWidth(STAGE_WIDTH);

        mainLayout.setPrefSize(STAGE_WIDTH, STAGE_HEIGHT);

        scrollPane.setPrefSize(STAGE_WIDTH, STAGE_HEIGHT - 75);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setMinHeight(STAGE_HEIGHT - 75); // Fit to scrollPane

        userInput.setPrefWidth(STAGE_WIDTH - SEND_BUTTON_WIDTH);

        sendButton.setPrefWidth(SEND_BUTTON_WIDTH);

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
        dialogContainer.setPadding(new Insets(0, 0, 10, 0));

        // Add functionality
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(stage);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(stage);
        });
        // Scroll down to the end every time dialogContainer's height changes
        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));

        // Send an initial welcome message
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(
                new Label(WELCOME_MESSAGE),
                new ImageView(duke))
        );
    }

    /**
     * Handles the text input given by the user through the GUI.
     */
    private void handleUserInput(Stage stage) {
        String userInputStr = userInput.getText().trim();
        if (userInputStr.equalsIgnoreCase(EXIT_COMMAND)) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(new Label(EXIT_MESSAGE), new ImageView(duke))
            );
            stage.close();
            return;
        }
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
}
