/**
 * Task is the superclass of the activities that can be stored within Duke.
 */
public abstract class Task {

    private String taskName;
    private STATUS status;

    public enum STATUS {
        COMPLETE, INCOMPLETE
    }

    /**
     * Creates a Task with the provided Task Name.
     * By default, created Tasks are marked as incomplete.
     * @param task_name The name of the uncompleted Task.
     */
    public Task(String task_name){
        this.taskName = task_name;
        this.status = STATUS.INCOMPLETE;
    }

    /**
     * Setter for Task state.
     */
    public void setComplete(STATUS status){
        this.status = status;
    }


    /**
     * Returns the state of the task alongside with the task name.
     *
     * Example output:
     * > `[X] read book` would mean that the task `read book` is complete.
     * > `[ ] read book` would mean that the task `read book` is incomplete.
     *
     * @return A string representation of this task and it's state.
     */
    @Override
    public String toString(){
        return String.format("[%s] %s", this.status == STATUS.COMPLETE ? "X" : " ", this.taskName);
    }
}
