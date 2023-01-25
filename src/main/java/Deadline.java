import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
    public String toFile() {
        int completed = this.completed ? 1 : 0;
        String deadline = this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("D | %d | %s | %s\n", completed, this.taskName, deadline);
    }

    public static Deadline toDeadlineFromFileStr(String taskNameData, String doneData, String deadlineData)
            throws DukeException {
        Deadline d = null;
        doneData = doneData.trim();
        deadlineData = deadlineData.trim();
        taskNameData = taskNameData.trim();
        if (taskNameData.isEmpty()) {
            throw new DukeException("todo");
        }
        if (doneData.isEmpty()) {
            throw new DukeException("missing details");
        }
        if (deadlineData.isEmpty()) {
            throw new DukeException("timing");
        }
        try {
            LocalDate deadline = LocalDate.parse(deadlineData);
            d = new Deadline(taskNameData, deadline);
            boolean completed = Integer.parseInt(doneData) == 1;
            d.setCompleted(completed);
        } catch (DateTimeParseException e) {
            throw new DukeException("date format");
        }
        return d;
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
