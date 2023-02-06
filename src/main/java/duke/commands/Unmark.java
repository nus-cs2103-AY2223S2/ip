package duke.commands;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class Unmark extends Command{
    private int index;
    private Task t;

    public Unmark(String index) throws DukeException {
        try {
            this.index = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("invalid index");
        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if(this.index > tasks.size() - 1 || this.index < 0) {
            throw new DukeException("Sorry, you used an invalid index");
        }
        this.t = tasks.getTask(this.index);
        this.t.markAsNotDone();
        storage.saveTaskList(tasks);
        ui.printUnmarked(this.t);
    }
}