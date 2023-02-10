package twofive.task;

import java.time.LocalDate;
import java.util.ArrayList;

import twofive.exception.TaskDoneException;
import twofive.exception.TaskUndoneException;

/**
 * Represents a tasks which can either be a ToDo, a Deadline or an Event.
 */
public abstract class Task {
    private String taskDescription;
    private boolean isDone;

    /**
     * Represents a constructor for the Task class.
     *
     * @param taskDescription Description of the task.
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    /**
     * Returns a String with an "X" in a bracket if the task is done,
     * or an empty bracket otherwise.
     *
     * @return String indicating whether the task is done.
     */
    public String getTaskStatus() {
        return isDone ? "Completed" : "Undone";
    }

    /**
     * Marks the current task as done if it is not yet done previously.
     *
     * @throws TaskDoneException If task is already done.
     */
    public void setDone() throws TaskDoneException {
        // Only mark as done if task is not done
        if (!this.isDone) {
            this.isDone = true;
        } else {
            throw new TaskDoneException();
        }
    }

    /**
     * Marks the current task as not done if it is already done previously.
     *
     * @throws TaskUndoneException If task is not yet done.
     */
    public void setUndone() throws TaskUndoneException {
        //Only remark as not done if task is done
        if (this.isDone) {
            this.isDone = false;
        } else {
            throw new TaskUndoneException();
        }
    }

    @Override
    public String toString() {
        return this.getTaskStatus() + " " + this.taskDescription;
    }

    /**
     * Returns a String which includes whether the task is done and its
     * description to be saved into a local file.
     *
     * @return String containing details of task to be saved to file.
     */
    public String getFileWriteString() {
        String isDoneString = this.isDone ? "1" : "0";
        return " | " + isDoneString + " | " + this.taskDescription;
    }

    /**
     * Returns whether the current task has the same deadline, start time
     * or end time as the given date.
     *
     * @param date Due date
     * @return Boolean indicating whether the task is due on the given date.
     */
    public abstract boolean isToday(LocalDate date);

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Task)) {
            return false;
        }

        Task task = (Task) o;

        return task.taskDescription.equals(this.taskDescription) && task.isDone == this.isDone;
    }

    /**
     * Returns whether the current task has the specified keyword in its description.
     *
     * @param keyword Keyword
     * @return Boolean indicating whether the task description includes the keyword.
     */
    public boolean hasKeyword(String keyword) {
        assert !taskDescription.isEmpty() : "Task description should not be empty";
        return this.taskDescription
                .toLowerCase()
                .contains(keyword.toLowerCase());
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public abstract ArrayList<String> getTaskDetails();
}
