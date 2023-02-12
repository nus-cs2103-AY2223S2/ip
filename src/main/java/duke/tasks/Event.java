package duke.tasks;

import java.time.LocalDate;

import duke.exceptions.TaskException;
import duke.ui.Ui;

/**
 * Represents an Event Task, which will happen between a start and end date and time
 */
public class Event extends Task {

    private LocalDate startDate;
    private LocalDate endDate;
    private String startTime;
    private String endTime;
    private String[] startingPeriod;
    private String[] endingPeriod;

    /**
     * Splits inputs into smaller parts and initialises its variables
     *
     * @param name         name of the task
     * @param startingTime start date and time of task
     * @param endTime      end date and time of task
     */
    public Event(String name, String startingTime, String endTime) {
        super(name);
        startingPeriod = startingTime.split(" ");
        endingPeriod = endTime.split(" ");

        this.startDate = LocalDate.parse(startingPeriod[0]);
        this.endDate = LocalDate.parse(endingPeriod[0]);

        this.startTime = startingPeriod[1];
        this.endTime = endingPeriod[1];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTask(String input) throws TaskException {
        boolean from = input.contains("-from");
        boolean to = input.contains("-to");

        if ((!from || !to) || !(from && to)) {
            Ui.error("event");
        }
        String[] inputPart = input.split("-from ");
        String[] periodRange = inputPart[1].split("-to ");
        startingPeriod = periodRange[0].split(" ");
        endingPeriod = periodRange[1].split(" ");
        System.out.println("You are now updating item in Event task");
        super.updateTask(inputPart[0]);
        this.startDate = LocalDate.parse(startingPeriod[0]);
        this.startTime = startingPeriod[1];
        this.endDate = LocalDate.parse(endingPeriod[0]);
        this.endTime = endingPeriod[1];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + startDate.getDayOfMonth() + " "
                + startDate.getMonth() + " " + startDate.getYear() + ", " + startTime + " to: "
                + endDate.getDayOfMonth() + " " + endDate.getMonth() + " " + endDate.getYear() + ", " + endTime + " )";
    }
}
