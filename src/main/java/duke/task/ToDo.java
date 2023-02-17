package duke.task;

/**
 * Class for ToDo object.
 *
 * @author Andre Lin HuiKai
 * @version CS2103T AY22/23 Semester 2
 */
public class ToDo extends Task {
    /**
     * Constructor for a ToDo object.
     * @param des Description of the ToDo task.
     */
    public ToDo(String des) {
        super(des);
    }

    /**
     * Displays formatted information about the task.
     * @return String containing information about ToDo.
     */
    @Override
    public String getStatusIcon() {
        return String.format("[T]%s", super.getStatusIcon());
    }

    /**
     * Encodes information about the event to be saved.
     * @return String representing encoded information about the task.
     */
    @Override
    public String encode() {
        return String.format("%s ### %s", "todo", super.encode());
    }
}
