package duke.commands;

import java.time.LocalDateTime;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.DeadlineTask;
import duke.ui.Ui;
import duke.storage.Storage;

public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime dateTime;

    public AddDeadlineCommand(String description, LocalDateTime dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = new DeadlineTask(description, dateTime);
        taskList.add(task);
        ui.printMessage("added: " + task);
    }
}
