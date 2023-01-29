package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

import java.time.LocalDate;

/**
 * The type Add command.
 */
public class AddCommand extends Command{

    private String type;

    private String description;

    private String by;

    private String from;

    private String to;

    /**
     * Instantiates a new Add command.
     *
     * @param description the description
     */
    public AddCommand(String description) {
        super();
        this.description = description;
        this.type = "T";
    }

    /**
     * Instantiates a new Add command.
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
     * Instantiates a new Add command.
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (this.type) {
            case "E":
                tasks.addEvent(description, from, to);
                ui.showTask(new Event(description, from, to), tasks.size());
                break;
            case "D":
                tasks.addDeadline(description, by);
                ui.showTask(new Deadline(description, LocalDate.parse(by)), tasks.size());
                break;
            case "T":
                tasks.addTodo(description);
                ui.showTask(new Todo(description), tasks.size());
                break;
        }
    }
}
