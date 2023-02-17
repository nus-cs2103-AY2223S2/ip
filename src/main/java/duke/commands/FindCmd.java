package duke.commands;

import duke.exceptions.CommandExecutionError;
import duke.parsing.Parser;
import duke.tasks.TaskList;

/**
 * Command class for 'find' keyword.
 * Finds tasks that contain the specified keyword(s) in their name.
 * <p>
 * Command format: "find &lt;keyword(s)&gt;"
 */
public class FindCmd extends Command {
    private TaskList findResult;

    /**
     * Constructor method.
     *
     * @param taskList task list to search for keyword
     * @param lineInput command line input that the user entered
     */
    public FindCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    }

    /** Searches for specified keyword in the task list. */
    public String execute() throws CommandExecutionError {
        String[] findKeywords = Parser.parseFindKeyword(this.lineInput);
        this.findResult = this.taskList.find(findKeywords);

        return String.format("Here's what I spotted:\n%s", findResult.toString());
    }
}
