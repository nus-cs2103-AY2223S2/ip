package duke.task;

/**
 * Class to support ToDo tasks.
 * ToDos are tasks without any date/time attached to it.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
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
