/**
 * DUKE
 * CS2103 project
 * @author EDWIN LIM
 * @version 0.01
 */

/**
 * The task class represents a task.
 */
public class Task {
    private boolean status;
    private String task_name;

    /**
     * Creates a Task with the provided Task name.
     * By default, a Tasks is not completed.
     * @param task_name
     */
    public Task(String task_name){
        this.task_name = task_name;
        this.status = false;
    }

    /**
     * Switches the state of the Task
     */
    public void setStatus(boolean TF){
        this.status = TF;
    }

    /**
     * Returns the status of this Task.
     * @return True if Task is marked as complete, otherwise False.
     */
    public boolean status() {
        return status;
    }

    @Override
    public String toString(){
        return String.format("[%s] %s", this.status ? "X" : " ", this.task_name);
    }
}
