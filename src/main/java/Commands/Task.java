package Commands;

import Week2.src.main.TaskList;

/**
 * Superclass of all tasks.
 * It contains information of the task content and if it is done.
 * Superclass of other tasks.
 * It includes information of if it is done and also change it as done.
 */
public class Task {
    String content;
    boolean isDone = false;

    /**
     * Task constructor.
     * Contains the content of the task.
     * Constructor of Task.
     * It contains task content information.
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
     * Set task as not done, which is false in this class.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Gives whether the task is done or not.
     * @return "X" if it is done, or a blank if is not done.
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

    public String getContent() {
        return this.content;
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
