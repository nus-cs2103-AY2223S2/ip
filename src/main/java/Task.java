abstract class Task {
	private String desc;
	private boolean isDone;

	public Task(String _desc) {
		desc = _desc;
		isDone = false;
	}

	public String desc() {
		return (isDone() ? "[X] " : "[ ] ") + desc;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone() {
		isDone = true;
	}

	public void setNotDone() {
		isDone = false;
	}

	public abstract String toString();
}


class Todo extends Task {
	public Todo(String desc) {
		super(desc);
	}

	@Override
	public String toString() {
		return "[T]" + desc();
	}
}


class Deadline extends Task {
	String deadline;

	public Deadline(String desc, String _deadline) {
		super(desc);
		deadline = _deadline;
	}

	@Override
	public String toString() {
		return "[D]" + desc() + String.format(" (by: %s)", deadline);
	}
}


class Event extends Task {
	String from, to;

	public Event(String desc, String _from, String _to) {
		super(desc);
		from = _from;
		to = _to;
	}

	@Override
	public String toString() {
		return "[E]" + desc() + String.format(" (from: %s to: %s)", from, to);
	}
}
