import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate date1;
    private LocalDate date2;

    public Event(String date1, String date2, String name){
        super(name);
        this.date1 = LocalDate.parse(date1);
        this.date2 = LocalDate.parse(date2);
    }

    @Override
    public String toString(){
        String d1 = date1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String d2 = date2.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String s = "[ E ]" + super.toString() + String.format("(%s %s)", d1, d2);
        return s;
    }
}
