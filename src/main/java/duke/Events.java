package duke;

import java.time.LocalDateTime;

public class Events extends TimedTask {
    /**
     * Represent Event tasks. Event tasks have both start date time and end date time.
     */
    LocalDateTime start;
    String consoleStartString;
    String fileStartString;

    /**
     * Empty constructor for an instance of an Event.
     */
    public Events() {
        super();
    }

    /**
     * Constructor for an instance of an Event. Sets the status, description, start time and end time.
     */
    public Events(boolean status, String des, String start, String end) {
        super();
        setStatus(status);
        setDes(des);
        setStart(start);
        setEnd(end);
    }

    /**
     * Method used to load saved Event task in file into Duke program. Read file inputs and create appropriate definitions
     *
     * @param des Description of task. Includes start date time and end date time.
     */
    @Override
    public void configure(String[] s) {
        setDes(s[0]);
        String[] d = s[1].split(" to ");
        setStart(dateTimeFileInParse(d[0]).format(isoFormat));
        setEnd(dateTimeFileInParse(d[1]).format(isoFormat));
    }

    /**
     * Method to set start date time of event.
     *
     * @param s String representation of start date time
     */
    public void setStart(String s) {
        this.start = dateTimeConsoleInParse(s);
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
        String s = (status) ? "X" : " ";
        return "[E][" + s + "] " + getDes() + " (from: " + toStringConsoleStart() + " to: " + toStringConsoleEnd() + ")\n";
    }

    /**
     * Method to display status of task onto file
     *
     * @return String representation of display
     */
    @Override
    public String toString() {
        String s = (status) ? "X" : " ";
        return "E | " + s + " | " + getDes() + " | " + toStringFileStart() + " to " + toStringFileEnd();
    }
}
