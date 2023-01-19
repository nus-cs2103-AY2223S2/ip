package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UserInterface;

public class MarkTask extends Command {
    private int index;

    public MarkTask(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list, UserInterface ui) throws DukeException {
        try {
            Task task = list.stream().filter(t -> t.id() == index).findFirst().get();
            task.markCompleted();
            ui.showMessage("Nice! I've marked this task as done: " + task);

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task with id " + index + " does not exist.");
        }
    }
}
