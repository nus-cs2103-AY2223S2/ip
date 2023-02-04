package dukes.engine;

import dukes.util.DukeException;
import dukes.util.TaskList;
import dukes.util.UI;
import dukes.util.Storage;
import dukes.util.Parser;

import dukes.command.Command;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The main driver class of Duke software.
 */
//public class Duke extends Application {
public class Duke {

    /** Handles loading and saving information to hard disk. */
    private Storage storage;
    /** Contains task list. */
    private TaskList tasks;
    /** Interprets the user command. */
    private Parser parser;
    /** Handles interactions with user. */
    private UI ui;
    public static final String DEFAULT_PATH = "data/tasks.txt";

    // When JavaFX builds the Application, "Application.launch(Duke.class, args)",
    // it is actually trying to create Duke().
    // But if your code does not have this constructor, it will fail.
    public Duke() {
        this(DEFAULT_PATH);
    }

    /**
     * Constructor of Duke class.
     * Handles the loading error when Duke starts.
     *
     * @param filePath the file path of the data file.
     */
    public Duke(String filePath) {
        ui = new UI();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

//    public void run() {
//        Scanner sc = new Scanner(System.in);
//        ui.showWelcome();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand(sc);
//                ui.showLine();
//                Command c = parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            } finally {
//                ui.showLine();
//            }
//        }
//        ui.showBye();
//        sc.close();
//    }

    /**
     * Generates the response of duke engine used by MainWindow.
     *
     * @param fullCommand user's command input
     * @return duke engine's corresponding output
     */
    public String getResponse(String fullCommand) {
        try {
            Command c = parser.parse(fullCommand);
            return c.runCommand(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

//    public static void main(String[] args) {
//        new Duke("data/tasks.txt").run();
//    }


}
