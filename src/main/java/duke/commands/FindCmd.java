package duke.commands;

import duke.exceptions.CommandExecutionError;
import duke.parsing.Parser;
import duke.tasks.TaskList;

public class FindCmd extends Command {
    private TaskList findResult;

    /**
     * Constructor method.
     * @param taskList Task list to search for keyword
     * @param lineInput Command line input that the user entered
     */
    public FindCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    }

    /** Searches for specified keyword in the task list */
    public void execute() throws CommandExecutionError {
        String[] findKeywords = Parser.parseFindKeyword(this.lineInput);
        this.findResult = this.taskList.find(findKeywords);

        this.response = String.format("Here are matching tasks in your list:\n%s", this.findResult.toString());
    }

    public TaskList getFindResult() {
        return this.findResult;
    }
}
