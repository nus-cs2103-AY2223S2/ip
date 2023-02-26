package Ava.tasks;


public class Task implements Comparable<Task> {
    protected String message;
    protected boolean marked = false;
    public static final String TASK_SIGN = "";
    protected TimeParser deadline = TimeParser.NO_DEADLINE;

    /**
     * Constructor for the Task
     * @param m message string
     */
    public Task(String m) {
        this.message = m;
    }

    /**
     * Getter for the message inside task
     * @return this.message
     */
    public String getMessage(){

        return this.message;
    }

    /**
     * Getter for the status icon of marked or unmarked
     * @return String of marked or unmarked icon
     */
    public String getStatusIcon(){

        return (this.isMarked()? "[X]": "[ ]");
    }

    /**
     * @return Return the Storage Representation of the Task
     */
    public String getStorageFormat() {
        return TASK_SIGN + "," + this.isMarked()+","+this.getMessage();
    }

    /**
     * @return mark/unmark sign + message
     */
    public String getRepresentation(){

        return this.getStatusIcon() + " " + this.getMessage();
    }

    /**
     * Compares the Deadline, if same compares the message
     * @param other
     * @return
     */
    @Override
    public int compareTo(Task other){
        int first_indi = deadline.compareTo(other.deadline);
        if (first_indi == 0 ){
            return message.compareTo(other.message);
        }
        return first_indi;
    }


    /**
     * Toggles the marked boolean to true
     */
    public void mark() {

        this.marked = true;
    }

    /**
     * toggles the marked boolean to false
     */
    public void unmark(){

        this.marked = false;
    }

    /**
     * Return the current value of marked feild
     * @return this.marked
     */
    public boolean isMarked(){

        return this.marked;
    }

    /**
     * Check if the task message contains key
     * @param key string to be checked
     * @return boolean indicate if message is contained
     */
    public boolean taskContains(String key){
        return this.message.contains(key);
    }


}
