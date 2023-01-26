import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String description;
    private LocalDateTime deadline;

    private Deadline(String description, LocalDateTime deadline) {
        super(TaskType.DEADLINE, description);
        this.description = description;
        this.deadline = deadline;
    }

    public static Deadline to(String str) {
        String target = " /by ";
        if (str.contains(target)) {
            LocalDateTime dateTime;
            String description, deadline, day, month;
            int index = str.indexOf(target);
            description = str.substring(0, index);
            deadline = str.substring(index + 5);
            if (!(deadline.length() > 12 && deadline.length() < 16)) {
                throw new RuntimeException("Unable to create deadline! Check your date and time format!");
            }
            int firstSlash = deadline.indexOf("/");
            int secondSlash = deadline.indexOf("/", firstSlash + 1);
            if (firstSlash == -1 || secondSlash == -1) {
                throw new RuntimeException("Unable to create Deadline! Check your format!\n");
            }
            day = firstSlash == 1 ? "d" : "dd";
            month = secondSlash - firstSlash == 2 ? "M" : "MM";
            DateTimeFormatter inFormatter = DateTimeFormatter.ofPattern(day + "/" + month + "/yyyy HHmm");
            try {
                dateTime = LocalDateTime.parse(deadline, inFormatter);
            } catch (DateTimeParseException e) {
                throw new RuntimeException("Invalid date and time! Please try again!\n");
            }
            if (!(description.equals("") && deadline.equals(""))) {
                return new Deadline(description, dateTime);
            }
            throw new RuntimeException("Unable to create Deadline! Description or deadline was not filled in!\n");
        }
        throw new RuntimeException("Unable to create Deadline! Check your format!\n");
    }

    @Override
    public String taskToSavedForm() {
        DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String marked = super.getStatusIcon() == "X" ? "1" : "0";
        return "deadline " + this.description + " /by " + this.deadline.format(outFormatter) + marked;
    }

    @Override
    public String toString() {
        DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        String str = super.toString();
        str += " " + "(by: " + this.deadline.format(outFormatter) + ")" + "\n";
        return str;
    }
}
