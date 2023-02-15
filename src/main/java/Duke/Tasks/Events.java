package Duke.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
/**
 * Contains the Events class
 */
public class Events extends Task {
    protected LocalDateTime from;
    protected LocalTime to;

    /**
     * The contructor for Events Task
     *
     * @param description
     * @param start
     * @param end
     */
    public Events(String description, LocalDateTime start, LocalTime end){
        super(description);
        this.from = start;
        this.to = end;
    }

    /**
     * Obtain the string representation of the event task.
     *
     * @return string representation of the event task
     */
    @Override
    public String toString() {
        return String.format("[E]%s(from:%s to:%s)", super.toString(), this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm a")), this.to.format(DateTimeFormatter.ofPattern("hhmm a")));
    }

    /**
     * The changeFormat method for change the format when  saving/Writing  tasks in data.txt file
     * @return String
     */
    @Override
    public String changeFormat() {

        return String.format("E %s " + "/ " + this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm" ))  + " / " + this.to.format(DateTimeFormatter.ofPattern("HHmm" )), super.changeFormat());

    }
}