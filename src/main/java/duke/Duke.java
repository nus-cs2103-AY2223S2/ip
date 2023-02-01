package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
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
import duke.parser.Parser;
import duke.storage.LocalStorage;
import duke.storage.TaskList;

/**
 * duke.Duke Chat Bot!
 */
public class Duke {
    private Button sendButton;
    private ScrollPane scrollPane;
    private Label label;
    private VBox dialogContainer;
    private TextField userInput;
    private Scene scene;
    private TaskList tasks;
    private LocalStorage localStorage;

    /**
     * Constructor for duke.Duke
     */
    public Duke() {
        this.tasks = new TaskList();
    }

    /**
     * Constructor to instantiate duke.Duke bot.
     * @param filePath path of local duke.storage file.
     */
    public Duke(String filePath) {
        TaskList tasks = new TaskList();
        this.localStorage = new LocalStorage(filePath);
        this.localStorage.loadTasks(tasks);
        this.tasks = tasks;
    }

    /**
     * Function to run duke.Duke bot.
     */
    public void run() {
        UI.greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            try {
                Command com = new Parser(input, tasks).processRequest();
                String response = com.execute(tasks);
                UI.printRes(response);
            } catch (DukeException error) {
                UI.printRes(error.getMessage());
            }
            input = sc.nextLine();
        }
        localStorage.saveFile(tasks);
        sc.close();
        UI.exit();
    }

    /**
     * Function to handle the user's request
     */
    public static void handleRequest() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        TaskList tasks = new TaskList();
        while (!input.equalsIgnoreCase("bye")) {
            Command com = new Parser(input, tasks).processRequest();
            String response = com.execute(tasks);
            UI.printRes(response);
            input = sc.nextLine();
        }
        sc.close();
    }

    /**
     * From JavaFX tutorial
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

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();;
    }
}
