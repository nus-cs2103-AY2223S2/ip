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
    public String toFile() {
        int completed = this.completed ? 1 : 0;
        return String.format("E | %d | %s | %s | %s\n", completed, this.taskName, this.start, this.end);
    }

    public static Event toEventFromFileStr(String taskNameData, String doneData,
                                                String startData, String endData) throws DukeException {
        doneData = doneData.trim();
        startData = startData.trim();
        endData = endData.trim();
        taskNameData = taskNameData.trim();
        if (taskNameData.isEmpty()) {
            throw new DukeException("todo");
        }
        if (doneData.isEmpty()) {
            throw new DukeException("missing details");
        }
        if (startData.isEmpty() || endData.isEmpty()) {
            throw new DukeException("timing");
        }
        Event e = new Event(taskNameData, startData, endData);
        boolean completed = Integer.parseInt(doneData) == 1;
        e.setCompleted(completed);
        return e;
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
