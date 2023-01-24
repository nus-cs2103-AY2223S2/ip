public class Deadline extends Task {
    private static final TaskCategory CATEGORY = TaskCategory.DEADLINE;
    static final String NAME_KEY = "name";
    static final String COMPLETED_KEY = "completed";
    static final String BY_KEY = "by";

    protected String by;

    public Deadline(String name, boolean completed, String by) throws DukeException {
        super(name, completed);
        this.by = by;
    }

    @Override
    public String serialize() {
        TaskSerializer ts = new TaskSerializer(CATEGORY);
        ts.add(NAME_KEY, name);
        ts.add(COMPLETED_KEY, completed);
        ts.add(BY_KEY, by);
        return ts.toString();
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
