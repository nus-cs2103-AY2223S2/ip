package cloneAva.tasks;


/**
 * Todo class extends Task
 **/
public class Todo extends Task {
    public static final String TASK_SIGN = "[T]";

    /**
     * Todo Contructor
     * @param input string message
     */
    public Todo(String input) {
        super(input);
    }

    /**
     * @return task_sign + mark/unmark sign + message
     */
    @Override
    public String getRepresentation(){
        return TASK_SIGN + this.getStatusIcon() + " " + this.getMessage();
    }

    /**
     * @return storage representation of Todo
     */
    @Override
    public String getStorageFormat() {
        return TASK_SIGN + "," +this.isMarked()+","+this.message;
    }
}
