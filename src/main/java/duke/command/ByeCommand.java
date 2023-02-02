package duke.command;
import duke.data.TaskList;
import duke.data.TypeOfTask;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Bye command class when user types "bye" or "quit"
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public class ByeCommand extends Command {

    /**
     * default constructor
     */
    public ByeCommand() {
        super(null,true);
    }
    
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        // need to save the updated taskList to the storage
        // the ui will be responsible in displaying whatever needs to be displayed -> not the responsibility of the command classes
        ui.displayResult(TypeOfTask.bye,null,null);
        storage.saveTasks(tasklist.getTasks());
    }
}
