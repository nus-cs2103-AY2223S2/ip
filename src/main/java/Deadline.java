import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    //protected String day;
    protected LocalDate date;
    protected LocalTime time;
    static protected String DESIRED_DATE_FORMAT = "MMM dd yyyy";

    public Deadline(String description,String date, String time) throws Exception {
        super(description,TypeOfTask.deadline);
        //this.day = day;
        this.date = covertToLocalDate(date);
        this.time = convertToLocalTime(time);
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
