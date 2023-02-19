package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import commands.Command;
import exceptions.DukeException;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * The main class that represents the application.
 * The class holds a task list, a storage, and a UI,
 * uses a scanner to receive user inputs and execute the corresponding commands.
 */
public class Duke {
    private static final String PATH = "data/Duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object with the default file path for the storage.
     * Loads the task list from the file and sets up the UI.
     * In case of a loading error, shows an error message and creates an empty task list.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(this.PATH);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Constructs a Duke object with a specified file path for the storage.
     * Loads the task list from the file and sets up the UI.
     * In case of a loading error, shows an error message and creates an empty task list.
     *
     * @param filePath the file path to the storage file
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadFile());
        } catch (Exception e) {
            ui.getLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke application CLI ver.
     * Continuously receives user inputs, then parses and executes the corresponding commands.
     * Exits the program when a bye command is executed.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showGreeting();
        boolean isBye = false;
        while (!isBye) {
            try {
                String commandInput = sc.nextLine();
                Command c = Parser.parse(commandInput);
                ui.printOutput(c.execute(tasks, ui, storage));
                isBye = c.isBye();
            } catch (DukeException e) {
                ui.showExceptionError(e);
            } catch (DateTimeParseException e) {
                ui.showDateTimeParseError();
            }
        }
    }

    /**
     * The main method that creates a Duke object with the default file path and runs the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new Duke(PATH).run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isBye()) {
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(event -> System.exit(0));
                delay.play();
            }
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.getExceptionError(e);
        } catch (DateTimeParseException e) {
            return ui.getDateTimeParseError();
        }
    }

    public Ui getUi() {
        return this.ui;
    }
}
