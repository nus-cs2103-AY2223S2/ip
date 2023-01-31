package connor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

import connor.gui.DialogBox;
import connor.parser.Parser;
import connor.storage.Storage;
import connor.task.Task;
import connor.task.TaskList;
import connor.ui.Ui;
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


/**
 * Connor object that is the backbone of the program.
 */
public class Connor extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    /**
     * Valid commands that are allowed to be inputted by the user.
     */
    public enum Commands {
        HI,
        BYE,
        MARK,
        UNMARK,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        DELETEALL,
        FIND
    }

    /** Storage variable for this instance */
    private Storage storage;

    /** Collection of tasks for this instance */
    private TaskList tasks;

    /** UI to print messages for this instance */
    private Ui ui;

    /** Parser to parse inputs for this instance */
    private Parser parser;

    /**
     * Constructor to instantiate a new Connor object.
     */
    public Connor() {
        this.ui = new Ui();
        this.ui.greet();
        this.storage = new Storage(getFile());
        this.parser = new Parser();
        try {
            LinkedList<Task> memory = storage.initialize();
            this.tasks = new TaskList(memory);
        } catch (IOException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Returns a File object that is created in a relative directory path of the user.
     * Creates a new Directory if the directory does not exist.
     * Creates a new File if the data file does not exist.
     * Utilises an existing file if it exists.
     *
     * @return File object that stores tasks across instances.
     */
    private File getFile() {
        String homeDir = System.getProperty("user.dir");
        Path directoryPath = Paths.get(homeDir, "data");
        Path dataPath = Paths.get(homeDir, "data", "connor.Connor.txt");
        try {
            if (Files.exists(dataPath)) {
                Ui.printMessage("Existing data detected, loading data.");
                return new File(String.valueOf(dataPath));
            } else {
                Ui.printMessage("No existing data detected, creating new save file.");
                Files.createDirectories(directoryPath);
                return new File("data/connor.Connor.txt");
            }
        } catch (IOException e) {
            Ui.printMessage("ALERT! FAILED TO CREATE DIRECTORY!");
        }
        return new File("data/connor.Connor.txt");
    }

    /**
     * Scans in user input and parses it.
     * Stores the output in the file.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean isOver = false;
        while (!isOver && sc.hasNextLine()) {
            String input = sc.nextLine().trim();
            isOver = this.parser.parse(input, this.tasks, this.ui);
            this.storage.updateFile(tasks.getList());
        }
        sc.close();
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
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
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Connor heard: " + input;
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

        stage.setTitle("Connor");
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
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // more code to be added here later
    }

    public static void main(String[] args) {
        new Connor().run();
    }
}
