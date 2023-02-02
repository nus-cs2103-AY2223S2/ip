package duke.command;

import java.time.LocalDate;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

public class CreateDeadline extends Command {
    private String desc;
    private LocalDate by;

    public CreateDeadline(String desc, LocalDate by) {
        this.desc = desc;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(new Deadline(desc, by, false));
        return ui.showAdd(tasks.get(tasks.size() - 1), tasks.size());
    }

}
