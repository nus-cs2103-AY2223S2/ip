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

public class AddCommand extends Command{

    private DukeCommand tType;
    private String title;
    private Boolean isDone;
    private LocalDateTime date1;
    private LocalDateTime date2;

    public AddCommand(DukeCommand tType, String title, boolean isDone) {
        this(tType, title, isDone, null, null);
    }

    public AddCommand(DukeCommand tType, String title, boolean isDone, LocalDateTime date1) {
        this(tType, title, isDone, date1, null);
    }

    public AddCommand(DukeCommand tType, String title, boolean isDone, LocalDateTime date1, LocalDateTime date2) {
        this.tType = tType;
        this.title = title;
        this.isDone = isDone;
        this.date1 = date1;
        this.date2 = date2;
    }

    @Override
    public void execute(DukeRepo db, Ui ui) throws InvalidTaskTypeException {
        ui.println(Message.ADD_TASK);
        ui.println("\t" + db.addTask(getTask()));
        ui.println(String.format(Message.COUNT_TASK, db.count()));
    }

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

    @Override
    public boolean isExit() {
        return false;
    }
    
}
