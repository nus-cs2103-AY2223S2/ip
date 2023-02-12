package duke.command;

import duke.storage.Note;
import duke.storage.TaskList;

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
     * @param notes Current Note.
     * @return Farewell message in String format.
     */
    @Override
    public String execute(TaskList tasks, Note notes) {
        return "Bye. Hope to see you again soon!";
    }
}
