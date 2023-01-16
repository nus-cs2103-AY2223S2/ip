package duke.command;

import java.time.LocalDateTime;

import duke.constant.DukeCommand;
import duke.constant.Message;
import duke.database.DukeRepo;
import duke.exception.InvalidTaskTypeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

public class AddCommand extends Command {

    private DukeCommand tType;
    private String title;
    private Boolean isDone;
    private LocalDateTime date1;
    private LocalDateTime date2;

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
     * @param date1  {@link LocalDateTime} object
     */
    public AddCommand(DukeCommand tType, String title, boolean isDone, LocalDateTime date1) {
        this(tType, title, isDone, date1, null);
    }

    /**
     * Constructor for adding a eveny.
     * 
     * @param tType  {@link DukeCommand} enum
     * @param title  {@link String} object
     * @param isDone boolean
     * @param date1  {@link LocalDateTime} object
     * @param date2  {@link LocalDateTime} object
     */
    public AddCommand(DukeCommand tType, String title, boolean isDone, LocalDateTime date1, LocalDateTime date2) {
        this.tType = tType;
        this.title = title;
        this.isDone = isDone;
        this.date1 = date1;
        this.date2 = date2;
    }

    /**
     * Add task to database and print the output.
     * 
     * @see Command#execute(DukeRepo, Ui)
     */
    @Override
    public void execute(DukeRepo db, Ui ui) throws InvalidTaskTypeException {
        ui.println(Message.ADD_TASK);
        ui.println("\t" + db.addTask(getTask()));
        ui.println(String.format(Message.COUNT_TASK, db.count()));
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
                return new Deadline(title, isDone, date1);
            case EVENT:
                return new Event(title, isDone, date1, date2);
            default:
                throw new InvalidTaskTypeException(Message.EXCEPTION_INVALID_TASK_TYPE);
        }
    }

    /**
     * @see Command#isExit()
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
