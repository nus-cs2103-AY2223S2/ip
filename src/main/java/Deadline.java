import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    String taskDescription;
    LocalDate deadLine;

    public Deadline(String taskString, LocalDate deadline) {
        super (taskString.substring(9, taskString.indexOf("/") - 1));

        taskDescription = taskString.substring(9, taskString.indexOf("/") - 1);
        deadLine = deadline;
    }
    @Override
    public String getTask() {
        return this.taskDescription;
    }

    public String getDeadline() {
        return this.deadLine.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " ( by: " + this.getDeadline() + " )" ;
    }
}
