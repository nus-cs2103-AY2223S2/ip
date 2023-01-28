package duke.command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int counter = 1;
        ui.showListMessage();
        for (int i = 0; i < tasks.getLength(); i++) {
            System.out.println(counter + "." + tasks.getTask(i).toString());
            counter++;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
