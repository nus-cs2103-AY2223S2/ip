/**
 * Users task that has no time whatsoever
 */
public class TodoTask extends UserTask {
    public final static String label = getTaskTypeLabel(Resource.cmdTodo);

    /**
     * @param desc Parsed string description of task.
     */
    public TodoTask(String desc) throws MeggyException {
        super(desc);
    }

    @Override
    public String toString() {
        return label + super.toString();
    }
}
