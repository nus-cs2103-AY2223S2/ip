package duke.event;
import duke.data.TypeOfTask;
import duke.exception.DukeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate date;
    private LocalTime time;
    static private String DESIRED_DATE_FORMAT = "MMM dd yyyy";

    public Deadline(String description,String date, String time) throws DukeException {
        super(description,TypeOfTask.deadline);
        this.date = super.parser.covertToLocalDate(date);
        this.time = super.parser.convertToLocalTime(time);
    }

    public LocalDate getDate(){
        return this.date;
    }

    public LocalTime getTime(){
        return this.time;
    }

    @Override
    public String toString() {
        //return "[D]" + super.getDescription() + " (by: " + this.day + ")";
        String day = this.getDate().format(DateTimeFormatter.ofPattern(DESIRED_DATE_FORMAT));
        return String.format("[D][%s] %s (by: %s %s)",super.getStatusIcon(),super.getDescription(),day,this.getTime().toString());
    }
}
