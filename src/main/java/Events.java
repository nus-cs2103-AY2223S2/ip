import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
public class Events extends  Task{
    protected LocalDateTime from;
    protected LocalTime to;
    public Events(String desciption, LocalDateTime start, LocalTime end){
        super(desciption);
        this.from = start;
        this.to = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from:%s to:%s)", super.toString(), this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm a")), this.to.format(DateTimeFormatter.ofPattern("hhmm a")));
    }
    @Override
    public String changeFormat() {

        return String.format("E %s " + "/ " + this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm" ))  + " / " + this.to.format(DateTimeFormatter.ofPattern("HHmm" )), super.changeFormat());

    }
}
