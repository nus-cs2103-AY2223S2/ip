package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;

import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {
    private String name;
    private String from;
    private String to;

    public AddEventCommand(String name, String from, String to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.addTaskResponse(tasks.addEvent(name, from, to), tasks);
        } catch (DateTimeParseException e1) {
            ui.invalidTiming();
        } catch (IllegalArgumentException e2) {
            ui.incompleteCommandErrorMessage();
        } catch (ArrayIndexOutOfBoundsException e3) {
            ui.incompleteCommandErrorMessage();
        }
    }
}
