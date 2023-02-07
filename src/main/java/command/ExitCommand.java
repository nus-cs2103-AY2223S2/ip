package command;

import duke.DukeException;

import task.TaskList;

/**
 * Command for exiting from the program
 */
public class ExitCommand extends Command {
    private static int NUM_COMPONENTS = 1;

    /**
     * Default constructor
     * @param command the user-input command
     * @param doesPrint whether to print messages
     */
    public ExitCommand(String command, boolean doesPrint) throws DukeException {
        super(command, doesPrint, NUM_COMPONENTS);
        isExit = true;
    }

    /**
     * Execute the command
     * @param taskList the list of tasks
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        return "Bye. Hope to see you again soon!";
    }
}
