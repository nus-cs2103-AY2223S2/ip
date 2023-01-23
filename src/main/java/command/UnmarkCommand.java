package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;
import exceptions.InvalidNumberException;

public class UnmarkCommand extends Command{
    private int index;

    public UnmarkCommand(int index){
        super();
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        int taskNumMinusOne = index - 1;
        if(taskNumMinusOne < 0 || taskNumMinusOne > taskList.size() - 1)
            throw new InvalidNumberException();
        taskList.unmarkTask(index);
        storage.writeFile(taskList);
        return ui.printUnmark(index , taskList.get(taskNumMinusOne));
    }
}