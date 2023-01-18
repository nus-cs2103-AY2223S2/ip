package seedu.shao;

import java.time.LocalDateTime;

public class Event extends Task {
	protected LocalDateTime from;
	protected LocalDateTime to;

	public Event(String description, LocalDateTime[] fromTo) {
		super(description);
		this.from = fromTo[0];
		this.to = fromTo[1];
	}

	@Override
	public String toString() {
		return "[E]" + super.toString() +
				String.format(" (from: %s, to: %s)", dateTimeString(from), dateTimeString(to));
	}

	@Override
	public String getSavedFormat() {
		return String.format("E|%d|%s|%s|%s", isDone ? 1 : 0, description, from, to);
	};
}
