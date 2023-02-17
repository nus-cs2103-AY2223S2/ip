package task;

import java.time.LocalDateTime;

public class Todo extends Task {

	public Todo(String description) {
		super(description);
	}

	@Override
	public String toString() {
		return "[T]" + super.toString();
	}

	@Override
	public String getSavedFormat() {
		return String.format("T|%d|%s", isDone ? 1 : 0, description);
	};

	@Override
	public boolean hasMatchDateTime(LocalDateTime dt) {
		return false;
	}
}
