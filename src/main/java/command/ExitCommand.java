package command;

import duke.DukeException;

import task.TaskList;

/**
 * Command for exiting from the program
 */
public class ExitCommand extends Command {

    /**
     * Default constructor
     * @param command the user-input command
     * @param doesPrint whether to print messages
     */
    public ExitCommand(String command, boolean doesPrint) throws DukeException {
        super(command, doesPrint, 1);
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
