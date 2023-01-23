package task;

/**
 * A deadline class extends from the Task.Task.
 * It has a deadline attribute on top of the task.
 */

import java.time.LocalDate;

import duke.DukeException;

public class Deadline extends Task {
    protected LocalDate time;

    /***
     *
     * @param description: the content of the user command
     * @throws DukeException when the command is incomplete
     */
    public Deadline(String description) throws DukeException {
        super();
        int indexOfBy = description.indexOf("/by");
        try {
            this.time = Task.parseDate(description.substring(indexOfBy + "/by ".length()));
            this.name = description.substring(0, indexOfBy - " ".length());
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("The command argument is not complete.");
        }
        this.type = "D";
    }

    /**
     * Returns the string representation
     *
     * @return string representation of a deadline task, where the deadline is specified
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", type, super.toString(), Task.printDate(time));
    }
}
