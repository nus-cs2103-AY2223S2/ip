package uwuke;

import java.util.NoSuchElementException;
import java.util.Scanner;

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

import uwuke.gui.DialogBox;

import uwuke.input.Advisor;
import uwuke.input.Command;

import uwuke.output.DukeException;
import uwuke.output.Printer;
import uwuke.output.Storage;

import uwuke.task.TaskList;

public class UwUke extends Application {

    private final static int CAPACITY = 100;
    private static TaskList tasks;
    private static Scanner sc = new Scanner(System.in);

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(400.0, 600.0);

        scene = new Scene(mainLayout);
        setStage(stage);
        setScrollPane();
        
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
        setAnchorPane();
        stage.setScene(scene);
        stage.show();

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
    
        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
    
        return textToAdd;
    }

    private void handleUserInput() {
        String inputString = userInput.getText();
        Label userText = new Label(inputString);
        DialogBox userBox = DialogBox.getUserDialogBox(userText, new ImageView(user));
        Label dukeText = new Label(getResponse(inputString));
        DialogBox dukeBox = DialogBox.getDukeDialogBox(dukeText, new ImageView(duke));

        dialogContainer.getChildren().addAll(userBox, dukeBox);
        userInput.clear();
    }

    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    private static void setStage(Stage stage) {
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }

    private void setScrollPane() {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    private void setAnchorPane() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private static void inititialise() {
        Printer.uwu();
        tasks = new TaskList(CAPACITY);
        try {
            tasks = Storage.readSavedTasks();
        } catch (Exception e) {
            Printer.printError("Could not load save file, creating new task list");
            tasks = new TaskList();
        }
        sc = new Scanner(System.in);
    }

    /**
     * Main handler to perform all commands other than the bye command
     * @param input command string
     */
    private static void performCommand(String input) throws DukeException {
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
            default:
                Printer.printWithDecorations(Advisor.advise(input));
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

    private static void run() {
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                handleIllegalCharacter(input);
                performCommand(input);
                input = sc.nextLine();
            } catch (DukeException e) {
                Printer.printError(e.getMessage());
            } catch (NoSuchElementException e) {
                Printer.printError("Error occurred when trying to read next line, try again.");
                input = "";
            } catch (Exception e) {
                Printer.printError("Unknown Error Ocurred");
            }
        }
        saveTask();
    }

    public static void main(String[] args) {
        inititialise();
        run();
        sc.close();
        Printer.printBye();
    }
}