package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class Mark extends Command{
    private int index;
    private Task t;

    public Mark(String index) {
        this.index = Integer.valueOf(index) - 1;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.t = tasks.getTask(this.index);
        this.t.markAsDone();
        storage.saveTaskList(tasks);
        ui.printMarked(this.t);
    }
}
