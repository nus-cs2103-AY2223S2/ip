package duke.command;
import duke.data.TaskList;
import duke.data.TypeOfTask;
import duke.action.Event;
import duke.action.Task;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class EventCommand extends Command {
    
    public EventCommand(String[] contents) {
        super(contents,false);
    }

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
