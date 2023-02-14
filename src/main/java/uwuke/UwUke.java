package uwuke;

import java.util.NoSuchElementException;

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
import uwuke.controller.Advisor;
import uwuke.controller.Command;
import uwuke.model.TaskList;
import uwuke.view.DialogBox;
import uwuke.view.Printer;

public class UwUke extends Application {

    private static final int CAPACITY = 100;
    private static TaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Stage stage;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @Override
    public void start(Stage stage) {
        initialiseGuiElements(stage);
        configureGuiElements(stage);
        initialiseHelperClasses();
        stage.setScene(scene);
        stage.show();
    }

    private void initialiseHelperClasses() {
        Printer.setDialogContainer(dialogContainer);
        DialogBox.setDukeImage(duke);
        DialogBox.setUserImage(user);
        inititialiseModels();
    }

    private void initialiseGuiElements(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        scene = new Scene(mainLayout);
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(400.0, 600.0);
        this.stage = stage;
    }

    private void configureGuiElements(Stage stage) {
        configureStage(stage);
        configureScrollPane();
        configureAnchorPane();
        configureDialogContainer();
        configureUserInputTextField();
        configureSendButton();
    }

    private void handleUserInput() {
        assert userInput != null : "User Input Text Field should have been initialised!";
        String inputString = userInput.getText();
        assert inputString != null : "User input should not be null!";
        Label userText = new Label(inputString);
        DialogBox userBox = DialogBox.getUserDialogBox(userText);
        assert dialogContainer != null : "Dialog Container should be initialised!";
        dialogContainer.getChildren().add(userBox);
        displayDukeResponse(inputString);
        userInput.clear();
    }

    private void displayDukeResponse(String input) {
        run(input);
    }

    private void configureSendButton() {
        sendButton.setPrefWidth(55.0);
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
    }

    private void configureUserInputTextField() {
        userInput.setPrefWidth(325.0);
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private void configureDialogContainer() {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private static void configureStage(Stage stage) {
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }

    private void configureScrollPane() {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    private void configureAnchorPane() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private static void inititialiseModels() {
        loadTaskFromStorage();
    }

    private static void loadTaskFromStorage() {
        tasks = new TaskList(CAPACITY);
        try {
            tasks = Storage.readSavedTasks();
        } catch (Exception e) {
            Printer.printError("Could not load save file, creating new task list");
            tasks = new TaskList();
        }
    }

    /**
     * Performs the command based on the input
     * Will identify command based on it's type, raw input string can be passed in directly.
     * @param input command string
     */
    private void performCommand(String input) throws DukeException {
        switch (Command.matchCommand(input)) {
        case LIST:
            Printer.printTasks(tasks.getList());
            break;
        case DEADLINE:
            tasks.addDeadline(input);
            break;
        case EVENT:
            tasks.addEvent(input);
            break;
        case TODO:
            tasks.addTodo(input);
            break;
        case MARK:
            tasks.markTask(input);
            break;
        case UNMARK:
            tasks.unmarkTask(input);
            break;
        case DELETE:
            tasks.deleteTask(input);
            break;
        case FIND:
            tasks.findTask(input);
            break;
        case BYE:
            saveTask();
            stage.close();
            break;
        default:
            Printer.printWithDecorations(Advisor.adviseUser(input));
        }
    }

    /**
     * Handles error if input contains a comma character, which will interfere with save file loading.
     * Can potentially cause fatal errors when trying to read files if commas were allowed.
     */
    private static void handleIllegalCharacter(String input) throws DukeException {
        if (input.contains(",")) { 
            throw new DukeException("Please do not use reserved character \',\'.");
        }
    }

    /**
     * Handles saving tasks and any potential errors
     */
    private static void saveTask() {
        try {
            Storage.saveTasks(tasks.getList());
        } catch (Exception e) {
            Printer.printWithDecorations("Error occured when trying to save tasks");
        }
    }

    private void run(String input) {
        try {
            handleIllegalCharacter(input);
            performCommand(input);
        } catch (DukeException e) {
            Printer.printError(e.getMessage());
        } catch (NoSuchElementException e) {
            Printer.printError("Error occurred when trying to read next line, try again.");
        } catch (Exception e) {
            Printer.printError("Unknown error ocurred");
        }
    }
}