package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Task;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    public UnmarkCommand(String fullCommand) throws DukeException {
        try {
            index = Integer.parseInt(fullCommand
                    .split("unmark")[1]
                    .trim());
        } catch (NumberFormatException e) {
            throw new DukeException("Index given is not an integer");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("An index for a task was not given");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.unmark(index);
        ui.echo("OK, I've marked this task as not done yet:");
        ui.showTask(task);
        storage.save(tasks);
    }
}
