package duke.command;

import duke.action.Task;
import duke.data.TaskList;
import duke.data.TypeOfTask;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Find command when user specifies "find" in their input
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public class FindCommand extends Command {

    /**
     * Default constructor
     * @param contents Contents of user input
     */
    public FindCommand(String[] contents) {
        super(contents, false);
    }

    /**
     * Finds all similar tasks that matches the description given by the user
     * @param taskList List of tasks
     * @param ui Ui object
     * @param storage Storage object
     * @throws DukeException when there are no matches or user enters illegal inputs
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        List<Task> temporaryTaskList = new ArrayList<Task>();
        String taskDescription = new Parser().convertToUserInput(super.getContents(), TypeOfTask.find," ");
        for(Task currentTask : taskList.getTasks()) {
            if (currentTask.getDescription().contains(taskDescription) || currentTask.getDescription().equalsIgnoreCase(taskDescription)) {
                temporaryTaskList.add(currentTask);
            }
        }
        if(temporaryTaskList.size() == 0) {
            throw new DukeException(TypeOfTask.find,1);
        } else {
            ui.displayResult(TypeOfTask.find,null, taskList);
            for(Task tempTask: temporaryTaskList) {
                System.out.println(tempTask.toString());
            }
        }
    }
}
