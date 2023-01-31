package Command;

import java.time.LocalDate;

import Main.TaskList;
import Main.Storage;
import Main.DukeException;
import Main.Ui;
import Task.Deadline;
import Task.Task;

public class DeadlineCommand extends Command {
    String description;
    LocalDate endDate;

    public DeadlineCommand(String description, LocalDate endDate) {
        this.description = description;
        this.endDate = endDate;

    }
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task t = new Deadline(description, endDate);
        taskList.addTask(t);
        storage.writeFile(taskList);
        ui.outputAddTask(t);
    }

}
