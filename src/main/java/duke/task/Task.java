<<<<<<< HEAD:src/main/java/Duke/Task/Task.java
package Duke.Task;

/**
 * Class to represent the Task created by user
 */
=======
package duke.task;
>>>>>>> branch-A-CodingStandard:src/main/java/duke/task/Task.java
public class Task {
    private boolean status = false;
    private String taskName;

    public Task (String name) {
        this.taskName = name;
    }

    public boolean getStatus() {
        return this.status;
    }

    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Change the status of Task to state
     *
     * @param state State of Task to be changed into
     */
    public void setStatus(boolean state) {
        this.status = state;
    }
}
