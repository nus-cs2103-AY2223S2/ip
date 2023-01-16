/** a class that represents a task
 *
 * @author Wong Yong Xiang
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /** constructor for the Task class
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** sets the isDone status of the Task
     *
     * @param status the new status of isDone
     */
    public void setDone(Boolean status) {
        this.isDone = status;
    }

    /** return the status of isDone as a string of either X or an empty space
     *
     * @return "X" if isDone is true and " " otherwise
     */
    public String toString() {
        String icon = this.isDone == true ? "[X]" : "[ ]";
        return icon + " " + description;
    }
}
