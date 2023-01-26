package kira.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import kira.exception.KiraException;

public class Event extends Task {
    LocalDateTime startDate;
    LocalDateTime endDate;

    public Event(String data, String startDate, String endDate) throws KiraException {
        super(data);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.startDate = LocalDateTime.parse(startDate, formatter);
            this.endDate = LocalDateTime.parse(endDate, formatter);
            if (this.startDate.isAfter(this.endDate)) {
                throw new KiraException("Start date cannot be after the end date!");
            }
        } catch (DateTimeParseException e) {
            throw new KiraException(
                    "Please input your date following this format:"
                    + " yyyy-MM-dd HHmm");
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        StringBuilder saveString = new StringBuilder("EVENT\",\"" + super.saveFormat());
        saveString.append("\",\"" + startDate.format(formatter));
        saveString.append("\",\"" + endDate.format(formatter));
        return saveString.toString();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        StringBuilder sBuilder = new StringBuilder("[E]");
        sBuilder.append(super.toString())
                .append(" (from: " + startDate.format(formatter))
                .append(", to: " + endDate.format(formatter) + ")");
        return sBuilder.toString();
    }
}
