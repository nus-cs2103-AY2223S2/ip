package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The Bye class is a subclass of Command as it is a type of command. 
 * It implements the execute method and changes isExit method to return true so that duke will stop running
 */
public class Bye extends Command{

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTaskList(tasks);
        return ui.printExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
