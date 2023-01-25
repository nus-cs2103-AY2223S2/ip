package duke.commands;

import duke.data.MyData;
import duke.tasks.Deadline;
import duke.ui.Ui;

public class AddDeadline extends Command {
    protected Deadline deadline;

    public AddDeadline(String description, String by) {
        this.deadline = new Deadline(description, by);
    }

    public void execute(MyData data, Ui ui) {
        data.setData(deadline);
        data.saveToFile();
        ui.add(deadline.toString(), data.len());
    }
}

