import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadlines extends Task {
    String endsBy;
    LocalDate dueDate;

    Deadlines(String taskName) throws DukeExceptions, DateTimeParseException{
        super(taskName.split("/by ")[0]);
        if (taskName.length() <= 0 || taskName.isBlank()) {
            throw new DukeExceptions("deadline");
        }
        this.endsBy = taskName.split("/by ")[1];

    }

    public void setEndBy(String setEnd) {
        this.endsBy = setEnd;
    }

    @Override
    public String toString() {
        String toReturn = "";
        if (this.done) {
            toReturn = "[D][X]" + this.name + "(by: " + endsBy + ")";
        } else {
            toReturn = "[D][ ]" + this.name + "(by: " + endsBy + ")";            
        }
        return toReturn;
    }
}
