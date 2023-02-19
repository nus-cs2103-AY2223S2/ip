package task;

import duke.DukeException;
import duke.IncompleteCommandDukeException;

import java.time.LocalDate;

import ui.Parser;

/**
 * A deadline class extends from the Task.Task.
 * It has a deadline attribute on top of the task.
 */
public class Deadline extends Task {
    protected static final String DEADLINE_KEYWORD = "/by";
    protected LocalDate time;

    /***
     * Constructor.
     *
     * @param description the content of the user command
     * @throws DukeException when the command is incomplete
     */
    public Deadline(String description) throws DukeException {
        super();
        int indexOfBy = getIndexOfBy(description);
        this.time = parseDeadline(description);
        this.name = description.substring(0, indexOfBy - " ".length());
        this.type = "D";
    }

    /**
     * Parses the index of bye
     * @param description the user-input description
     * @return the index of the bye keyword
     * @throws DukeException when the keyword cannot be found
     */
    private int getIndexOfBy(String description) throws DukeException {
        return getKeywordIndex(description, DEADLINE_KEYWORD);
    }

    /**
     * Parses the deadline from description
     * @param description user-input description
     * @throws DukeException when the deadline is unrecognizable or incomplete
     */
    private LocalDate parseDeadline(String description) throws DukeException {
        int indexOfBy = getIndexOfBy(description);
        try {
            String deadline = description.substring(indexOfBy + (DEADLINE_KEYWORD + " ").length());
            return Parser.parseDate(deadline);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IncompleteCommandDukeException("The deadline date cannot be parsed. ");
        }
    }

    /**
     * Returns the string representation of the task.
     * The deadline date is included.
     *
     * @return string representation of a deadline task, where the deadline is specified
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", type, super.toString(), Task.printDate(time));
    }
}
