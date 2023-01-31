package duke.commands;
import duke.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public class ToDoCommand extends Command{
    private String requestContent;
    public ToDoCommand(String requestContent){
        super("TODO");
        this.requestContent = requestContent;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addToDo(requestContent);
    }
}
