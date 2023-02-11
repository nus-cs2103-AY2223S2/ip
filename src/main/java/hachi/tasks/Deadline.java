package hachi.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a Task with a deadline.
 */
public class Deadline extends Task {
    protected String time;

    /**
     * Ddl constructor.
     *
     * @param input The description of the task.
     * @param time  The deadline of the task.
     */
    public Deadline(String input, String time) {
        super(input);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate output = LocalDate.parse(time, dateFormatter);
        DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("MMM dd yyyy");
        this.time = output.format(newPattern);
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
     * Returns the string representation of the Ddl object with
     * specified description and date.
     *
     * @return String representation of the Ddl object
     */
    public String toString() {
        return "   [D]" + super.toString() + " (by: " + this.time + ")";
    }

}
