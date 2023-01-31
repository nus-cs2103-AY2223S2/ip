import java.io.IOException;

public class TodoCommand extends Command{
    String description;


    public TodoCommand (String description) {

        this.description = description;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
            //index out of bound
        Task t = new Todo(description);
        taskList.addTask(t);
        storage.writeFile(taskList);
        ui.outputAddTask(t);
    }
}

