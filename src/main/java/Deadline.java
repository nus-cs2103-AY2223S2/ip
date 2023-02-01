import java.io.PrintStream;
import java.time.LocalDateTime;
import static utils.FormatHelper.INPUTFORMAT;
import static utils.FormatHelper.PRINTFORMAT;


public class Deadline extends Task {
    protected LocalDateTime by;

    Deadline(String description, LocalDateTime by) throws IllegalArgumentException {
        super(description);
        this.by = by;
    }

    @Override
    public String toSaveFormat() {
        return "D||" + super.toSaveFormat() + "||" + by.format(INPUTFORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(PRINTFORMAT) + ")";
    }

    public static Deadline fromSaveFormat(String savedData) {
        String[] inputs = savedData.split("\\|\\|");
        Deadline generatedDeadline = new Deadline(inputs[2], LocalDateTime.parse(inputs[3], INPUTFORMAT));
        if (inputs[1].equals("1")) {
            generatedDeadline.setCompleted(true);
        }
        return generatedDeadline;
    }
}
