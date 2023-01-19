package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UserInterface;

public class UnmarkTask extends Command {
    private int index;

    public UnmarkTask(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list, UserInterface ui) throws DukeException {
        try {
            Task task = list.stream().filter(t -> t.id() == index).findFirst().get();
            task.markCompleted();
            ui.showMessage("Nice! I've marked this task as pending: " + task);

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task with id " + index + " does not exist.");
        }
    }
}
