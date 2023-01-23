package Week2.src.main;

/**
 * Superclass of other tasks.
 * It includes information of if it is done and also change it as done.
 */
public class Task {
    String content;
    boolean isDone = false;

    /**
     * Constructor of Task.
     * It contains task content information.
     * @param content
     */
    Task(String content) {
        this.content = content;
    }

    /**
     * It marks the task as done (which is true in this class)
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * It shows whether the task is done or not.
     * @return It returns "X" if it is done, and a blank if is not.
     */
    public String getDone() {
        if(isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * Overriden toString() method which will be overriden again in subclasses.
     * @return
     */
    @Override
    public String toString() {
        return "";
    }

}
