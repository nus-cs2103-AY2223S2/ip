package duke.command;

import duke.TaskList;

/**
 * Bye command which is a command placeholder for the program to stop.
 */
public class Bye extends Command {

    /**
     * Constructor for Bye
     *
     * @param input The user input.
     */
    public Bye(String input) {
        super(input);
    }

    /**
     * Does nothing.
     *
     * @param tasks The current Task List.
     * @return The current Task List.
     */
    @Override
    public TaskList execute(TaskList tasks) {
        return tasks;
    }
}
