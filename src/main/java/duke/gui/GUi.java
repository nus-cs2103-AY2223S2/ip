package duke.gui;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.command.Command;
import duke.command.ListCommand;

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

/**
 * Encapsulates the main GUI scene, creating and styling nodes as appropriate.
 */
public class GUi extends Application {

    // Window support values
    private final double minWidth = 400.0;
    private final double minHeight = 600.0;

    // JavaFX components
    private Stage stage;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button enterButton;
    private Scene scene;

    // Image files
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/pixlbot.png"));

    // Duke behaviour support
    private Storage storage;
    private Parser parser;
    private TaskList list;

    private Ui ui;
    private boolean isExit;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        initializeGUiFields();

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, enterButton);

        scene = new Scene(mainLayout);
        stage.setScene(scene);

        stage.setTitle("PixlBot");
        stage.setResizable(false);
        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);

        mainLayout.setPrefSize(minWidth, minHeight);

        styleNodes();

        // Add functionality.
        enterButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // Scroll to end whenever dialogueContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        stage.show();
        initializeBehaviourElems();
    }

    /**
     * Initializes the GUi fields
     */
    private void initializeGUiFields() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        enterButton = new Button("Enter");
    }

    /**
     * Initializes the Duke behaviour classes.
     */
    private void initializeBehaviourElems() {
        assert ui == null && storage == null && parser == null;
        isExit = false;
        ui = new Ui();
        storage = new Storage("data/Duke");
        parser = new Parser();

        welcomeUser();

        try {
            list = new TaskList(storage.load());
            if (list.getSize() != 0) {
                sayAsDuke(new ListCommand().execute(ui, list, ""));
            }
        } catch (DukeException de) {
            sayAsDuke(ui.pixlPrintException(de));
            sayAsDuke("Creating a new list...");
            list = new TaskList();
        }
    }

    /**
     * Welcomes the user.
     */
    private void welcomeUser() {
        assert dialogContainer != null;
        assert ui != null;
        dialogContainer.getChildren().add(new Label(ui.open()));
    }

    /**
     * Saves data and exits.
     */
    private void exitProgram() {
        assert isExit;
        assert storage != null;
        assert list != null;
        assert ui != null;
        assert stage != null;

        try {
            storage.save(list.getList());
        } catch (DukeException de) {
            sayAsDuke(ui.pixlPrintException(de));
        }
        stage.close();
    }

    /**
     * Displays the given text as Duke.
     * @param text The message to be displayed.
     */
    private void sayAsDuke(String text) {
        assert dialogContainer != null;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(new Label(text), new ImageView(duke)));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        assert dialogContainer != null;
        assert userInput != null;

        if (userInput.getText().isEmpty() || userInput.getText().isBlank()) {
            return;
        }

        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));

        if (isExit) {
            dialogContainer.getChildren()
                    .add(DialogBox.getUserDialog(userText, new ImageView(user)));
            userInput.clear();
            exitProgram();
            return;
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        assert parser != null;

        try {
            Command command = parser.parse(input);
            isExit = parser.getIsExit();
            return command.execute(ui, list, input);
        } catch (DukeException de) {
            return ui.pixlPrintException(de);
        }
    }

    /**
     * Handles node styling.
     */
    private void styleNodes() {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        enterButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(enterButton, 1.0);
        AnchorPane.setRightAnchor(enterButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

}
