package james.jamesbot;

import james.command.Command;
import james.exception.JamesException;
import james.task.TaskList;

/**
 * Executes JamesBot program.
 */
public class JamesBot {
    /** Prints out JamesBot response in the ui. */
    private static Ui ui = new Ui();

    /** Stores the task list in the user's hard disk. */
    private Storage storage;

    /** Adds the list of tasks. */
    private TaskList tasks;

    /**
     * Constructs an JamesBot object.
     */
    public JamesBot() throws JamesException {
        storage = new Storage();
        try {
            tasks = storage.load();
        } catch (JamesException e) {
            ui.showError(String.valueOf(e));
            tasks = new TaskList();
        }
    }

    /**
     * Gets the response from JamesBot based on user's input.
     *
     * @param input The user's input.
     * @return JamesBot response to user's input.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (JamesException e) {
            return e.getMessage();
        }
    }

    /**
     * Checks if the JamesBot program has ended.
     *
     * @param input The user input.
     * @return true if the command exits the JamesBot program.
     *         false if the command does not exit the JamesBot program.
     */
    public boolean isEnd(String input) {
        try {
            Command command = Parser.parse(input);
            return command.isExit();
        } catch (JamesException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
