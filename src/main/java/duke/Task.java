package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task {
	protected String description;
	protected boolean isDone;

	public Task(String description) {
		this.description = description;
		this.isDone = false;
	}

	public String getStatusIcon() {
		return (isDone ? "X" : " ");
	}

	public void setDone() {
		this.isDone = true;
	}

	public void setUndone() {
		this.isDone = false;
	}

	public boolean matchesTask(String searchWord) {
		return this.description.contains(searchWord);
	}

	@Override
	public String toString() {
		return ("[" + this.getStatusIcon() + "] " + this.description);
	}

	public static class Todo extends Task {
		public Todo(String description) {
			super(description);
		}

		@Override
		public String toString() {
			return "[T]" + super.toString();
		}
	}

	public static class Deadline extends Task {
		protected LocalDate by;
		protected LocalTime when;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");

		public Deadline(String description, LocalDate by, LocalTime when) {
			super(description);
			this.by = by;
			this.when = when;
		}

		@Override
		public String toString() {
			return "[D]" + super.toString() + " (by: " + formatter.format(by) + " " + when + ")";
		}
	}

	public static class Event extends Task {
		protected String from;
		protected String to;

		public Event(String description, String from, String to) {
			super(description);
			this.from = from;
			this.to = to;
		}

		@Override
		public String toString() {
			return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
		}
	}
}
