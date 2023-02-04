package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.DialogBox;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * <h1>Duke Task Manager</h1>
 * The Duke program a that the creation of tasks such as Todo, Event and
 * Deadline. The program allows to listing of those added tasks and mark them as
 * done and not done, it also allows deletion of wrongly added task.
 *
 * @author Leng Wei Cong, Justin
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("../resources/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("../resources/images/DaDuke.png"));

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
    * Constructor.
    * @param filePath the path to the text file that stores the tasks
    */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.load());
    }

    /**
     * Another constructor for JavaFX
     */
    public Duke() {
        // Empty
    }

    /**
    * Runs Duke.
    */
    public void run() {
        this.ui.showWelcome();
        this.handleRequest();
        this.ui.showExit();
    }

    /**
    * Handles the request from user input.
    */
    public void handleRequest() {
        while (true) {
            String fullcommand = ui.readCommand();
            Command command = Parser.parse(fullcommand);

            // Breaks when is an exit command
            if (command.isExit()) {
                break;
            }

            // Executes command
            command.execute(tasks, ui, storage);

            ui.showDivider();
        }
    }

    /**
    * Prints whatever the user inputs.
    * @param input the user input
    */
    public void echo(String input) {
        ui.showMessage(input);
    }

    /**
     * Starts JavaFX
     */
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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
   }

    /**
    * Iteration 1:
    * Creates a label with the specified text and adds it to the dialog container.
    * @param text String containing text to add
    * @return a label with the specified text that has word wrap enabled.
    */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                new DialogBox(userText, new ImageView(user)),
                new DialogBox(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
    * This is the main method which makes use of run method.
    * @param args Unused.
    */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
