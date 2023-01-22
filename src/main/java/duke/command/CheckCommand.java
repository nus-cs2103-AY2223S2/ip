package duke.command;

import duke.DukeException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDate;

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

    @Override
    public boolean execute() throws DukeException {
        System.out.println("Relevant tasks on specified date:  ");
        for (Task task : taskList.allTasks()) {
            if (task instanceof EventTask) {
                EventTask eventTask = (EventTask) task;
                if ((eventTask.getFrom().isEqual(targetDate) || eventTask.getFrom().isBefore(targetDate)) &&
                        (eventTask.getTo().isEqual(targetDate) || eventTask.getTo().isAfter(targetDate))) {
                    System.out.println(eventTask);
                }
            }
            else if (task instanceof DeadlineTask) {
                DeadlineTask deadlineTask = (DeadlineTask) task;
                if (deadlineTask.getBy().isEqual(targetDate) || deadlineTask.getBy().isAfter(targetDate)) {
                    System.out.println(deadlineTask);
                }
            }
        }

        return false;
    }
}
