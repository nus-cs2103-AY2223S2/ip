package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Encapsulates the related fields and behavior of Duke.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userIcon = new Image(this.getClass().getResourceAsStream("/images/UserIcon.png"));
    private Image dukeIcon = new Image(this.getClass().getResourceAsStream("/images/DukeIcon.png"));

    /**
     * Instantiates Duke.
     *
     * @param dirPath The path to the directory that the file is stored in.
     * @param fileName The name of the storage file.
     */
    public Duke(String dirPath, String fileName) {
        this.storage = new Storage(dirPath, fileName);
        this.ui = new Ui();
        this.parser = new Parser();
        try {
            tasks = new TaskList(storage.read());
        } catch (DukeException e) {
            ui.printMsg(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Stores the tasks in a file in hard drive.
     */
    public void store() {
        Task[] t = { new ToDo("a") };
        this.storage.writeToFile(this.tasks.getTaskList().toArray(t));
    }

    /**
     * Renders the starting stage to the screen.
     * @param stage The primary stage for this application
     *              for the scene to be set.
     */
    @Override
    public void start(Stage stage) {
        //set up components

        //The container for the scrolling chat.
        this.scrollPane = new ScrollPane();

        this.scrollPane.setContent(this.dialogContainer);

        this.userInput = new TextField();
        this.sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(this.scrollPane, this.userInput, this.sendButton);

        this.scene = new Scene(mainLayout);

        stage.setScene(this.scene);
        stage.show();

        //format the window to look as desired
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        this.scrollPane.setPrefSize(385, 535);
        this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        this.scrollPane.setVvalue(1.0);
        this.scrollPane.setFitToWidth(true);

        this.dialogContainer.setMinHeight(Region.USE_COMPUTED_SIZE);

        this.userInput.setPrefWidth(325.0);

        this.sendButton.setPrefWidth(55.0);

        //AnchorPane used to achieve desired layout.
        AnchorPane.setTopAnchor(this.scrollPane, 1.0);

        AnchorPane.setBottomAnchor(this.sendButton, 1.0);
        AnchorPane.setRightAnchor(this.sendButton, 1.0);

        AnchorPane.setLeftAnchor(this.userInput , 1.0);
        AnchorPane.setBottomAnchor(this.userInput, 1.0);

        //A handler on the VBox that react to its own size changing
        // and scrolling the ScrollPane down.
        //Scroll down to the end every time dialogContainer's height changes.
        this.dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));

        //handle user input
        this.sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        this.userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Creates two dialog boxes, one echoing user input
     * and the other containing Duke's reply
     * and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = this.userInput.getText();
        String dukeText = getResponse(userText);
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, this.userIcon),
                DialogBox.getDukeDialog(dukeText, this.dukeIcon)
        );
        this.userInput.clear();
    }

    /**
     * Parses the user input to get a Command, executes the returned Command and
     * catches and handles DukeException if there is any thrown.
     *
     * @param input The user input read.
     * @return A string representing Duke's response to the user input.
     */
    protected String getResponse(String input) {
        try {
            Command cmd = this.parser.parseCommand(input);
            return cmd.execute(this.tasks, this.storage, this.ui);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
