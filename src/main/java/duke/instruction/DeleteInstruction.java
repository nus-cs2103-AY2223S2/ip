package duke.instruction;

import duke.exception.GeneralDukeException;
import duke.exception.InvalidInputException;
import duke.task.TaskList;

public class DeleteInstruction extends GeneralDukeInstruction {
    private final int taskIndex;

    public DeleteInstruction(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public boolean isEmpty(TaskList list) {
        return list.remainingTasks() == 0;
    }

    public boolean isValidIndex(TaskList list) {
        return taskIndex >= 0 && taskIndex <= list.remainingTasks();
    }

    @Override
    public void run(TaskList list) throws GeneralDukeException {
        if (isEmpty(list)) {
            String errorMessage = "☹ OOPS!!! Your task list is currently empty";
            throw new InvalidInputException(errorMessage + "\nPlease add in more tasks");
        }
        if (!isValidIndex(list)) {
            String errorMessage = "☹ OOPS!!! The input index is not within the range of [1, "
                    + list.remainingTasks() + "]";
            throw new InvalidInputException(errorMessage + "\nPlease input a valid index");
        } else {
            format.displayWithBar("Noted. I've removed this task:\n " +
                    list.getTask(taskIndex) + "\nNow you have " +
                    (list.remainingTasks() - 1) + " tasks in the list.");
            list.deleteTask(this.taskIndex);
        }
    }
}
