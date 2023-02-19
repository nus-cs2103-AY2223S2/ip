package duke.command;

import duke.*;
import duke.task.Deadline;
import duke.task.TaskList;

import java.util.Date;

public class AddDeadlineCommand extends Command {
    public String description;
    public String deadlineTime;
    public Date dueDate;

    public AddDeadlineCommand(String description, String deadlineTime) {
        this.description = description;
        this.deadlineTime = deadlineTime;
    }

    public AddDeadlineCommand(String description, Date dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
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