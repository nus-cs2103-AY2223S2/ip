package duke.ui;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Input Output Handler for Duke.
 */
public interface IoHandler {

    // region Ui for input

    /**
     * Throws away the input.
     *
     * <p>
     * Warning: May prompt user for a line of input if not used properly.
     */
    public void throwAwayInput();

    /**
     * Retrieves the task number input.
     *
     * @return The task number.
     * @throws DukeException If the input is not a valid number.
     */
    public int getTaskNum() throws DukeException;

    /**
     * Retrives the command input.
     *
     * @return The string containing the user's command.
     */
    public String getCommand();

    /**
     * Retrives the name of a task from input.
     *
     * @return The string containing the name of a task from the user.
     * @throws DukeException If the name is empty.
     */
    public String getName() throws DukeException;

    /**
     * Retrives the input for a deadline task.
     *
     * @return The string array containing the name and date for a deadline task.
     * @throws DukeException If the name and date are not found.
     */
    public String[] getDeadline() throws DukeException;

    /**
     * Retrives the input for an event task from the user.
     *
     * @return The string array containing the name, start and end date for an event task.
     * @throws DukeException If the name, start and end date are not found.
     */
    public String[] getEvent() throws DukeException;

    // endregion

    // region Ui for output
    /**
     * Gives Duke's greetings.
     *
     * @return Duke's greetings as a formatted String.
     */
    public String greet();

    /**
     * Outputs a formatted message of Duke.
     *
     * @param s The message to be given to the user.
     * @return Duke's formatted version of the message.
     */
    public String produceDukeOutput(String s);

    /**
     * Outputs a formatted TaskList of Duke.
     *
     * @param tasks The task list to be formatted.
     * @return Duke's formatted version of the task list.
     */
    public String produceTaskListOutput(TaskList tasks);

    /**
     * Outputs the input as Duke.
     *
     * @return Duke's formatted version of the input.
     */
    public String produceInputAsOutput();

    /**
     * Outputs the exception as Duke.
     *
     * @param message The exception message.
     * @return Duke's formatted version of the exception message.
     */
    public String produceExceptionOutput(String message);

    /**
     * Outputs Duke's goodbye.
     *
     * @return Duke's goodbye.
     */
    public String produceGoodbyeOutput();

    /**
     * Undoes the previous Ouput of Duke.
     *
     * @return null
     */
    public String undoOutput();
    // endregion

}
