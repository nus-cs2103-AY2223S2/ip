package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public abstract class TimedTask extends Task{
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime end;
    String endString;

    public TimedTask(boolean status, String des) {
        super(status, des);
    }

    public TimedTask() {
        super();
    }

    public LocalDateTime dateTimeParse(String s) {
        return LocalDateTime.parse(s,formatter);
    }
    @Override
    public void setDes(String[] des) {
        super.des = des[0];
        setEnd(des[1]);
    }
    @Override
    public void configure(String[] des) {
        this.setDes(des);
    }

    public void setEnd(String s) {
        this.end = dateTimeParse(s);
        this.endString = end.format(formatter);
    }
    public String toStringEnd() {
       return this.endString;
    }
}
