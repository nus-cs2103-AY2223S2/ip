package james.jamesbot;

import james.command.Command;
import james.exception.JamesException;
import james.task.TaskList;

/**
 * The JamesBot program to be executed.
 */
public class JamesBot {
    /** The ui to print out JamesBot response. */
    private static Ui ui = new Ui();

    /** The task list that is stored in the user's hard disk. */
    private Storage storage;

    /** The list where tasks are added to. */
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
