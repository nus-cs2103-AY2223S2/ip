package duke.task;

/**
 * Class to support ToDo tasks.
 * ToDos are tasks without any date/time attached to it.
 */
public class ToDo extends Task {

    /**
     * Constructor to create a ToDo task.
     *
     * @param description String containing ToDo description.
     * @param tag String containing ToDo tag.
     */
    public ToDo(String description, String tag) {
        super(description, tag);
    }

    public ToDo(String description) {
        this(description, null);
    }


    /**
     * @inheritDoc
     *
     * @return "T".
     */
    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "] " + super.toString();
    }
}
