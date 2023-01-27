package duke;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructs an event task with the given description, completion status, start time and end time.
     * The start and end times given has to be in either formats below.
     * dd/MM/yyyy HHmm
     * HHmm
     * dd/MM/yyyy 
     * @param description The description of Event task.
     * @param isDone The done status of the Event task.
     * @param startTime The start time of the Event task as a String.
     * @param endTime The end time of the Event task as a String.
     */
    public Event(String description, boolean isDone, String startTime, String endTime) {
        super(description, isDone);
        this.startTime = DateTimeParser.parseInput(startTime);
        this.endTime = DateTimeParser.parseInput(endTime);
    }

    /**
     * Constructs an event task with the given description, start time and end time.
     * The start and end times given has to be in either formats below.
     * The isDone status defaults to false.
     * dd/MM/yyyy HHmm
     * HHmm
     * dd/MM/yyyy 
     * @param description The description of Event task.
     * @param startTime The start time of the Event task as a String.
     * @param endTime The end time of the Event task as a String.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = DateTimeParser.parseInput(startTime);
        this.endTime = DateTimeParser.parseInput(endTime);
    }

    /**
     * Formats the Event task to be printed by Duke.
     * @return The formatted Event task as a String.
     */
    @Override
    public String printTask() {
        return String.format("[E][%s] %s (%s - %s)",
                (super.isDone() ? "X" : " "),
                super.getDescription(),
                DateTimeParser.formatOutput(this.startTime),
                DateTimeParser.formatOutput(this.endTime));
    }
    
    /**
     * Formats the Event task to be saved in a format recognisable by Storage.
     * @return The formatted Event task as a String.
     */
    @Override
    public String formatTask() {
        return String.format("event~-~-~%s~-~-~%s~-~-~%s~-~-~%s",
                this.getDescription(),
                DateTimeParser.formatSave(this.startTime),
                DateTimeParser.formatSave(this.endTime),
                this.isDone() ? "X" : "O");
    }
}
