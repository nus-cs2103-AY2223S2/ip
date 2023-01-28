package DukeBot;

<<<<<<< HEAD
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Date;

public class Deadline extends Task {

    private static final String typeToString = "[D]";
    private final LocalDateTime deadline;
=======
import java.util.ArrayList;
import java.util.Objects;

public class Deadline extends Task {

    private static final String typeToString = "D";
    private final String deadline;
>>>>>>> branch-Level-7

    public Deadline(String task, String deadline) throws DateTimeParseException {
        super(task);
        System.out.println(deadline);
        this.type = Types.DEADLINE;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.deadline = LocalDateTime.parse(deadline, formatter);
        System.out.println(this.deadline);
    }

    public Deadline(String[] data) {
        super(data[2]);
        this.completed = Objects.equals(data[1], "X");
        this.deadline = data[2];
    }

    @Override
    public String status() {
        String status = this.completed ? "[X]" : "[ ]";
<<<<<<< HEAD
        return typeToString + status + details + " (by: " +
                this.deadline.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + " [" +
                this.deadline.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) + "]" + ")";
=======
        return "[" + typeToString + "]" + status + this.details + " (by:" + this.deadline + ")";
    }

    @Override
    public ArrayList<String> data() {
        ArrayList<String> data = new ArrayList<>();
        data.add(typeToString);
        data.add(this.completed ? "X" : " ");
        data.add(this.details);
        data.add(this.deadline);
        return data;
>>>>>>> branch-Level-7
    }
}
