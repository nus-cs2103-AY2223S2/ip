package duke;

import java.time.LocalDateTime;

import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Parser;
import duke.task.Reminder;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class for the Duke object.
 *
 * @author Gao Mengqi
 * @version CS2103T AY22/23 Semester 2
 */
public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private Reminder reminder;
    private boolean isExit = false;

    public Duke() {

    }
    /**
     * Constructor for Duke object.
     *
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
            ui.showLoadingError();
        } finally {
            reminder = new Reminder(LocalDateTime.now());
        }
    }

    public TaskList getTaskList() {
        return this.tasks;
    }

    /**
     * Start the Duke program.
     */
    public void run() {
        System.out.println(ui.showWelcome());
        System.out.println(reminder.getReminder(tasks, ui));
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                System.out.println(c.execute(tasks, ui, storage));
                isExit = c.isExit();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(ui.showInvalidCommandMsg());
                continue;
            } catch (NullPointerException e) {
                continue;
            }
        }
    }

    /**
     * Initialise the Duke object.
     *
     * @param args
     * @throws DukeException
     */
    public static void main(String[] args) {
        new Duke("ip/data/tasks.txt").run();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws DukeException {
        if (!isExit) {
            try {
                Command c = Parser.parse(input);
                assert c != null : "command should not be null";
                isExit = c.isExit();
                return c.execute(tasks, ui, storage);
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Invalid command! Use command 'help' to see the commands available for use :)";
            }
        } else {
            return ui.exit();
        }
    }



}
