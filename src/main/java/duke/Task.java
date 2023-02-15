package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Contains all information for tasks in the task list
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task object with a specific description
     *
     * @param description what the task is about
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the txt formatted version of the referenced tasks
     *
     * @return txt formatted String of the referenced task
     */
    public String toData() {
        return "";
    }

    public static class Todo extends Task {

        /**
         * Creates to-do object of the given description
         *
         * @param description what the to-do object is about
         */
        public Todo(String description) {
            super(description.strip());
        }

        /**
         * Returns the txt formatted version of the referenced to-do task
         *
         * @return the txt formatted version of the referenced to-do task
         */
        @Override
        public String toData() {
            int isDoneData = 0;
            if (this.isDone) {
                isDoneData = 1;
            }
            return String.format("T|%d|%s", isDoneData, this.description);
        }

        /**
         * Returns the String of the referenced to-do task
         *
         * @return the String of the referenced to-do task
         */
        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Deadline extends Task {

        protected LocalDate by;

        /**
         * Creates deadline object of the given description and finishing date
         *
         * @param description the description of the task
         * @param by the deadline that the class is to be completed
         */
        public Deadline(String description, String by) {
            super(description.strip());
            this.by = LocalDate.parse(by.strip());
        }

        /**
         * Returns the txt formatted version of the referenced deadline task
         *
         * @return the txt formatted version of the referenced deadline task
         */
        @Override
        public String toData() {
            int isDoneData = 0;
            if (this.isDone) {
                isDoneData = 1;
            }
            return String.format("D|%d|%s|%s", isDoneData, this.description, this.by);
        }

        /**
         * Returns the String of the referenced deadline task
         *
         * @return the String of the referenced deadline task
         */
        @Override
        public String toString() {
            String byFormatted = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return "[D]" + super.toString() + " (by: " + byFormatted + ")";
        }
    }

    public static class Event extends Task {

        protected LocalDate from;
        protected LocalDate to;

        /**
         * Creates an event object of the given description and time period
         *
         * @param description the description of the task
         * @param from the start of the event
         * @param to the end of the event
         */
        public Event(String description, String from, String to) {
            super(description.strip());
            this.from = LocalDate.parse(from.strip());
            this.to = LocalDate.parse(to.strip());

        }

        /**
         * Returns the txt formatted version of the referenced event task
         *
         * @return the txt formatted version of the referenced event task
         */
        @Override
        public String toData() {
            int isDoneData = 0;
            if (this.isDone) {
                isDoneData = 1;
            }
            return String.format("E|%d|%s|%s|%s", isDoneData, this.description, this.from, this.to);
        }

        /**
         * Returns the String of the referenced event task
         *
         * @return the String of the referenced event task
         */
        @Override
        public String toString() {
            String fromFormatted = from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            String toFormatted = to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return "[E]" + super.toString() + " (from: " + fromFormatted + " to: " + toFormatted + ")";
        }
    }

    /**
     * Returns the status of the referenced task
     *
     * @return String representation of the status of the referenced task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the current task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the current task as undone
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the String of the referenced task
     *
     * @return the String of the referenced task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}