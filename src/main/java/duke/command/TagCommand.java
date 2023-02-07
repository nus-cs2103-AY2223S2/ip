package duke.command;

import duke.exception.DukeException;
import duke.storage.StorageList;
import duke.task.TaskList;

/**
 * Tag Command to tag task with a name.
 */
public class TagCommand extends Command {
    private String tagName;
    private int taskNum;

    /**
     * Constructor for tag command.
     *
     * @param taskNo Task to be tagged.
     * @param input Name of tag.
     */
    public TagCommand(String taskNo, String input) {
        this.tagName = input;
        this.taskNum = Integer.valueOf(taskNo);
    }

    /**
     * Method to execute the tagging of the task.
     *
     * @param tasks   - task list of the current tasks.
     * @param storage - database of the history of commands.
     * @return String Output when the task is tagged.
     */
    public String execute(TaskList tasks, StorageList storage) throws DukeException {
        tasks.tagTask(taskNum, tagName);
        return "Task " + (taskNum) + " has been tagged as " + "#" + tagName
                + "\n" + tasks.getLengthMessage();
    }
}
