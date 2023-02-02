import duke.Deadline;
import duke.DukeException;
import duke.Event;
import duke.Parser;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Todo;
import duke.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Scanner;

public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }

    public Duke() {
        super();
    }

    public static void main(String[] args) {
        new Duke("src/data/duke.txt").run();
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

        // more code to be added here later
    }

    /**
     * Executes the Duke chatbot. 
     */
    public void run() {

        ui.introduce();
        ui.instruct();
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();

        while (!parser.checkEnd(userInput)) {
            if (parser.checkListRequest(userInput)) {
                tasks.printContents();
            } else if (parser.checkMarkRequest(userInput)) {
                String[] terms = userInput.split(" ");
                int itemNo = Integer.parseInt(terms[1]);
                tasks.markTask(itemNo);
            } else if (parser.checkDeleteRequest(userInput)) {
                String[] terms = userInput.split(" ");
                int itemNo = Integer.parseInt(terms[1]);
                tasks.deleteTask(itemNo);
            } else if (parser.checkFindRequest(userInput)) {
                String toBeFound = userInput.substring(5);
                tasks.find(toBeFound);
            }
            else {
                String[] terms = userInput.split(" ");
                if (terms[0].equals("todo")) {
                    try {
                        if (terms.length == 1) {
                            String error = "The description of a todo cannot be empty";
                            throw new DukeException(error);
                        }
                        Task newTask = new Todo(userInput.substring(5));
                        tasks.addTask(newTask);
                    } catch (DukeException err) {
                        System.out.println(err);
                    }
                } else if (terms[0].equals("deadline")) {
                    String[] splitBySlash = userInput.split("/");
                    try {
                        if (splitBySlash.length != 2) {
                            throw new DukeException("Wrong format for deadline Task");
                        }
                        String description = splitBySlash[0].substring(9);
                        String by = splitBySlash[1].substring(3);
                        Task newTask = new Deadline(description, by);
                        tasks.addTask(newTask);
                    } catch (DukeException err) {
                        System.out.println(err);
                    }

                } else if (terms[0].equals("event")) {
                    String[] splitBySlash = userInput.split("/");
                    try {
                        if (splitBySlash.length != 3) {
                            throw new DukeException("Wrong format for event Task");
                        }
                        String description = splitBySlash[0].substring(6);
                        String from = splitBySlash[1].substring(5);
                        String to = splitBySlash[2].substring(3);
                        Task newTask = new Event(description, from, to);
                        tasks.addTask(newTask);
                    } catch (DukeException err) {
                        System.out.println(err);
                    }
                } else {
                    try {
                        throw new DukeException("I don't know what that means.");
                    } catch (DukeException err) {
                        System.out.println(err);
                    }
                }

            }
            userInput = scan.nextLine();
        }
        storage.addToFile(tasks);
        ui.terminate();
    }
}
