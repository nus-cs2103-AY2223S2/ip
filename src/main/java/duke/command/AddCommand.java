package duke.command;

import java.time.LocalDateTime;
import java.util.function.BiConsumer;

import duke.constant.DialogType;
import duke.constant.DukeCommand;
import duke.constant.Message;
import duke.database.DukeRepo;
import duke.exception.DukeException;
import duke.exception.InvalidTaskTypeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * AddCommand.
 *
 * @see Command
 */
public class AddCommand extends Command {

    private DukeCommand tType;
    private String title;
    private Boolean isDone;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Constructor for adding a todo.
     *
     * @param title  task title
     * @param isDone boolean marked indicator
     */
    public AddCommand(String title, boolean isDone) {
        this(DukeCommand.TODO, title, isDone, null, null);
    }

    /**
     * Constructor for adding a deadline.
     *
     * @param title     task title
     * @param isDone    boolean marked indicator
     * @param startDate deadline start date (by)
     * @see LocalDateTime
     */
    public AddCommand(String title, boolean isDone, LocalDateTime startDate) {
        this(DukeCommand.DEADLINE, title, isDone, startDate, null);
    }

    /**
     * Constructor for adding an event.
     *
     * @param title     task title
     * @param isDone    boolean marked indicator
     * @param startDate event start date (from)
     * @param endDate   event end date (to)
     * @see LocalDateTime
     */
    public AddCommand(String title, boolean isDone, LocalDateTime startDate, LocalDateTime endDate) {
        this(DukeCommand.EVENT, title, isDone, startDate, endDate);
    }

    /**
     * Base constructor for creating different types of tasks.
     *
     * @param tType     {@link DukeCommand} enum task type
     * @param title     task title
     * @param isDone    boolean marked indicator
     * @param startDate event start date (from/by)
     * @param endDate   event end date (to)
     * @see LocalDateTime
     */
    public AddCommand(DukeCommand tType, String title, boolean isDone, LocalDateTime startDate, LocalDateTime endDate) {
        this.tType = tType;
        this.title = title;
        this.isDone = isDone;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(DukeRepo db, BiConsumer<DialogType, String> con) throws DukeException {
        StringBuilder sb = new StringBuilder();
        sb.append(Message.ADD_TASK + "\n");
        sb.append(db.addTask(getTask()) + "\n");
        sb.append(String.format(Message.COUNT_TASK, db.count()) + "\n");
        con.accept(DialogType.NORMAL, sb.toString());
    }

    /**
     * Produces the corresponding task object for add command variants.
     *
     * @return {@link Task} object task created based on supplied type
     * @throws InvalidTaskTypeException if invalid task type were configured in the
     *                                  constructor.
     */
    public Task getTask() throws InvalidTaskTypeException {
        switch (tType) {
        case TODO:
            return new Todo(title, isDone);
        case DEADLINE:
            return new Deadline(title, isDone, startDate);
        case EVENT:
            return new Event(title, isDone, startDate, endDate);
        default:
            throw new InvalidTaskTypeException(Message.EXCEPTION_INVALID_TASK_TYPE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
