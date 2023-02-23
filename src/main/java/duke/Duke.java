package duke;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import duke.exceptions.DukeyException;

/**
 * Interactive list which allows users to add Tasks and perform various operations to these Tasks.
 */
public class Duke extends Application {
    private static final String FILEPATH = "DukeySave.txt";
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private int inputStatus;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/Cap.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/IronMan.jpg"));

    /**
     * Returns a new instance of Duke with a Storage, Ui and TaskList.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(FILEPATH);
        this.taskList = new TaskList(ui);
        this.inputStatus = 0;
    }


    /**
     * Starts Duke
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        //duke.initiateDukeyList();

    }


    /**
     * Initiates the DukeyList by loading a save, then scans for and performs commands from the user.
     */
    public String runDuke(String input) {
        StringBuilder sb = new StringBuilder();
        ActionEnum actionEnum = ActionEnum.BYE;
        try {
            actionEnum = ui.readCommand(input);
            switch (actionEnum) {
            case TODO:
                sb.append(taskList.addTask(ToDo.createToDo(ui, input)));
                break;
            case DEADLINE:
                sb.append(taskList.addTask(Deadline.createDeadline(ui, input)));
                break;
            case EVENT:
                sb.append(taskList.addTask(Event.createEvent(ui, input)));
                break;
            case MARK:
                sb.append(taskList.mark(input));
                break;
            case UNMARK:
                sb.append(taskList.unmark(input));
                break;
            case DELETE:
                sb.append(taskList.delete(input));
                break;
            case LIST:
                sb.append(taskList.readList());
                break;
            case CLEARLIST:
                sb.append(taskList.clearList());
                break;
            case FIND:
                sb.append(taskList.find(input));
                break;
            case SAVE:
                sb.append(taskList.save(storage));
                break;
            case CLEARSAVE:
                taskList.clearSave(storage);
                break;
            }
        } catch (DukeyException e) {
            sb.append(ui.printExceptionMessage(e));
        }

        return sb.toString();


    }

    public String getInstruction() {
        return ui.getWelcomeMessage() + ui.printInstruction();
    }

    public String loadDuke() {
        StringBuilder sb = new StringBuilder();
        try {
            int status = taskList.initiate(storage);
            sb.append(ui.printLoadMessage(status));
        } catch (FileNotFoundException e) {
            sb.append(ui.printLoadMessage(0));
        }
        sb.append(taskList.readList());
        return sb.toString();
    }

    public String exitDuke() {
        String response = "";
        try {
            response = taskList.save(storage);
        } catch (DukeyException e) {
            response = ui.printExceptionMessage(e);
        }

        return response + ui.getGoodbyeMessage() + '\n' + "Exiting...";
    }

    @Override
    public void start(Stage stage) {
        //Setting up the required components
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

        //Formatting to look as expected.
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
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

        //Handle user input
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
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * Generates a response to user input.
     */
    public String getResponse(String input) {
        return "DukeyList:  " + input;
    }

}
