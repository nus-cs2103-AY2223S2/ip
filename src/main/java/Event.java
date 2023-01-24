import com.sun.jdi.LocalVariable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Event extends Task{
    protected LocalDate start;
    protected LocalDate end;

    public Event(String taskName, LocalDate start, LocalDate end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    public static void processEvent(String command, TaskList lst) throws DukeException{
        String info = command.trim();
        if (info.isEmpty()) {
            throw new DukeException("event");
        }
        String[] details = info.split("/");
        if (details.length < 3) {
            throw new DukeException("timing");
        }
        try {
            String startString = details[1].split(" ", 2)[1];
            String endString = details[2].split(" ", 2)[1];
            LocalDate start = LocalDate.parse(startString);
            LocalDate end = LocalDate.parse(endString);
            Event e = new Event(details[0], start, end);
            lst.addTask(e);
            Duke.printLine();
            System.out.println("Got it! I've added: ");
            System.out.println(" " + e.toString());
            lst.printSize();
            Duke.printLine();
        } catch (DateTimeParseException e) {
            throw new DukeException("date format");
        }
    }

    @Override
    public String toString() {
        String s;
        String start = this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String end = this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        if (this.completed) {
            s = "[E]" + super.toString() +
                    " (from: " + start + " to: " + end + ")";
        } else {
            s = "[E]"  + super.toString() +
                    " (from: " + start + " to: " + end + ")";
        }
        return s;
    }

}
