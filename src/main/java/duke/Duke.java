package duke;

import duke.command.Command;
import javafx.animation.PauseTransition;
import javafx.application.Application;
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
import javafx.util.Duration;

/**
 * The ChatBot Duke.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private boolean isExit;


    /**
     * The Duke object's constructor.
     */
    public Duke() {
        this.ui = new Ui();
        this.isExit = false;
        try {
            this.storage = new Storage("data/tasks.txt");
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Starts running Duke.
     */
    public String run(String command) {
        try {
            Command c = Parser.parse(command);
            this.isExit = c.isExit();
            return c.execute(this.tasks, this.ui, this.storage);
        } catch (DukeException e) {
            return this.ui.showError(e.getMessage());
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        this.scrollPane = new ScrollPane();
        this.dialogContainer = new VBox();
        this.scrollPane.setContent(this.dialogContainer);

        this.userInput = new TextField();
        this.sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(this.scrollPane, this.userInput, this.sendButton);

        this.scene = new Scene(mainLayout);

        stage.setScene(this.scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
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

        // You will need to import `javafx.scene.layout.Region` for this.
        this.dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        this.userInput.setPrefWidth(325.0);

        this.sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(this.scrollPane, 10.0);

        AnchorPane.setBottomAnchor(this.sendButton, 1.0);
        AnchorPane.setRightAnchor(this.sendButton, 5.0);

        AnchorPane.setLeftAnchor(this.userInput , 5.0);
        AnchorPane.setBottomAnchor(this.userInput, 1.0);

        Label dukeText = new Label(this.ui.showWelcome());
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(dukeText, new ImageView(this.user))
        );

        //Part 3. Add functionality to handle user input.
        this.sendButton.setOnMouseClicked((event) -> {
            this.handleUserInput(stage);
        });

        this.userInput.setOnAction((event) -> {
            this.handleUserInput(stage);
        });

        //Scroll down to the end every time dialogContainer's height changes.
        this.dialogContainer.heightProperty().addListener((observable) -> this.scrollPane.setVvalue(1.0));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput(Stage stage) {
        String input = this.userInput.getText();
        Label userText = new Label(input);
        Label dukeText = new Label(this.run(input));
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(this.user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(this.duke))
        );
        this.userInput.clear();

        //Reused from https://stackoverflow.com/questions/27334455
        if (this.isExit) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> stage.close());
            delay.play();
        }
    }
}
