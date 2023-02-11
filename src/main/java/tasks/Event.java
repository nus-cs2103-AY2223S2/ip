package tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import exceptions.IncompleteCommandException;
import exceptions.UnknownCommandException;
import formatters.DateTimeUtils;
import formatters.StringUtils;

/**
 * Represents a basic task that lasts over a period of time.
 */
public class Event extends Task {

    private String startTimeString;
    private String endTimeString;
    private String startDateString;
    private String endDateString;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructor for the Event class.
     * @param description what the event is about
     * @param startDate start date
     * @param endDate end date
     * @param startTime start time
     * @param endTime end time
     */
    public Event(String description, String startDate, String endDate, String startTime, String endTime) {
        super(description);
        this.startDateString = startDate;
        this.endDateString = endDate;
        this.startTimeString = startTime;
        this.endTimeString = endTime;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
        this.startTime = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("Hmm"));
        this.endTime = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("Hmm"));
    }

    /**
     * Factory method to create an Event task based on commands.
     * @param input command input
     * @return an Event task object based on command input
     */
    public static Event create(String input) {
        String[] splitInput = input.split(" ");
        splitInput = StringUtils.removeWhiteSpace(splitInput);
        int fromIndex = StringUtils.searchString(splitInput, "/from");
        int toIndex = StringUtils.searchString(splitInput, "/to");
        if (fromIndex == -1 || toIndex == -1 || fromIndex + 1 == toIndex || fromIndex == 1) {
            throw new IncompleteCommandException("Incomplete arguments for command event, I have found", null);
        } else if (fromIndex > toIndex) {
            throw new UnknownCommandException("This command I do not know, only :\n "
                    + "event <description> /from <start> /to <end>", null);
        }
        String eventDescription = StringUtils.joinString(splitInput, 1, fromIndex - 1);
        String startDateExtract = splitInput[fromIndex + 1];
        String startTimeExtract = splitInput[fromIndex + 2];
        String endDateExtract = splitInput[toIndex + 1];
        String endTimeExtract = splitInput[toIndex + 2];
        if (!DateTimeUtils.isCorrectDateFormat(startDateExtract)
                || !DateTimeUtils.isCorrectDateFormat(endDateExtract)) {
            throw new UnknownCommandException("Not a valid date format, it is!", null);
        } else if (!DateTimeUtils.isCorrectTimeFormat(startTimeExtract)
                || !DateTimeUtils.isCorrectTimeFormat(endTimeExtract)) {
            throw new UnknownCommandException("Not a valid time format, it is!", null);
        }
        return new Event(eventDescription, startDateExtract, endDateExtract,
                startTimeExtract, endTimeExtract);
    }

    /**
     * Factory method which create an Event from data in file.
     * @param description what the event is about
     * @param startDate start date
     * @param endDate end date
     * @param startTime start time
     * @param endTime end time
     * @param marked status of the task
     * @return Event object based on data from file.
     */
    public static Event create(String description, String startDate, String endDate,
                               String startTime, String endTime, String marked) {
        if (!DateTimeUtils.isCorrectDateFormat(startDate)
                || !DateTimeUtils.isCorrectDateFormat(endDate)) {
            throw new UnknownCommandException("Not a valid date format, it is!", null);
        } else if (!DateTimeUtils.isCorrectTimeFormat(startTime)
                || !DateTimeUtils.isCorrectTimeFormat(endTime)) {
            throw new UnknownCommandException("Not a valid time format, it is!", null);
        }
        Event task = new Event(description, startDate, endDate, startTime, endTime);
        if (marked.equals("1")) {
            task.markSilent();
        }
        return task;
    }

    /**
     * Checks the status of the task and logs to stdout.
     */
    @Override
    public void mark() {
        super.mark();
        System.out.printf(" [%s][%s] %s (from: %s %s to: %s %s)%n",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description,
                this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.startTime.format(DateTimeFormatter.ofPattern("h:mm a")),
                this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.endTime.format(DateTimeFormatter.ofPattern("h:mm a")));
    }

    /**
     * Unchecks the status of the task and logs to stdout.
     */
    @Override
    public void unmark() {
        super.unmark();
        System.out.printf(" [%s][%s] %s(from: %s %s to: %s %s)%n",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description,
                this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.startTime.format(DateTimeFormatter.ofPattern("h:mm a")),
                this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.endTime.format(DateTimeFormatter.ofPattern("h:mm a")));
    }

    /**
     * Method for child classes to return their type.
     * @return string type of task
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * String representation of the Event Task.
     * @return string representation of the Event Task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s\n (from: %s %s\n        to: %s %s)",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description,
                this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.startTime.format(DateTimeFormatter.ofPattern("h:mm a")),
                this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.endTime.format(DateTimeFormatter.ofPattern("h:mm a")));
    }


    /**
     * Data representation of the Event to be written to file.
     * @return data representation of the event to be written in file
     */
    @Override
    public String writeTask() {
        return String.format("%s %d %s from %s %s to %s %s",
                this.getTaskType(),
                super.isCompleted() ? 1 : 0,
                this.description,
                this.startDateString,
                this.startTimeString,
                this.endDateString,
                this.endTimeString);
    }
}
