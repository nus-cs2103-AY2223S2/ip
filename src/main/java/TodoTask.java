/**
 * {@link UserTask} that has no time whatsoever
 */
public class TodoTask extends UserTask {
    /**
     * Bracketed icon of task type.
     */
    public final static String label = getTaskTypeLabel(Resource.cmdTodo);

    /**
     * @param desc Non-null. Description string of task with command removed.
     */
    public TodoTask(String desc) throws MeggyException {
        super(desc);
    }

    @Override
    public String toString() {
        return label + super.toString();
    }
}
