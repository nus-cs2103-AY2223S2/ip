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
import duke.ui.Ui;

/**
 * AddCommand
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
     * @param tType  {@link DukeCommand} enum
     * @param title  {@link String} object
     * @param isDone boolean
     */
    public AddCommand(DukeCommand tType, String title, boolean isDone) {
        this(tType, title, isDone, null, null);
    }

    /**
     * Constructor for adding a deadline.
     *
     * @param tType  {@link DukeCommand} enum
     * @param title  {@link String} object
     * @param isDone boolean
     * @param startDate  {@link LocalDateTime} object
     */
    public AddCommand(DukeCommand tType, String title, boolean isDone, LocalDateTime startDate) {
        this(tType, title, isDone, startDate, null);
    }

    /**
     * Constructor for adding a eveny.
     *
     * @param tType  {@link DukeCommand} enum
     * @param title  {@link String} object
     * @param isDone boolean
     * @param startDate  {@link LocalDateTime} object
     * @param endDate  {@link LocalDateTime} object
     */
    public AddCommand(DukeCommand tType, String title, boolean isDone, LocalDateTime startDate, LocalDateTime endDate) {
        this.tType = tType;
        this.title = title;
        this.isDone = isDone;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Add task to database and print the output.
     *
     * {@inheritDoc}
     */
    @Override
    public void execute(DukeRepo db, Ui ui) throws InvalidTaskTypeException {
        ui.printConsole(Message.ADD_TASK);
        ui.printConsole(db.addTask(getTask()));
        ui.printConsole(String.format(Message.COUNT_TASK, db.count()));
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
     * @return {@link Task} object
     * @throws InvalidTaskTypeException
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
