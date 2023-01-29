package command;
import exception.DukeException;
import main.Storage;
import main.Ui;
import main.TaskList;

public class UnMarkCommand implements Command{
    private int inputIndex;
    public UnMarkCommand(int indexToMark) {
        this.inputIndex = indexToMark;
    }

    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException{
        if (inputIndex < 0 || inputIndex >= list.size()) {
            throw new DukeException("OOPS!!! The index to unmark cannot be less than 0 or "
                    + "greater than the length of the list.");
        }
        list.unmark(inputIndex);
        ui.printUnMarkedTask(list.get(inputIndex));
    }

    public boolean isExit() {
        return false;
    }
}