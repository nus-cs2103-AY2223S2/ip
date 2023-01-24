public class Event extends Task {
	private static final TaskCategory CATEGORY = TaskCategory.EVENT;
	static final String NAME_KEY = "name";
	static final String COMPLETED_KEY = "completed";
	static final String FROM_KEY = "from";
	static final String TO_KEY = "to";

	protected String from;
	protected String to;

	public static Event deserialize(TaskSerializer ts) throws DukeException {
		Event event = null;
		if (ts.isCategory(CATEGORY)) {
			
		}
		return event;
	}

	public Event(String name, boolean completed, String from, String to) throws DukeException {
		super(name, completed);
		this.from = from;
		this.to = to;
	}

	@Override
	public String serialize() {
		TaskSerializer ts = new TaskSerializer(CATEGORY);
		ts.add(NAME_KEY, name);
		ts.add(COMPLETED_KEY, completed);
		ts.add(FROM_KEY, from);
		ts.add(TO_KEY, to);
		return ts.toString();
	}

	@Override
	public String toString() {
		return String.format("[%s]%s (from: %s to: %s)", CATEGORY, super.toString(), from, to);
	}
}
