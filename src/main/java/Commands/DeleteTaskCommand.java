package Commands;


import Exceptions.DukeException;
import Files.Storage;
import Tasks.TaskList;
import Ui.Ui;

public class DeleteTaskCommand extends Command {
    private String indexString;
    public DeleteTaskCommand(String command) {
        super();
        this.indexString = command;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.deleteTask(this.indexString);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

    }
}
