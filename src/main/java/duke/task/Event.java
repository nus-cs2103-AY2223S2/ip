package duke.task;

import java.time.LocalDateTime;

public class Event extends TimedTask {
    /**
     * Represents Event tasks. Event tasks have both start date time and end date time.
     */
    LocalDateTime start;
    String consoleStartString;
    String fileStartString;

    /**
     * Empty constructor for an instance of an Event.
     */
    public Event() {
        super();
    }

    /**
     * Constructor for an instance of an Event. Sets the status, description, start time and end time.
     *
     * @param status Describes the completion status of the event
     * @param des    Describes the task
     * @param start  Specifies the start time of the event
     * @param end    Specifies the end time of the event
     */
    public Event(boolean status, String des, String start, String end) {
        super();
        setMark(status);
        setDes(des);
        setStart(start);
        setEnd(end);
    }

    /**
     * Method used to load saved Event task in file into Duke program. Read file inputs and create appropriate definitions
     *
     * @param s Each line in the save file
     */
    @Override
    public void configure(String[] s) {
        setDes(s[0]);
        String[] d = s[1].split(" to ");
        String startTime = dateTimeFileInParse(d[0]).format(isoFormat);
        assert startTime instanceof String : "Line from save file has not been transformed";
        String endTime = dateTimeFileInParse(d[1]).format(isoFormat);
        assert endTime instanceof String : "Line from save file has not been transformed";
        setStart(startTime);
        setEnd(endTime);
    }

    /**
     * Method to set start date time of event.
     *
     * @param s String representation of start date time
     */
    public void setStart(String s) {
        LocalDateTime startTime = dateTimeConsoleInParse(s);
        assert startTime instanceof LocalDateTime : "Start time has not be set properly";
        this.start = startTime;
        this.consoleStartString = start.format(super.consoleFormat);
        this.fileStartString = start.format(super.fileFormat);
    }

    /**
     * Method to display start date time onto console
     *
     * @return String representation of display
     */
    public String toStringConsoleStart() {
        return this.consoleStartString;
    }

    /**
     * Method to display start date time onto file
     *
     * @return String representation of display
     */
    public String toStringFileStart() {
        return this.fileStartString;
    }

    /**
     * Method to display status of task onto console
     */
    @Override
    public String printStatus() {
        String s = (isMark) ? "X" : " ";
        return "[E][" + s + "] " + getDes() + " (from: " + toStringConsoleStart() + " to: " + toStringConsoleEnd() + ")\n";
    }

    /**
     * Method to display status of task onto file
     *
     * @return String representation of display
     */
    @Override
    public String toString() {
        String s = (isMark) ? "X" : " ";
        return this.taskNumber + " | E | " + s + " | " + getDes() + " | " + toStringFileStart() + " to " + toStringFileEnd();
    }
}
