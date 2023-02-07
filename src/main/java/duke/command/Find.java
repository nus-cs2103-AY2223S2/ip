package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class Find extends Command {
    private String keyword;

    public Find(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList matches = tasks.stream().filter(t -> t.getDescription().contains(keyword)).collect(TaskList::new, TaskList::add, TaskList::addAll);
        return ui.showFind(matches);
    };
    
}
