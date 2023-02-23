package duke.task;

import java.util.List;

/**
 * Represents task type of event.
 */
public class Todo extends Task {
    private final String taskType;

    public Todo(int taskNumber, boolean isMarked, String task, int totalNumOfTasks) {
        super(taskNumber, isMarked, task, totalNumOfTasks);
        this.taskType = "[T]";
    }

    /**
     * Prints that task is marked as done when task is done.
     *
     * @return String response that show task is marked as done.
     */
    @Override
    public String markAsDone() {
        return "\t Nice! I've marked this task as done:\n"
                + "\t  " + this.taskType + "[X]" + " " + this.task;
    }

    /**
     * Prints that task is marked as not done when task is unmarked.
     *
     * @return String response that show task is unmarked as undone.
     */
    @Override
    public String unmarkAsUndone() {
        return "\t OK, I've marked this task as not done yet:\n"
                + "\t  " + this.taskType + "[ ]" + " " + this.task;
    }

    /**
     * Prints task information when added to task list.
     *
     * @return String response that show task is added.
     */
    public String printToDoTask() {
        return "\t Got it. I've added this task:"
                + "\n\t   [T]" + super.getTaskStatus() + " " + super.task
                + "\n\t Now you have " + super.totalNumOfTasks + " tasks in the list.";
    }

    /**
     * Prints that task is deleted.
     *
     * @return String response that show task is deleted.
     */
    @Override
    public String printDelete(List<Task> allTasks) {
        return  "\t Noted. I've removed this task:" + "\n\t   " + this.taskType
                + super.getTaskStatus() + " " + super.task
                + "\n\t Now you have " + allTasks.size() + " tasks in the list.";
    }

    /**
     * Returns that this is a todo task type.
     *
     * @return Task type.
     */
    @Override
    public String getTaskType() {
        return this.taskType;
    }
}
