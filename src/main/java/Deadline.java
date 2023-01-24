import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task{
    protected LocalDate deadline;

    public Deadline(String taskName, LocalDate deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public static void processDeadline(String command, TaskList lst) throws DukeException{
        String info = command.trim();
        if (info.isEmpty()) {
            throw new DukeException("deadline");
        }
        String[] details = info.split("/");
        if (details.length < 2) {
            throw new DukeException("timing");
        }
        try {
            String deadlineString = details[1].split(" ", 2)[1];
            LocalDate deadline = LocalDate.parse(deadlineString);
            Deadline d = new Deadline(details[0], deadline);
            lst.addTask(d);
            Duke.printLine();
            System.out.println("Got it! I've added: ");
            System.out.println(" " + d.toString());
            lst.printSize();
            Duke.printLine();
        } catch (DateTimeParseException e) {
            throw new DukeException("date format");
        }
    }

    @Override
    public String toString() {
        String s;
        String deadline = this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        if (this.completed) {
            s = "[D]" + super.toString() + "(by: " + deadline + ")";
        } else {
            s = "[D]" + super.toString()  + " (by: " + deadline + ")";
        }
        return s;
    }
}
