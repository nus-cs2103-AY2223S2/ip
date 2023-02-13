package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Task that tracks general info and completion status
 * <p>
 * author Guo-KeCheng
 */
public abstract class Task implements Comparable<Task> {
    private boolean isCompleted; //by default the task should not be completed
    private final String task;

    /**
     * Task constructor
     *
     * @param task        Description of task
     * @param isCompleted Completion status
     */
    public Task(String task, boolean isCompleted) {
        this.task = task;
        this.isCompleted = isCompleted;
    }

    /**
     * Get the String representation of the task type
     *
     * @return String representation of the task type
     */
    public abstract String getTaskType();

    /**
     * Get the String representation of the completion status
     *
     * @return String representation of the completion status
     */
    public abstract String getStatus();

    /**
     * Get the String representation of the task description
     *
     * @return String representation of the task description
     */
    public abstract String getDescription();


    /**
     * Get the completion status of task
     *
     * @return completion status of task
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Set isCompleted as false
     */
    public void markUncompleted() {
        isCompleted = false;
    }

    /**
     * Set isCompleted as true
     */
    public void markCompleted() {
        isCompleted = true;
    }

    /**
     * Get the String representation of the task description
     *
     * @return String representation of the task description
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Convert Task Object to String to be saved in data file
     *
     * @return String representation of Task Object
     */
    public String encode() {
        return getTaskType() + " | " + getStatus() + " | " + getDescription();
    }

    public boolean containsKeyword(String keyword) {
        return this.task.contains(keyword);
    }

    public LocalDateTime getFirstEnd() {
        LocalDateTime startTime;

        if (this instanceof Deadline) {
            startTime = ((Deadline) this).getEndDate();
        } else if (this instanceof Event) {
            startTime = ((Event) this).getStartDate();
        } else {
            // Won't reach here
            startTime = null;
        }

        return startTime;
    }

    public LocalDateTime getSecondStart() {
        LocalDateTime startTime;

        if (this instanceof Deadline) {
            startTime = ((Deadline) this).getEndDate();
        } else if (this instanceof Event) {
            startTime = ((Event) this).getStartDate();
        } else {
            // Won't reach here
            startTime = null;
        }

        return startTime;
    }

    public LocalDateTime getSecondEnd() {
        LocalDateTime startTime;

        if (this instanceof Deadline) {
            startTime = ((Deadline) this).getEndDate();
        } else if (this instanceof Event) {
            startTime = ((Event) this).getEndDate();
        } else {
            // Won't reach here
            startTime = null;
        }

        return startTime;
    }

    /**
     * Override toString method
     *
     * @return String representation of task object including completion status
     */
    @Override
    public String toString() {
        return (isCompleted ? "[X] " + task : "[ ] " + task);
    }

}
