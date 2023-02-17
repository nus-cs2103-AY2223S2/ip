package duke.tasks;
import java.time.LocalDateTime;

import duke.Ui;


/**
 * Represents the Task information with
 * its status and description
 */
public abstract class Task {
    private String taskText;
    private boolean isCompleted;

    private Ui ui;

    /**
     * Creates a new Task with the given task description
     * with deafult status as false.
     * @param taskText description of the task
     */
    public Task(String taskText) {
        this.taskText = taskText;
        this.isCompleted = false;
        this.ui = new Ui();
    }

    /**
     * Changes the status of the task to the opposite of its current status.
     */
    public void changeStatus() {
        if (this.isCompleted) {
            this.isCompleted = false;
        } else {
            this.isCompleted = true;
        }
    }

    /**
     * Returns the status of the task
     *
     * @return boolean status of the task
     */
    public boolean getStatus() {
        return this.isCompleted;
    }

    /**
     * Returns the status of the task in String format
     *
     * @return String status of the task
     */
    public String getCurrentStatus() {
        return this.isCompleted ? "1" : "0";
    }

    private String getStatusText() {
        return isCompleted ? "X" : " ";
    }

    /**
     * Returns the task text
     * @return String task text
     */
    public String getTaskText() {
        return this.taskText;
    }

    public abstract String writeFile();

    public abstract LocalDateTime getDate();

    public abstract String getTaskType();

    /**
     * Returns the string representation of task status
     * and description.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusText(), this.taskText);
    }


}
