package command;

import java.time.LocalDate;

import main.TaskList;
import main.Storage;
import main.DukeException;
import main.Ui;
import task.Deadline;
import task.Task;

public class DeadlineCommand extends Command {
    private String description;
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
