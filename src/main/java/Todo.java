public class Todo extends Task {
    private static final TaskCategory CATEGORY = TaskCategory.TODO;
    static final String NAME_KEY = "name";
    static final String COMPLETED_KEY = "completed";

    public Todo(String name, boolean completed) throws DukeException {
        super(name, completed);
    }

    @Override
    public String serialize() {
        TaskSerializer ts = new TaskSerializer(CATEGORY);
        ts.add(NAME_KEY, name);
        ts.add(COMPLETED_KEY, completed);
        return ts.toString();
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
