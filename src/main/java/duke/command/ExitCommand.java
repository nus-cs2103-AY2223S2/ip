package duke.command;

import duke.taskstorage.TaskList;

/**
 * Class for ExitCommand.
 */
public class ExitCommand extends Command {
    /**
     * Constructor for ExitCommand.
     * @param userInput User input.
     */
    public ExitCommand(String userInput) {
        super(userInput);
    }

    /**
     * Executes delete command
     * @param tasks Current TaskList
     * @return Farewell message in String format.
     */
    @Override
    public String execute(TaskList tasks) {
        return "Bye. Hope to see you again soon!";
    }
}
