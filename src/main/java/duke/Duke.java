package duke;
import duke.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.DialogBox;

/**
 * Main Duke class
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

    /**
     * Duke initialisation method.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    public Duke() {}

    public void saveFile() {
        Parser.saveFile("data/duke.txt");
    }

<<<<<<< HEAD
=======
    public String dukeStartup() {
        return ui.startUpMsg();
    }

    /**
     * Run the loop of the chat bot.
     */
    public void run() {
        boolean isRunning = true;
        //listReader();
        while (isRunning) {
            try {
                isRunning = Parser.talk();
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }

>>>>>>> branch-A-JavaDoc
    public static void main(String[] args) {
        //new Duke("data/duke.txt").run();
    }

    public String startUpMsg() {
        String name = "todo bot";
        String helloMsg = "";
        helloMsg += "Hello from " + name + "\n";
        helloMsg += "------------------------------------\n";
        helloMsg += "I can help you take care of your daily todos :)\n";
        helloMsg += "There are 3 types of tasks I can handle.\n";
        helloMsg += "------------------------------------\n";
        helloMsg += "1. normal todo, format: \n";
        helloMsg += "   todo task\n";
        helloMsg += "2. deadline, format: \n";
        helloMsg += "   deadline task /by yyyy-mm-dd\n";
        helloMsg += "3. event, format: \n";
        helloMsg += "   event task /from yyyy-mm-dd /to yyyy-mm-dd\n";
        helloMsg += "------------------------------------\n";
        helloMsg += "Here are some operations you can perform on the tasks\n";
        helloMsg += "1. mark taskNumber\n";
        helloMsg += "2. unmark taskNumber\n";
        helloMsg += "3. delete taskNumber\n";
        helloMsg += "4. find tasks\n";
        helloMsg += "5. snooze taskNumber newTaskDate\n";
        helloMsg += "6. list\n";
        helloMsg += "------------------------------------\n";
        return helloMsg;
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

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

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
        try {
            Label userText = new Label(userInput.getText());
            Label dukeText = new Label(Parser.operationHandler(userInput.getText()));
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userInput.getText(), user),
                    DialogBox.getDukeDialog(userInput.getText(), duke)
            );
            userInput.clear();
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return input;
    }

    public String startUpMsg() {
        String name = "todo bot";
        String helloMsg = "";
        helloMsg += "Hello from " + name + "\n";
        helloMsg += "------------------------------------\n";
        helloMsg += "I can help you take care of your daily todos :)\n";
        helloMsg += "There are 3 types of tasks I can handle.\n";
        helloMsg += "------------------------------------\n";
        helloMsg += "1. normal todo, format: \n";
        helloMsg += "   todo task\n";
        helloMsg += "2. deadline, format: \n";
        helloMsg += "   deadline task /by yyyy-mm-dd\n";
        helloMsg += "3. event, format: \n";
        helloMsg += "   event task /from yyyy-mm-dd /to yyyy-mm-dd\n";
        helloMsg += "------------------------------------\n";
        helloMsg += "Here are some operations you can perform on the tasks\n";
        helloMsg += "1. mark taskNumber\n";
        helloMsg += "2. unmark taskNumber\n";
        helloMsg += "3. delete taskNumber\n";
        helloMsg += "4. find tasks\n";
        helloMsg += "5. snooze taskNumber newTaskDate\n";
        helloMsg += "6. list\n";
        helloMsg += "------------------------------------\n";
        return helloMsg;
    }

}
