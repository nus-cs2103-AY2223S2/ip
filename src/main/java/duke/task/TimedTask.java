package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class TimedTask extends Task {
    /**
     * Represent a Timed task. Includes Deadline and Event tasks.
     */
    DateTimeFormatter isoFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    DateTimeFormatter consoleFormat = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
    DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime end;
    String consoleEndString;
    String fileEndString;

    /**
     * Empty constructor for TimedTask. Note that TimedTask is abstract
     */
    public TimedTask() {
        super();
    }

    /**
     * Method to convert user input into ISO date time input format
     *
     * @param s String representation of date time
     * @return LocalDateTime object to be used
     */
    public LocalDateTime dateTimeConsoleInParse(String s) {
        return LocalDateTime.parse(s, isoFormat);
    }

    /**
     * Method to convert user input into File date time input format
     *
     * @param s String representation of date time
     * @return LocalDateTime object to be used
     */
    public LocalDateTime dateTimeFileInParse(String s) {
        return LocalDateTime.parse(s, fileFormat);
    }

    /**
     * Method to assist with loading from file.
     *
     * @param s String representation loaded from file
     */
    @Override
    public void configure(String[] s) {
        setDes(s[0]);
        String endTime = dateTimeFileInParse(s[1]).format(isoFormat);
        assert endTime instanceof String : "Line from save file has not been transformed";
        setEndFromFile(endTime);
    }

    /**
     * Method to set description of timed tasks. Includes setting end date
     *
     * @param des Description of task
     */
    @Override
    public void setDes(String des) {
        super.des = des;
    }

    /**
     * Method to set end time for timed tasks
     *
     * @param s String representation of end date time
     */
    public void setEndFromFile(String s) {
        LocalDateTime endTime = dateTimeConsoleInParse(s);
        assert endTime instanceof LocalDateTime : "End time has not be set properly";
        this.end = endTime;
        this.consoleEndString = end.format(consoleFormat);
        this.fileEndString = end.format(fileFormat);
    }

    public void setEndFromApp(LocalDateTime endTime) {
        assert endTime instanceof LocalDateTime : "End time has not be set properly";
        this.end = endTime;
        this.consoleEndString = end.format(consoleFormat);
        this.fileEndString = end.format(fileFormat);
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    /**
     * Method to display end date time onto console
     *
     * @return String representation of end date time
     */
    public String toStringConsoleEnd() {
        return this.consoleEndString;
    }

    /**
     * Method to display end date time onto file
     *
     * @return String representation of end date time
     */
    public String toStringFileEnd() {
        return this.fileEndString;
    }


}
