package task;
/**
 * Task class is the base class from which other more specific tasks inherit from.
 * Specific tasks that inherit from this base class are Event, Deadline and Todo.
 *
 * @author      Tseng Chen-Yu
 * @version     %I%, %G%
 * @since       1.0
 */
public class Task {
    private String description;
    private boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    public String getStatusIcon(){
        return (isDone ? "X" : " "); //mark done task with X
    }
    public void setIsDone(boolean done){
        this.isDone = done;
    }
    public boolean getIsDone(){
        return this.isDone;
    }
    public String getDescription(){
        return this.description;
    }
    @Override
    public String toString() {
        return description;
    }
}
