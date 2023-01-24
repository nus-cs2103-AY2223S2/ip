package dukes.engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import dukes.util.*;
import dukes.command.*;
import dukes.task.*;

import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The main driver class of Duke software.
 */
public class Duke {

    /** Handles loading and saving information to hard disk. */
    private Storage storage;
    /** Contains task list. */
    private TaskList tasks;
    /** Interprets the user command. */
    private Parser parser;
    /** Handles interactions with user. */
    private UI ui;

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

    /**
     * Runner of Duke class. Call classes to interpret command and execute.
     * Catch all the runtime DukeExceptions and provide feedback.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(sc);
                ui.showLine();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.showBye();
        sc.close();
    }

    /**
     * Main driver of the Duke engine.
     *
     * @param args keyboard arguments
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
