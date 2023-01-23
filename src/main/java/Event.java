import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    LocalDateTime startDate;
    LocalDateTime endDate;

    public Event(String data, String startDate, String endDate) throws KiraException {
        super(data);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.startDate = LocalDateTime.parse(startDate, formatter);
            this.endDate = LocalDateTime.parse(endDate, formatter);
        } catch (DateTimeParseException e) {
            throw new KiraException("Please input your date by this format: yyyy-MM-dd HHmm\n");
        }
    }

    /**
     * Checks if today is between the startDate and the endDate.
     * 
     * @return boolean to indicate
     */
    public boolean withinTimeframe() {
        LocalDateTime now = LocalDateTime.now();
        return now.isBefore(endDate) && now.isAfter(startDate);
    }

    @Override
    public String saveFormat() {
        StringBuilder temp = new StringBuilder("E\",\"" + super.saveFormat());
        temp.append("\",\"" + startDate);
        temp.append("\",\"" + endDate);
        return temp.toString();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        StringBuilder ret = new StringBuilder("[E]");
        ret.append(super.toString())
                .append("(from: " + startDate.format(formatter))
                .append(" to: " + endDate.format(formatter) + ")");
        return ret.toString();
    }
}
