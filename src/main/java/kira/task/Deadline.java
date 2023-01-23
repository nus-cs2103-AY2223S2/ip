package kira.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import kira.exception.KiraException;

public class Deadline extends Task {
    LocalDateTime deadline;

    public Deadline(String data, String deadline) throws KiraException {
        super(data);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.deadline = LocalDateTime.parse(deadline, formatter);
        } catch (DateTimeParseException e) {
            throw new KiraException(
                    "Please input your date by this format:"
                    + " yyyy-MM-dd HHmm");
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
    public String saveFormat() {
        StringBuilder temp = new StringBuilder("DEADLINE\",\"" + super.saveFormat());
        temp.append("\",\"" + deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
        return temp.toString();
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        StringBuilder ret = new StringBuilder("[D]");
        ret.append(super.toString())
                .append(" (by: " + deadline.format(formatter) + ")");
        return ret.toString();
    }
}
