import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate date1;
    private LocalDate date2;

    public Event(String name, boolean done, String date1, String date2) {
        super(name, done);
        this.date1 = LocalDate.parse(date1);
        this.date2 = LocalDate.parse(date2);
    }


    public LocalDate getDate1() {
        return this.date1;
    }

    public LocalDate getDate2() {
        return this.date2;
    }

    @Override
    public String summary(){
        String s = "D___";
        String d = this.getDone()? "✓" : "X";
        return s + d + "___" + this.getTask() + "___" + this.getDate1() + "___" + this.getDate2();
    }

    @Override
    public String toString(){
        String d1 = date1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String d2 = date2.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String s = "[ E ]" + super.toString() + String.format("(%s %s)", d1, d2);
        return s;
    }
}
