import java.io.IOException;
import java.time.LocalDate;

public class EventCommand extends Command{
    String description;
    LocalDate endDate;
    LocalDate startDate;

    public EventCommand(String description, LocalDate startDate, LocalDate endDate) {
        this.description = description;
        this.endDate = endDate;
        this.startDate = startDate;

    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        //index out of bound
        Task t = new Event(description, startDate, endDate);
        taskList.addTask(t);
        storage.writeFile(taskList);
        ui.outputAddTask(t);
    }

}