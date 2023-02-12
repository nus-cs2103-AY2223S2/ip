package command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * Command to find all tasks that matches a phrase.
 */
public class CommandFind extends Command {

    private final TaskList taskList;
    private final String phrase;

    /**
     * Constructor for CommandFind.
     *
     * @param taskList List of all tasks.
     * @param phrase Phrase use to find task.
     */
    public CommandFind(TaskList taskList, String phrase) {
        this.taskList = taskList;
        this.phrase = phrase;
    }

    @Override
    public String execute() throws DukeException {
        return Ui.getAllTaskFoundMessageWithAttitude(this.taskList.findTaskWith(this.phrase));
    }
}
