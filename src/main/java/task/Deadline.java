import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import utils.DateUtil;

/**
 * Deadline
 */
class Deadline extends Task {

    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }
    
    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " 
        + DateUtil.dateToString(by)
        + ")";
    }

}
