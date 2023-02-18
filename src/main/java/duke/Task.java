package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Task class encapsulates
 * each task object.
 */
public class Task {
	protected String description;
	protected boolean isDone;

	/**
	 * Constructor for the task class.
	 *
	 * @param description The description of the task.
	 */
	public Task(String description) {
		this.description = description;
		this.isDone = false;
	}

	/**
	 * Gets the status of the current class
	 *
	 * @return A string representation of the status.
	 */
	public String getStatusIcon() {
		return (isDone ? "X" : " ");
	}

	public void setDone() {
		this.isDone = true;
	}

	public void setUndone() {
		this.isDone = false;
	}

	@Override
	public String toString() {
		return ("[" + this.getStatusIcon() + "] " + this.description);
	}


	/**
	 * Todo class that encapsulates the
	 * todo object.
	 */
	public static class Todo extends Task {

		/**
		 * Constructor for the todo class
		 *
		 * @param description The description of the todo task.
		 */
		public Todo(String description) {
			super(description);
		}

		@Override
		public String toString() {
			return "[T]" + super.toString();
		}
	}

	/**
	 * Deadline class encapsulates a
	 * deadline object.
	 */
	public static class Deadline extends Task {
		protected LocalDate by;
		protected LocalTime when;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");

		/**
		 * Constructor for the deadline class.
		 *
		 * @param description: The description of the task.
		 * @param by The date due for the deadline task.
		 * @param when The time due for the deadline task.
		 */
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

	/**
	 * Event class that encapsulates
	 * an event object.
	 */
	public static class Event extends Task {
		protected String from;
		protected String to;

		/**
		 * Constructor for the event class.
		 *
		 * @param description Description for the event class.
		 * @param from The starting time of the event.
		 * @param to The ending time of the event.
		 */
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
