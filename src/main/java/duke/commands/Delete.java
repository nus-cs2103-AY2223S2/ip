package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;


public class Delete extends Command {
    private int index;
    private Task t;
    public Delete(String index) {
        this.index = Integer.valueOf(index) - 1;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage){
        this.t = tasks.getTask(index);
        tasks.removeTask(index);
        storage.saveTaskList(tasks);
        ui.printRemovedMessage(this.t, tasks.size());
    }
}
