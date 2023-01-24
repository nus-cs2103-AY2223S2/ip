package twofive.task;

import twofive.exception.TaskDoneException;
import twofive.exception.TaskUndoneException;

import java.time.LocalDate;

public abstract class Task {
    private String taskDescription;
    private boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public String getTaskStatus() {
        // Done tasks are marked with X
        return (isDone ? "[X]" : "[ ]");
    }

    // Mark current task as done
    public void setDone() throws TaskDoneException {
        // Only mark as done if task is not done
        if (!this.isDone) {
            this.isDone = true;
        } else {
            throw new TaskDoneException();
        }
    }

    // Mark current task as undone
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

    public String getFileWriteString() {
        String isDoneString = this.isDone ? "1" : "0";
        return " | " + isDoneString + " | " + this.taskDescription;
    }

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
        return this.taskDescription
                .toLowerCase()
                .contains(keyword.toLowerCase());
    }
}
