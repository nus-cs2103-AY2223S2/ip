package hachi.tasks;

import hachi.main.HachiExceptions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a Task with a deadline.
 */
public class Deadline extends Task {
    private static final String tag = "D";
    protected LocalDate time;
    private static final DateTimeFormatter TO = DateTimeFormatter.ofPattern("YYYY-MM-dd");

    /**
     * Ddl constructor.
     *
     * @param input The description of the task.
     * @param time  The deadline of the task.
     */
    public Deadline(String input, LocalDate time) {
        super(input);
        this.time = time;
    }



    public boolean checkInput(Deadline taskOne, Deadline taskTwo) {
        return taskOne.input.equals(taskTwo.input);
    }

    public boolean checkTime(Deadline taskOne, Deadline taskTwo) {
        return taskOne.time.equals(taskTwo.time);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline) {
            Deadline task = (Deadline) o;
            if (checkInput(task, this) || checkTime(task, this)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Formats the task into a string to be saved locally
     *
     * @return A String of tasks with specified format.
     */
    public String saveTask() {
        String completed = this.isDone ? "1" : "0";
        String formattedDate = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return this.tag + " | " + completed + " | "
                + this.input + " | " + formattedDate;
    }

    /**
     * Returns the string representation of the Ddl object with
     * specified description and date.
     *
     * @return String representation of the Ddl object
     */
    public String toString() {
        return "   [D]" + super.toString() + " (by: " + this.time.format(TO) + ")";
    }

}
