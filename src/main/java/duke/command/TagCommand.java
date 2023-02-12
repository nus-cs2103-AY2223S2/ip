package duke.command;

import java.io.IOException;
import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Class that handles adding tags to Tasks
 */
public class TagCommand extends Command {
    private int taskNo;
    private String tag;

    /**
     * Command to handle adding tags to Tasks
     * @param taskNo an int that specifies which Task to add the tag
     * @param tag a String representing the tag to be added
     */
    public TagCommand(int taskNo, String tag) {
        this.taskNo = taskNo;
        this.tag = tag;
    }

    /**
     * Adds tag to the Task
     * @param tasks TaskList to be updated if needed
     * @param ui Ui for displaying messages in a unique way
     * @param storage Storage for updating local tasks
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String result = "";
        try {
            result += ui.changeToFormat("Tag added to the following task:\n    " + tasks.addTag(taskNo, tag));
        } catch (DukeException e) {
            result += e.getMessage();
        }
        try {
            storage.updateLocal(tasks);
        } catch (IOException e) {
            result += ("\nfailed to update tasks locally: " + e.getMessage());
        }
        return result;
    }
}
