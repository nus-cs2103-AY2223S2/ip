import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {
    LocalDateTime fromDate;
    LocalDateTime toDate;

    Event(String content, String fromToDate) throws InvalidEventException {
        super(content);
        String[] fromToDateSplit = fromToDate.split(" /to ");

        if (fromToDateSplit.length != 2) {
            throw new InvalidEventException("The from or to date of an event task cannot be empty\n");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm", Locale.ENGLISH);
        this.fromDate = LocalDateTime.parse(fromToDateSplit[0], formatter);
        this.toDate = LocalDateTime.parse(fromToDateSplit[1], formatter);
    }

    @Override
    public String toString() {
        String printFromDateTime = this.fromDate.format(DateTimeFormatter.ofPattern("HHmm MMM d yyyy"));
        String printToDateTime = this.toDate.format(DateTimeFormatter.ofPattern("HHmm MMM d yyyy"));
        return String.format("[E]%s (from: %s to: %s)", super.toString(), printFromDateTime, printToDateTime);
    }
}
