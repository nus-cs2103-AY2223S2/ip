import java.util.Scanner;

import command.Command;
import exception.DukeException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import parser.Parser;
import storage.LocalStorage;
import storage.TaskList;

/**
 * Duke Chat Bot!
 */
public class Duke extends Application {

    private TaskList tasks;
    private LocalStorage localStorage;

    /**
     * Constructor for Duke
     */
    public Duke() {
        this.tasks = new TaskList();
    }

    /**
     * Constructor to instantiate Duke bot.
     * @param filePath path of local storage file.
     */
    public Duke(String filePath) {
        TaskList tasks = new TaskList();
        this.localStorage = new LocalStorage(filePath);
        this.localStorage.loadTasks(tasks);
        this.tasks = tasks;
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Function to run Duke bot.
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

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();;
    }
}
