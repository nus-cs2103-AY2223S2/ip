package duke.task;

/**
 * This class represents task of the type 'Todo'.
 */
public class Todo extends Tasks {

    /**
     * Construct a new Todo objcet with the given content and state.
     * @param content
     * @param isDone
     */
    public Todo(String content, boolean isDone) {
        super(content, isDone);
        this.type = 'T';
    }

    /**
     * Return the string representation of Todo object.
     * @return String
     */
    @Override
    public String toString() {
        return "[" + this.getTypeIcon() + "]"
                + "[" + this.getStatusIcon() + "] " + this.seeTaskContent();
    }
}
