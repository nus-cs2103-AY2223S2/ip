package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;

public class DeadlineCommand extends Command {

    private final String NAME = "deadline";
    private String title;
    private String deadline;

    public DeadlineCommand(String title, String deadline) {
        this.title = title;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        Task task = new Deadline(this.title, this.deadline);
        taskList.addTask(task);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass() != this.getClass()) {
            return false;
        }
        DeadlineCommand cmd = (DeadlineCommand) obj;
        return this.title.equals(cmd.title) && this.deadline.equals(cmd.deadline);
    }

}
