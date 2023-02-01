package duke;

public class Deadline extends Task {

    protected Times ddline;
    protected String type = "[D]";

    /**
     * Constructor for Deadline
     * @param description -> Task description
     * @param by -> String format of time deadline
     */
    public Deadline(String description, String by) {
        super(description);
        ddline = new Times(by);
    }

    /**
     * Adds new deadline task.
     * Format: "deadline {description} /by {time}
     * example: deadline return book /by 2019-10-15 1530
     * @param input Input Chat which starts with "deadline"
     * @throws DukeException if format of deadline task is wrong.
     */
    public static Deadline createDeadline(String input) throws DukeException {
        int ddlineIndex = input.indexOf("/");
        if (ddlineIndex == -1) {
            throw new DukeException(ExceptionType.TASK_FORMAT_ERROR);
        }
        String description = input.substring(9, ddlineIndex - 1);
        String by = input.substring(ddlineIndex + 4);
        Deadline temp = new Deadline(description, by);
        return temp;
    }
    @Override
    public String toString() {
        return type + super.toString() + " (by: " + ddline + ")";
    }

    @Override
    public String getDescriptionAndTime() {
        return description + " (by: " + ddline + ")";
    }

}
