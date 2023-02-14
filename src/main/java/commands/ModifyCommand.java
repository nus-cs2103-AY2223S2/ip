package commands;

import dukeexceptions.DukeException;
import dukeexceptions.IllegalCommandException;
import dukeexceptions.IllegalInputException;
import dukeexceptions.TaskListIndexOutOfBoundsException;
import elems.Storage;
import elems.TaskList;
import elems.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class ModifyCommand extends Command{
    enum ModifyType {
        MARK,
        UNMARK
    }

    private final ModifyType modifyType;

    public ModifyCommand(String keyword, ArrayList<String> params) throws IllegalCommandException {
        super(keyword, params);

        switch (keyword) {
        case "mark":
            this.modifyType = ModifyType.MARK;
            break;
        case "unmark":
            this.modifyType = ModifyType.UNMARK;
            break;
        default:
            throw new IllegalCommandException("Invalid keyword for ModifyCommand");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.params.size() != 1) {
            throw new IllegalInputException("Wrong number of parameters for this command!");
        }
        try {
            int taskIndex = Integer.parseInt(this.params.get(0));
            switch (this.modifyType) {
            case MARK:
                tasks.markTask(taskIndex);
                break;
            case UNMARK:
                tasks.unmarkTask(taskIndex);
                break;
            }
            storage.refreshStorage(tasks);
            ui.display("Task successfully modified! \n" + tasks.getTaskString(taskIndex));
        } catch (NumberFormatException e) {
            throw new IllegalInputException("Invalid task item selected!");
        } catch (TaskListIndexOutOfBoundsException e) {
            ui.errorDisplay(e);
        } catch (IOException e) {
            ui.display("Seems like there is something wrong with the storage file \n"
                    + "Any updates will not be saved!");
            ui.errorDisplay(e);
            e.printStackTrace();
        }
    }
}
