package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.Command;
import duke.command.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Duke command line tool that helps to track tasks.
 */
public class Duke {
    /** Whether the duke is running or has been commanded to stop running */
    private boolean isRunning;

    /** Storage to load and save tasks on disk */
    private final Storage storage;

    /** Task list to store tasks in memory */
    private final TaskList tasks;

    /** Ui to handle user interface */
    private final Ui ui;

    /**
     * Constructs a duke.
     */
    public Duke() {
        storage = new Storage("./data/tasks.txt");
        tasks = new TaskList();
        ui = new Ui();
        isRunning = false;
        ui.printGreeting();
        try {
            tasks.load(storage.getScanner());
        } catch (FileNotFoundException e) {
            ui.printStorageLoadFailure();
        }
    }

    /**
     * Entry point to start and run duke.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Runs the duke.
     */
    public void run() {
        isRunning = true;
        ui.printPrompt();
        while (isRunning) {
            try {
                execute(Parser.parse(ui.readCommand()));
            } catch (IllegalArgumentException e) {
                ui.printBadCommandMessage(e.getMessage());
            }
        }
        exit();
    }

    /**
     * Executes a command.
     *
     * @param command Command to execute.
     */
    private void execute(Command command) {
        switch (command.getName()) {
        case NO_OP:
            break;
        case BYE:
            isRunning = false;
            break;
        case TODO:
            // FallThrough
        case DEADLINE:
            // FallThrough
        case EVENT:
            ui.printAddTaskSuccessMessage(tasks.execute(command));
            break;
        case LIST:
            System.out.println(tasks);
            break;
        case MARK:
            Task task = tasks.execute(command);
            ui.printMarkTaskSuccessMessage(task);
            break;
        case DELETE:
            ui.printDeleteTaskSuccessMessage(tasks.execute(command));
            break;
        default:
            // Do nothing
        }
    }

    /**
     * Cleans up the duke to exit.
     * Save tasks to the disk.
     */
    private void exit() {
        try {
            tasks.save(storage.getFileWriter());
        } catch (IOException e) {
            ui.printStorageSaveFailure();
        }
        ui.printFareWell();
        ui.close();
    }
}
