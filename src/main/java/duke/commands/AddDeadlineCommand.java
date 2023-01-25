package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Event;

import java.io.IOException;
import java.time.LocalDate;

public class AddDeadlineCommand extends AddCommand {
    private String description;
    private LocalDate by;

    public AddDeadlineCommand(String description, LocalDate by) {
        super("D");
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addNewDeadline(this.description, this.by);
        try {
            storage.saveData(taskList);
        } catch (IOException e) {
            Ui.showFatalError("Error in saving data.\nReboot Duke and try again");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AddDeadlineCommand)) {
            return false;
        }

        AddDeadlineCommand c = (AddDeadlineCommand) o;
        if (this.description.equals(c.description) && this.by.equals(c.by)) {
            return true;
        } else {
            return false;
        }
    }
}
