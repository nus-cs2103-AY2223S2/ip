package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The type Add command.
 */
public class AddCommand extends Command {

    private String type;

    private String description;

    private String by;

    private String from;

    private String to;

    /**
     * Instantiates a new Add command for a Todo object.
     *
     * @param description the description
     */
    public AddCommand(String description) {
        super();
        this.description = description;
        this.type = "T";
    }

    /**
     * Instantiates a new Add command for a Deadline object.
     *
     * @param description the description
     * @param by          the by
     */
    public AddCommand(String description, String by) {
        super();
        this.description = description;
        this.by = by;
        this.type = "D";
    }

    /**
     * Instantiates a new Add command for an Event object.
     *
     * @param description the description
     * @param from        the from
     * @param to          the to
     */
    public AddCommand(String description, String from, String to) {
        super();
        this.description = description;
        this.from = from;
        this.to = to;
        this.type = "E";
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String newList;
        switch (this.type) {
        case "E":
            String newEventString = tasks.addEvent(description, from, to);
            newList = tasks.showList();
            return ui.showTask(newEventString, tasks.size(), newList);
        case "D":
            String newDeadlineString = tasks.addDeadline(description, by);
            newList = tasks.showList();
            return ui.showTask(newDeadlineString, tasks.size(), newList);
        case "T":
            String newTodoString = tasks.addTodo(description);
            newList = tasks.showList();
            return ui.showTask(newTodoString, tasks.size(), newList);
        default:
            throw new DukeException("could not add task ^.~");
        }
    }
}
