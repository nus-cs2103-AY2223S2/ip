package commands;

import dukeexceptions.DukeException;
import dukeexceptions.IllegalInputException;
import dukeexceptions.TaskListIndexOutOfBoundsException;
import elems.Storage;
import elems.TaskList;
import elems.Ui;
import items.Task;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteCommand extends Command{
    public DeleteCommand(String keyword, ArrayList<String> params) {
        super(keyword, params);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.params.size() != 1) {
            throw new IllegalInputException("Wrong number of arguments for this command!");
        }
        try {
            int deleteIndex = Integer.parseInt(this.params.get(0));
            Task deleted = tasks.delete(deleteIndex);
            storage.refreshStorage(tasks);
            ui.display("Successfully deleted the following task! \n" +
                    deleted.toString());
        } catch (NumberFormatException e){
            throw new IllegalInputException("Invalid task index chosed!");
        } catch (TaskListIndexOutOfBoundsException e) {
            ui.errorDisplay(e);
        } catch (IOException e) {
            ui.display("Seems like there is something wrong with the storage file \n" +
                    "Any updates will not be saved!");
            ui.errorDisplay(e);
            e.printStackTrace();

        }
    }
}
