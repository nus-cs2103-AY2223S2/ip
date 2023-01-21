package seedu.shao.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

abstract public class Task {
	protected String description;
	protected boolean isDone;

	public Task(String description) {
		this.description = description;
		this.isDone = false;
	}

	public String getStatusIcon() {
		return (isDone ? "X" : " "); // mark done task with X
	}

	public void markAsDone() {
		this.isDone = true;
	}

	public void markAsUndone() {
		this.isDone = false;
	}

	public String dateTimeString(LocalDateTime dt) {
		return dt.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
	}

	public String formatSavedDateTime(LocalDateTime dt) {
		return dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
	}

	@Override
	public String toString() {
		return String.format("[%s] %s", getStatusIcon(), description);
	}

	public abstract String getSavedFormat();
}
