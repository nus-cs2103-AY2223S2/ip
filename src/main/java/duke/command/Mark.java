package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class Mark extends Command {
    private Integer index;

    public Mark(Integer i) {
        this.index = i;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index > tasks.size() || index < 1) {
            throw new DukeException("Please specify a valid task number.");
        }
        Task t = tasks.get(index-1);
        t.setDone();
        return ui.showMark(t);
    }
}
