import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDate date;

    public Deadline(String date, String name){
        super(name);
        this.date = LocalDate.parse(date);
    }

    @Override
    public String toString(){
        String dateFormatted = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String s = "[ D ]" + super.toString() + String.format("(%s)", dateFormatted);
        return s;
    }
}
