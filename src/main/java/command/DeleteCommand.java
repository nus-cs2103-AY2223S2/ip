package command;
import exception.DukeException;
import main.Storage;
import main.Ui;
import main.TaskList;

public class DeleteCommand implements Command{
    private int inputIndex;
    
    public DeleteCommand(int inputIndex) {
        this.inputIndex = inputIndex;
    }
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        if (inputIndex < 0 || inputIndex >= list.size()) {
            throw new DukeException("OOPS!!! The index to remove cannot be less than 0 or "
                    + "greater than the length of the list.");
        }
        list.delete(this.inputIndex);
        ui.printDeletedTask(list.get(inputIndex), list);
    }

    public boolean isExit() {
        return false;
    }
}
