package duke.tasks;

import java.time.LocalDate;

import duke.exceptions.TaskException;
import duke.ui.Ui;

/**
 * Represents a Deadline Task to be done with a deadline
 */
public class Deadline extends Task {
    private LocalDate date;
    private String time;
    private String[] period;


    /**
     * Acts as constructor for deadline class
     *
     * @param name  sets name of the tasks
     * @param frame sets deadline date
     */
    public Deadline(String name, String frame) {
        super(name);
        this.period = frame.split(" ");
        this.date = LocalDate.parse(period[0]);
        this.time = period[1];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTask(String input) throws TaskException {
        if (!input.contains("-by")) {
            Ui.error("deadline");
        }
        String[] inputPart = input.split("-by ");
        this.period = inputPart[1].split(" ");
        System.out.println("You are now updating item in Deadline task");
        super.updateTask(inputPart[0]);
        this.date = LocalDate.parse(period[0]);
        this.time = period[1];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + date.getDayOfMonth() + " "
                + date.getMonth() + " " + date.getYear() + ", " + time + " )";
    }
}
