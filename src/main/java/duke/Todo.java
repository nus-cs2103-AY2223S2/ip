package duke;


/**
 * Contains the Todo object
 */
public class Todo extends Task {

    /**
     * Public constructor
     *
     * @param value = name of the task
     * @param mark = the status of the task, whether it is completed
     */
    public Todo(String value,boolean mark) {
        super(value,mark);
    }

    /**
     *
     * @return = returns a string that represents the todo object
     *         that is stored in a file
     */
    public String toFile() {
        return "todo," + super.isMark() + "," + super.getValue();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
