import java.util.HashMap;

public class TaskSerializer {
    private static final String GROUP_DELIMITER = String.valueOf((char)0x1d);
    private static final String RECORD_DELIMITER = String.valueOf((char)0x1e);

    private HashMap<String, Object> map;
    private TaskCategory category;

    public static TaskSerializer deserialize(String s) {
        String[] entries = s.split(GROUP_DELIMITER);
        TaskCategory category = TaskCategory.fromString(entries[0]);
        TaskSerializer ts = new TaskSerializer(category);
        for (int i = 1; i < entries.length; i++) {
            String[] keyVal = entries[i].split(RECORD_DELIMITER);
            ts.add(keyVal[0], keyVal[1]);
        } 
        return ts;
    }

    public TaskSerializer(TaskCategory category) {
        this.map = new HashMap<>();
        this.category = category;
    }

    public void add(String key, Object value) {
        map.put(key, value);
    }

    public Object get(String key) {
        return map.get(key);
    }

    public boolean isCategory(TaskCategory category) {
        return this.category.equals(category);
    }

    public Task createTask() throws DukeException {
        if (isCategory(TaskCategory.DEADLINE)) {
            String name = get(Deadline.NAME_KEY).toString();
            boolean completed = Boolean.parseBoolean(get(Deadline.COMPLETED_KEY).toString());
            String by = get(Deadline.BY_KEY).toString();
            return new Deadline(name, completed, by);
        } 
        if (isCategory(TaskCategory.EVENT)) {
            String name = get(Event.NAME_KEY).toString();
			boolean completed = Boolean.parseBoolean(get(Event.COMPLETED_KEY).toString());
			String from = get(Event.FROM_KEY).toString();
			String to = get(Event.TO_KEY).toString();
			return new Event(name, completed, from, to);
        }
        if (isCategory(TaskCategory.TODO)) {
            String name = get(Todo.NAME_KEY).toString();
			boolean completed = Boolean.parseBoolean(get(Todo.COMPLETED_KEY).toString());
            return new Todo(name, completed);
        }
        throw new DukeException("Unable to create task from TaskSerializer");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(category);
        for (String key: map.keySet()) {
            sb.append(GROUP_DELIMITER);
            sb.append(key);
            sb.append(RECORD_DELIMITER);
            sb.append(map.get(key).toString());
        }
        return sb.toString();
    }
}
