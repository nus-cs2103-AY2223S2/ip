package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class Event extends Task {

    protected Optional<LocalDate> to;
    protected Optional<LocalDate> from;
    protected String strFrom;
    protected String strTo;
    protected String tag;
    public Event(String description, String from, String to) {
        super(description);
        this.tag = "[E]";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            this.from = Optional.of(LocalDate.parse(from, formatter));
            this.to = Optional.of(LocalDate.parse(to, formatter));
        } catch (Exception e) {
            this.strTo = to;
            this.strFrom = from;
        }
    }


    public Event(String description, String from, String to, boolean isMark) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            this.from = Optional.of(LocalDate.parse(from, formatter));
            this.to = Optional.of(LocalDate.parse(to, formatter));
        } catch (Exception e) {
            this.strTo = to;
            this.strFrom = from;
        }
        this.tag = "[E]";
        if (isMark) {
            super.markTask(isMark);
        }
    }

    public String getDate() {
        if (strTo == null) {
            DateTimeFormatter date = DateTimeFormatter.ofPattern("MMM dd yyyy");
            String formattedFrom = this.from.get().format(date);
            String formattedTo = this.to.get().format(date);
            return formattedFrom + "|" + formattedTo;
        }
        return strFrom + "|" + strTo;
    }

    public String getTag() {
        return this.tag;
    }

    @Override
    public String toString() {
        String line = this.getDate();
        String[] parmArr = line.split("\\|");
        List<String> parm = Arrays.asList(parmArr);
        return this.getTag() + super.toString() + " (from:" + parm.get(0) + " to:" + parm.get(1) + ")";
   }
}
