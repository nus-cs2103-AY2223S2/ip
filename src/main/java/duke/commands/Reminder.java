package duke.commands;
import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Deadline;

/**
 * Reminder is a subclass of Command that looks through all the deadlines and find ones that are due the next day
 */
public class Reminder extends Command {
    /**
     * This is a method that is used to find the tasks that are due in the next 3 days.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            assert tasks.size() > 0 : ui.printNoTasksError();
        } catch (AssertionError a) {
            return a.getMessage();
        }

        StringBuilder listOfTasks = new StringBuilder("Here are the deadlines due soon:\n");
        LocalDate today = LocalDate.now();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTask(i) instanceof Deadline) {
                Deadline currentTask = (Deadline) tasks.getTask(i);
                int daysBeforeDeadline = today.compareTo(currentTask.getDeadline());
                if (daysBeforeDeadline <= 0 && daysBeforeDeadline >= -3) {
                    listOfTasks.append(i + 1).append(". ").append(currentTask).append("\n");
                }
            }
        }
        return listOfTasks.toString();
    }
}
