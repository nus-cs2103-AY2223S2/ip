import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
    protected LocalDateTime by;

    Deadline(String description, LocalDateTime by) throws IllegalArgumentException {
        super(description);
        this.by = by;
    }

    @Override
    public String toSaveFormat() {
        return "D||" + super.toSaveFormat() + "||" + by.format(formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    public static Deadline fromSaveFormat(String savedData) {
        String[] inputs = savedData.split("\\|\\|");
        Deadline generatedDeadline = new Deadline(inputs[2], LocalDateTime.parse(inputs[3], formatter));
        if (inputs[1].equals("1")) {
            generatedDeadline.setCompleted(true);
        }
        return generatedDeadline;
    }
}
