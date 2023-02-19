import java.util.Date;

public class AddDeadlineCommand extends Command {
    private String description;
    private String deadlineTime;
    private Date dueDate;

    public AddDeadlineCommand(String description, String deadlineTime) {
        this.description = description;
        this.deadlineTime = deadlineTime;
    }

    public AddDeadlineCommand(String description, Date dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Deadline deadline;
        if (dueDate == null) {
            deadline = new Deadline(description, deadlineTime);
        } else {
            deadline = new Deadline(description, dueDate);
        }
        tasks.add(deadline);
        Ui.ShowAddMessage(deadline, tasks.size());
        storage.save(tasks.getAllTasks());
    }
}