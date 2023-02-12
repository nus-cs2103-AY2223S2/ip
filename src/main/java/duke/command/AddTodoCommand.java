package duke.command;

import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.EmptyTaskDescriptionException;
import duke.tag.Tag;
import duke.task.Task;
import duke.task.ToDo;

/**
 * A command representing the user adding a new Todo to the task list.
 */
public class AddTodoCommand extends Command {
    private String contents;

    /**
     * A constructor for AddToDoCommand.
     *
     * @param tasks TaskList object to add Todo object to
     * @param contents Description of task
     */
    public AddTodoCommand(TaskList tasks, String contents) {
        super(tasks);
        this.contents = contents;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute() throws DukeException {
        try {
            Task task;
            if (!contents.contains("/tag")) {
                String description = contents.substring(5);
                task = new ToDo(description);
            } else {
                int tagIndex = contents.indexOf("/tag");
                String description = contents.substring(5, tagIndex - 1);
                String tagName = contents.substring(tagIndex + 5);
                Tag tag = new Tag(tagName);
                task = new ToDo(description, tag);
            }
            tasks.addTask(task);
            return tasks.addTaskText(task);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyTaskDescriptionException();
        }
    }
}
