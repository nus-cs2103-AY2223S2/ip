package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class Bye extends Command{
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTaskList(tasks);
        ui.printExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
