package duke.command;

import static duke.command.CommandValidations.validateNotEmptyArgs;

import java.time.LocalDate;

import duke.DukeException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A command that filters tasks that is relevant on a specific date.
 * @author Junyi
 */
public class CheckCommand extends Command {

    private final TaskList taskList;
    private final LocalDate targetDate;

    /**
     * Constructor for CheckCommand.
     * Display tasks that occurs before or during the target date.
     * @param taskList TaskList of Duke's tasks.
     * @param targetDate The mentioned date.
     */
    public CheckCommand(TaskList taskList, LocalDate targetDate) {
        this.taskList = taskList;
        this.targetDate = targetDate;
    }

    /**
     * Factory method to create check command from user input string
     * @param inputString The mentioned input string from user.
     * @param taskList TaskList of Duke's tasks.
     * @return An instance of CheckCommand.
     */
    public static CheckCommand createCheckCommand(String inputString, TaskList taskList) throws DukeException {
        validateNotEmptyArgs(inputString);
        String dueArgs = inputString.substring(6);
        LocalDate targetDate = LocalDate.parse(dueArgs);

        return new CheckCommand(taskList, targetDate);
    }

    @Override
    public String execute() throws DukeException {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Relevant tasks on specified date:  \n");
        for (Task task : taskList.allTasks()) {
            if (task instanceof EventTask) {
                EventTask eventTask = (EventTask) task;
                if (eventTask.checkDateDuringTask(targetDate)) {
                    stringBuilder.append(eventTask);
                    stringBuilder.append("\n");
                }
            } else if (task instanceof DeadlineTask) {
                DeadlineTask deadlineTask = (DeadlineTask) task;
                if (deadlineTask.checkDateIsBefore(targetDate)) {
                    stringBuilder.append(deadlineTask);
                    stringBuilder.append("\n");
                }
            }
        }

        return stringBuilder.toString();
    }
}
