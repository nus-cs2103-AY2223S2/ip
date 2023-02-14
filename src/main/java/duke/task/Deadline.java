package duke.task;

import java.time.LocalDateTime;

/**
 * {@code Deadline} class encapsulates a deadline Task.
 */
public class Deadline extends Task {
    /**
     * {@code LocalDateTime} object that stores date and time
     * of deadline
     */
    protected LocalDateTime deadline;

    /**
     * Constructor method for {@code Deadline} class
     * @param description description of Deadline Task
     * @param deadline String representation of date and time of deadline to be reached
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = parseDateTime(deadline);
    }

    /**
     * Provides string representation of {@code Deadline} object
     * @return string representation of Deadline
     */
    @Override
    public String toString(){
        String deadlineString = "D" + " | " + super.toString()
                + " (by: " + this.deadline.toString() + ")";
        if (super.tag.toString().isEmpty()) {
            deadlineString += " | Tag: " + super.tag.toString();
        }
        return deadlineString;
    }

    /**
     * converts String to {@code LocalDateTime} object that contains day and time
     * of deadline
     * @param deadline String representation of day and time of deadline
     * @return new {@code LocalDateTime} object containing day and time
     */
    private LocalDateTime parseDateTime(String deadline) {
        String[] dateAndTime = deadline.split(" ");
        String[] dayMonthYear = dateAndTime[0].split("/");

        //Checks if 2 strings: date and time were given
        assert dateAndTime.length == 2;
        //Checks if 3 strings: day, month and year were given
        assert dayMonthYear.length == 3;

        int hour = Integer.parseInt(dateAndTime[1]) / 100;
        int minute = Integer.parseInt(dateAndTime[1]) % 100;
        int[] dateNumbers = new int[3];
        for (int i = 0; i < dayMonthYear.length; i++) {
            dateNumbers[i] = Integer.parseInt(dayMonthYear[i]);
        }

        return LocalDateTime.of(dateNumbers[2], dateNumbers[1], dateNumbers[0], hour, minute);
    }
}
