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

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The main driver class of Duke software.
 */
public class Duke extends Application {

    /** Handles loading and saving information to hard disk. */
    private Storage storage;
    /** Contains task list. */
    private TaskList tasks;
    /** Interprets the user command. */
    private Parser parser;
    /** Handles interactions with user. */
    private UI ui;

    // public Duke() {}
    // When JavaFX build the Application, "Application.launch(Duke.class, args)",
    // it is actually trying to create Duke().
    // But if your code does not have this constructor, it will fail.

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

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
    }

}
