package duke;
import duke.command.Command;
import duke.data.TaskList;
import duke.data.TypeOfTask;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Creates main class and program for iP
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    /**
     * Default constructor
     *
     * @throws Exception
     */
    public Duke() throws Exception {
        this.ui = new Ui();
        this.storage = new Storage();
        this.parser = new Parser();
        try {
            taskList = new TaskList(this.storage.loadTasks());
        } catch (DukeException duke) {
            TypeOfTask errorTask = duke.getErrorTask();
            if (errorTask == TypeOfTask.storage) {
                System.out.println(duke.getMessage());
                throw new Exception("goodbye!");
            } else {
                System.out.println(duke.getMessage());
            }

        }
    }

    /**
     * Runs the program in a loop while waiting for the user's inputs.
     *
     * @throws Exception when user inputs are not legal
     */
    public void run() throws Exception {
        ui.displayWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = this.parser.parse(fullCommand);
                command.execute(this.taskList, this.ui, this.storage);
                isExit = command.isExit();
            } catch (DukeException duke) {
                System.out.println(duke.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.close();
    }

    /**
     * Creates main method for Duke class
     *
     * @param args
     * @throws Exception when unforseen error occurs.
     */
    public static void main(String[] args) throws Exception {
        new Duke().run();
    }
    /**
     * Gets the response from the user
     *
     * @param input Inputs from the user
     */
    public String getResponse(String input) {
        try {
            String fullCommand = input;
            Command command = this.parser.parse(fullCommand);
            String result = command.execute(this.taskList, this.ui, this.storage);
            return result;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
