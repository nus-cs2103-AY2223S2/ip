package duke;

import java.util.Scanner;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/** Handles input/output for Duke. */
public class Ui {

    // commonly-displayed elements
    public static final String DIVIDER = "____________________________________________________________";
    public static final String LOGO = " ____        _        \n" + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String NEWL = "\n";

    // duke components
    private Scanner scanner;
    private StringBuilder message;
    private boolean doPromptUserInput;

    // javafx ui components
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Initializes a Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
        this.message = new StringBuilder();
        this.doPromptUserInput = true;
    }

    /**
     * Initializes the main Scene to be displayed on the Stage.
     */
    public Scene initializeScene() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        userInput = new TextField();
        sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();

        scrollPane.setContent(dialogContainer);
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        this.scene = new Scene(mainLayout);

        return this.scene;
    }

    /** Prints a welcome message. */
    public void showWelcome() {
        this.addToMessage("Hello from");
        this.addToMessage(LOGO);
        this.addToMessage("Hello! I'm Duke.");
        this.addToMessage("What can I do for you?");
        this.displayMessage();
    }

    /**
     * Prints the stored message to the user.
     */
    public void displayMessage() {
        Ui.prettyPrint(this.message.toString());
        this.clearMessage();
        if (this.doPromptUserInput) {
            System.out.print("> ");
        } else {
            this.doPromptUserInput = true;
        }
    }

    /** Clears the stored message. */
    public void clearMessage() {
        this.message.setLength(0);
    }

    /**
     * Appends a string to the stored message.
     *
     * @param toAdd The string to be appended to the stored message.
     */
    public void addToMessage(String toAdd) {
        this.message.append(toAdd);
        this.message.append(NEWL);
    }

    /**
     * Appends a string to the stored message.
     *
     * @param toAdd             The string to be appended to the stored message.
     * @param doPromptUserInput Indicates whether the user should be prompted for
     *                          input after this message.
     */
    public void addToMessage(String toAdd, boolean doPromptUserInput) {
        this.doPromptUserInput = doPromptUserInput;
        this.addToMessage(toAdd);
    }

    /** Reads a line of input from the user. */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Pretty prints a given string.
     *
     * @param text The string to be pretty-printed.
     */
    public static void prettyPrint(String text) {
        System.out.println(DIVIDER);
        System.out.print(text);
        System.out.println(DIVIDER);
        System.out.println();
    }
}
