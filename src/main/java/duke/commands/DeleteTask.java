package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UserInterface;

public class DeleteTask extends Command {
    private int index;

    public DeleteTask(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list, UserInterface ui) throws DukeException {
        try {
            Task task = list.stream().filter(t -> t.id() == index).findFirst().get();
            list.remove(task);
            ui.showMessage("Nice! I've deleted the task: " + list.get(index));
            
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task with id " + index + " does not exist.");
        }
    }
}
