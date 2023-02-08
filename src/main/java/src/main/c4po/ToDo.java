package src.main.c4po;
public class ToDo extends Task {

    /**
     * Instantiates a To.Do task
     * @param description a String describing the task
     */
    public ToDo(String description, Integer priority) {
        super(description, false, priority);
    }

    public ToDo(String description, boolean isDone, Integer priority) {
        super(description, isDone, priority);
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    protected String getTaskFileFormat() {
        return "T" + " | " + super.getTaskFileFormat();
    }
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public String getTaskInline() {
        return "[T]" + super.getTaskInline();
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public String getTaskInline(Integer index) {
        return index.toString() + ". [T]" + super.getTaskInline();
    }
}
