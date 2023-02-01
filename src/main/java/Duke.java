package duke;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

import duke.command.Command;

/**
 * Represents a Duke programme.
 *
 * @author Karen
 */
public class Duke {
    static final String STR = "------------------------------------------------------------";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises new instance of Duke.
     *
     * @param filePath The file path in String of the file where memory is stored.
     */
    private Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
    }

    /**
     * Runs Duke program when called.
     */
    public void run() {
        // Introduction
        Duke duke = new Duke("./data/duke.txt");
        ui.welcomeResponse();

        // Load data
        try {
            this.tasks = new TaskList(storage.loadData());
            ui.successfulLoadResponse();
        } catch (FileNotFoundException e) {
            this.tasks = new TaskList();
            ui.loadingErrorMessage();
        }
        ui.listTaskResponse(this.tasks);

        boolean isExit = false;
        while (!isExit) {
            try {
                ui.askForTaskResponse();
                String fullCommand = ui.nextInput();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DateTimeParseException e2) {
                System.out.println("this");
                ui.unreadableCommandErrorMessage();
            } catch (IllegalArgumentException e3) {
                ui.incompleteCommandErrorMessage();
            } catch (ArrayIndexOutOfBoundsException e3) {
                ui.incompleteCommandErrorMessage();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

}
