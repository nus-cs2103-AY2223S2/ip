package tasks;

public class Task {

    private String name;
    private boolean isDone = false;

    /**
     * Constructor to create task object
     */
    public Task(String name){
        this.name = name;
    }

//    public boolean getStatus(){
//        return this.isDone;
//    }

    /**
     * sets task as marked
     */
    public void setAsMarked(){
        isDone = true;
    }

    /**
     * gets description of task
     * @return
     */
    public String getDescription(){
        return this.name;
    }

    /**
     * retrieves status of task
     * @return String
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * set task as not done
     */
    public void setAsUnmarked(){
        isDone = false;
    }

    /**
     * Returns the string representation of the tasks.Task object.
     *
     * @return the string listing the elements in duke.TaskList
     */
    @Override
    public String toString(){
        if(isDone)
            return "[X] " + name;
        else
            return "[ ] " + name;
    }
}