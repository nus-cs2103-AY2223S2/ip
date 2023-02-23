package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import task.TaskList;

import java.io.IOException;

public class Duke extends Application {
    private final static String SAVED_PATH = "data/tasks.txt";
    private Ui ui;
    private Storage storage;
    private final Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));


    /**
     * Constructs a Duke object for program to run.
     * Initialises the ui, storage, parser and tasklist to be used for the program.
     *
//     * @param filePath Path in which the file is located relative to project root.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(SAVED_PATH);
        TaskList taskList;
        ui.showWelcomeMessage();

        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.showLoadingErrorMessage();
            taskList = new TaskList();
        }

        parser = new Parser(storage, taskList);
    }

    @Override
    public void start(Stage stage) {
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

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (DukeException | IOException e) {
                e.printStackTrace();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (DukeException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() throws DukeException, IOException {
        String userText = userInput.getText();
        assert userText.length() >= 0;
        String dukeText = getResponse(userInput.getText());
        System.out.println(dukeText);
        if (dukeText == null) {
            dukeText = "Nononono";
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    String getResponse(String input) {
        // The response to an input command
        try {
            String output = parser.readInput(input);
            return output;
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Accepts user input and checks whether program terminated or continues running.
     */
    public void run() {
        boolean isContinueRunning = true;

        while (isContinueRunning) {
            try {
                String input = ui.requestUserInput();
                assert input.length() <= 0 : "User input should not be empty";
                String output = parser.readInput(input);
                if (output.isEmpty()) {
                    isContinueRunning = false;
                }
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
