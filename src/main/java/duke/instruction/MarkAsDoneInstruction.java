package duke.instruction;

import duke.exception.GeneralDukeException;
import duke.exception.InvalidInputException;
import duke.task.GeneralDukeTask;
import duke.task.TaskList;

public class MarkAsDoneInstruction extends GeneralDukeInstruction{
    private final int taskIndex;

    public MarkAsDoneInstruction(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public boolean isValidIndex(TaskList list) {
        return taskIndex >= 0 && taskIndex <= list.remainingTasks();
    }

    public boolean isEmpty(TaskList list) {
        return list.remainingTasks() == 0;
    }

    @Override
    public void run(TaskList list) throws GeneralDukeException {
        if (isEmpty(list)) {
            String errorMessage = "☹ OOPS!!! Your task list is currently empty";
            throw new InvalidInputException(errorMessage + "\nPlease add in more tasks");
        } if (!isValidIndex(list)) {
            String errorMessage = "☹ OOPS!!! The input index is not within the range of [1, "
                    + list.remainingTasks() + "]";
            throw new InvalidInputException(errorMessage + "\nPlease input a valid index");
        } else {
            GeneralDukeTask currentTask = list.getTask(this.taskIndex);
            currentTask.markAsDone();
            format.displayWithBar("Nice! I've marked this task as done:\n " +
                    currentTask);
        }
    }
}
