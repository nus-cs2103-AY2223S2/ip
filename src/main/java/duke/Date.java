package duke;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date implements Serializable {
    private LocalDate localDate;
    private LocalDateTime localDateTime;

    public Date(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Date(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public boolean isDate() {
        return this.localDate != null;
    }

    public boolean isDateTime() {
        return !isDate();
    }

    public String asFileDate() {
        return this.isDate()
                ? this.localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                : this.localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return this.isDate()
                ? this.localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                : this.localDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }
}
