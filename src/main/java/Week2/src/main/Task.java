package Week2.src.main;

/**
 * Superclass of all tasks.
 * It contains information of the task content and if it is done.
 */
public class Task {
    String content;
    boolean isDone;

    /**
     * Task constructor.
     * Contains the content of the task.
     * @param content
     */
    Task(String content) {
        this.content = content;
    }

    /**
     * Set the task to be done, which is true in this class.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Gives whether the task is done or not.
     * @return "X" if it is done, or a blank if is not done.
     */
    public String getDone() {
        if(isDone) {
            return "X";
        } else {
            return " ";
        }
    }


    public String getContent() {
        return this.content;
    }

    @Override
    public String toString() {
        return "";
    }

}
