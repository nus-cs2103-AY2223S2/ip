package command;

import duke.Storage;
import duke.Ui;
import task.Task;
import task.Tasklist;

/**
 * A class that represents a Tag Command which is used to tag a task in the task list.
 * Implements the Command interface.
 */
public class TagCommand implements Command {
    private int index;
    private String tagName;

    /**
     * Constructor that creates a new TagCommand with the given task index and tag name.
     * @param index The index of the task to be tagged.
     * @param tagName The name of the tag to be applied to the task.
     */
    public TagCommand(int index, String tagName) {
        this.index = index;
        this.tagName = tagName;
    }

    /**
     * Executes the Tag Command by tagging the task with the specified index with the specified tag name.
     * @param ui The user interface.
     * @param list The list of tasks.
     * @param storage The storage object.
     * @return A message to be displayed to the user indicating that the task has been tagged.
     */
    @Override
    public String execute(Ui ui, Tasklist list, Storage storage) {
        int oneIndex = index + 1;
        Task taggedTask = list.getTask(oneIndex);
        list.tagTask(this.index, this.tagName);
        return ui.getTagReply(taggedTask);
    }
}
