package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;

import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command{
    private String name;
    private String by;

    public AddDeadlineCommand(String name, String by) {
        this.name = name;
        this.by = by;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.addTaskResponse(tasks.addDeadline(name, by), tasks);
        } catch (DateTimeParseException e1) {
            ui.invalidTiming();
        } catch (IllegalArgumentException e2) {
            ui.incompleteCommandErrorMessage();
        } catch (ArrayIndexOutOfBoundsException e3) {
            ui.incompleteCommandErrorMessage();
        }
    }
}
