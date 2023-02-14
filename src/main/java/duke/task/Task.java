package duke.task;

import java.time.LocalDateTime;

/**
 * Class to represent the Task created by user
 */
public class Task implements Comparable<Task> {
    private boolean isCompleted = false;
    private String taskName;

    public Task(String name) {
        this.taskName = name;
    }

    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Change the status of Task to state
     *
     * @param state State of Task to be changed into
     */
    public void setIsCompleted(boolean state) {
        this.isCompleted = state;
    }

    public LocalDateTime getTaskEndTime() {
        if (this instanceof Deadline) {
            return ((Deadline) this).getDeadline();
        }
        if (this instanceof Event) {
            return ((Event) this).getEndTime();
        }
        return null;
    }

    @Override
    public int compareTo(Task task) {
        if (task instanceof Todo) {
            return -1;
        }
        if (this instanceof Todo) {
            return 1;
        }
        if (task.getTaskEndTime().isAfter(this.getTaskEndTime())) {
            return -1;
        }
        return 1;
    }
}
