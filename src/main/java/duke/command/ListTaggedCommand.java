package duke.command;

import duke.TaskList;
import duke.exception.DukeException;
import duke.tag.Tag;

public class ListTaggedCommand extends Command {
    private String tagName;
    public ListTaggedCommand(TaskList tasks, String tagName) {
        super(tasks);
        this.tagName = tagName;
    }

    @Override
    public String execute() throws DukeException {
        Tag tag = new Tag(tagName);
        TaskList tasksFound = tasks.listTagged(tag);
        return tasksFound.getListOfTasks();
    }
}
