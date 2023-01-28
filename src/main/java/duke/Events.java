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
    public Events(boolean status, String des) {
        super();
        setStatus(status);
        String[] d = new String[3];
        String[] s = des.split(" /from ");
        d[0] = s[0];
        String[] x = s[1].split(" /to ");
        d[1] = x[1];
        setDes(d);
        setStart(x[0]);
    }

    /**
     * Method used to load saved Event task in file into Duke program. Read file inputs and create appropriate definitions
     *
     * @param des Description of task. Includes start date time and end date time.
     */
    @Override
    public void configure(String[] des) {
        String[] d = new String[2];
        d[0] = des[0];
        String[] temp = des[1].split(" to ");
        d[1] = dateTimeFileInParse(temp[1]).format(isoFormat);
        setDes(d);
        setStart(dateTimeFileInParse(temp[0]).format(isoFormat));
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
    public void printStatus() {
        String s = (status) ? "X" : " ";
        System.out.println("[E][" + s + "] " + getDes() + " (from: " + toStringConsoleStart() + " to: " + toStringConsoleEnd() + ")");
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
