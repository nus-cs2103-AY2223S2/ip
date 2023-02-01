
package Duke.Tasks;
/**
 * Contains the ToDo class
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * The contructor for Task class
     *
     * @param description
     *
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * The method for getSymbol
     *
     */
    public String getSymbol() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * The getter method for getting description
     *
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * The method for markDone
     *
     */
    public void markDone(){
        this.isDone = true;
    }

    /**
     * The method for markNotDone
     *
     */
    public void markNotDone(){
        this.isDone = false;
    }

    /**
     * The method for contains
     *
     */
    public boolean contains(String task) {
        return  description.contains(task);
    }

    /**
     * Obtain the string representation of the event task.
     *
     * @return string representation of the event task
     */
    @Override
    public String toString(){
        return this.getSymbol() + " "  + this.getDescription();
    }

    /**
     * The changeFormat method for change the format when  saving/Writing  tasks in data.txt file
     * @return String
     */
    public String changeFormat() {
        String indicator = isDone ? "1" : "0";
        return "/ " + indicator + " / " + this.description;

    }
}