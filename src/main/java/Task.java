/**
 * Task is the superclass of the activities that can be stored within Duke.
 */
public abstract class Task {

    private String taskName;
    private boolean status;

    /**
     * Creates a Task with the provided Task Name.
     * By default, created Tasks are marked as incomplete.
     * @param task_name The name of the uncompleted Task.
     */
//    public Task(String task_name){
//        this.taskName = task_name;
//        this.status = false;
//    }

    public Task(String task_name, Boolean status){
        this.taskName = task_name;
        this.status = status;
    }

    /**
     * Setter for Task state.
     */
    protected void setComplete(boolean status){
        this.status = status;
    }

    public boolean getComplete(){
        return this.status;
    }

    public String getName(){
        return this.taskName;
    }

    /**
     * Returns the state of the task alongside with the task name.
     *
     * Example output:
     * > `[X] read book` would mean that the task `read book` is complete.
     * > `[ ] read book` would mean that the task `read book` is incomplete.
     *
     * @return A string representation of this task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.status ? "X" : " ", this.taskName);
    }

    public abstract String toCSV();
}
