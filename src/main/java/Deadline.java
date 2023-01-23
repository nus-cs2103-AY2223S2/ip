import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    LocalDateTime deadline;

    public Deadline(String data, String deadline) throws KiraException {
        super(data);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.deadline = LocalDateTime.parse(deadline, formatter);
        } catch (DateTimeParseException e) {
            throw new KiraException("Please input your date by this format: yyyy-MM-dd HHmm\n");
        }
    }

    /**
     * Checks if today is the deadline.
     * 
     * @return boolean to indicate
     */
    public boolean matchToday() {
        return LocalDateTime
                .now()
                .toLocalDate()
                .equals(deadline.toLocalDate());
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        StringBuilder ret = new StringBuilder("[D]");
        ret.append(super.toString())
                .append("(by: " + deadline.format(formatter) + ")");
        return ret.toString();
    }
}
