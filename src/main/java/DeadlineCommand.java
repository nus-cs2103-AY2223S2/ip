import java.io.IOException;
import java.time.LocalDate;

public class DeadlineCommand extends Command {
    String description;
    LocalDate endDate;

    public DeadlineCommand(String description, LocalDate endDate) {
        this.description = description;
        this.endDate = endDate;

    }
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        //index out of bound
        Task t = new Deadline(description, endDate);
        taskList.addTask(t);
        storage.writeFile(taskList);
        ui.outputDeleteTask(t);
    }

}
