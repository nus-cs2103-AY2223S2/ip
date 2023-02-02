package duke.command;
import duke.data.TaskList;
import duke.data.TypeOfTask;
import duke.action.Event;
import duke.action.Task;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Event command when user specifies "event" at the start
 *
 * @author Haiqel Bin Hanaffi
 */
public class EventCommand extends Command {
    /**
     * Default constructor
     * @param contents Inputs from user
     */
    public EventCommand(String[] contents) {
        super(contents,false);
    }

    /**
     * Saves the task to the memory and displays the result
     * @param taskList List of tasks
     * @param ui Ui object
     * @param storage Storage object
     * @throws DukeException When saving of task is not possible due to unforseen errors
     */
    public void execute(TaskList taskList,Ui ui,Storage storage) throws DukeException {
        String description = super.parser.convertToUserInput(super.contents,TypeOfTask.event,"");
        // added additional variable to store the start and end time of event
        String startTime = super.parser.convertToUserInput(super.contents,TypeOfTask.event,"/from");
        String endTime = super.parser.convertToUserInput(super.contents,TypeOfTask.event,"/to");
        Task newTask = new Event(description,startTime,endTime);
        taskList.addTask(newTask);
        ui.displayResult(TypeOfTask.event, newTask, taskList);
    }
}
