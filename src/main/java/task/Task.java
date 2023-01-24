package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

abstract public class Task {
	protected String description;
	protected boolean isDone;

	public Task(String description) {
		this.description = description;
		this.isDone = false;
	}

	/**
	 * Get String representation of icon by task completed status.
	 * 
	 * @return String
	 */
	public String getStatusIcon() {
		return (isDone ? "X" : " "); // mark done task with X
	}

	public void markAsDone() {
		this.isDone = true;
	}

	public void markAsUndone() {
		this.isDone = false;
	}

	/**
	 * Get String representaion of an output datetime String.
	 * 
	 * @param dt
	 * @return String
	 */
	public String dateTimeString(LocalDateTime dt) {
		return dt.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
	}

	/**
	 * Get String representaion of an input datetime String.
	 * 
	 * @param dt
	 * @return String
	 */
	public String formatSavedDateTime(LocalDateTime dt) {
		return dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
	}

	@Override
	public String toString() {
		return String.format("[%s] %s", getStatusIcon(), description);
	}

	/**
	 * Get the String representation of a task read from a file.
	 * 
	 * @return String
	 */
	public abstract String getSavedFormat();
}
