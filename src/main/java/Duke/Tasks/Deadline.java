
package Duke.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Contains the Deadline class
 */
public class Deadline  extends Task {
    protected LocalDateTime doneBy;

    /**
     * The contructor for Deadline Task
     *
     * @param description
     * @param doneBy
     */

    public Deadline(String description, LocalDateTime doneBy) {
        super(description);
        this.doneBy = doneBy;
    }

    /**
     * Obtain the string representation of the deadline task.
     *
     * @return string representation of the deadline task
     */

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.doneBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy hhmm a")));
    }

    /**
     * The changeFormat method for change the format when  saving/Writing  tasks in data.txt file
     * @return String
     */

    @Override
    public String changeFormat(){
        return String.format("D %s " + "/ " + this.doneBy.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")), super.changeFormat());

        }



}

