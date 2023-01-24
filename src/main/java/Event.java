import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    Event(String description, String from, String to) throws InvalidDateTimeException {
        super(description);
        this.symbol = "E";

        for (DateTimeFormatter f : SUPPORTED_DATE_TIME_INPUT) {
            try {
                this.from = LocalDateTime.parse(from, f);
                break;
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        for (DateTimeFormatter f : SUPPORTED_DATE_TIME_INPUT) {
            try {
                this.to = LocalDateTime.parse(to, f);
                break;
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        if (Objects.isNull(this.from) || Objects.isNull(this.to)) {
            throw new InvalidDateTimeException("Try reformatting your date/time to the supported formats:\n" +
                    "yyyy-MM-dd HHmm or yyyy/MM/dd HHmm");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from.format(OUTPUT_FORMAT) + ", to: " + this.to.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String getDataFormat() {
        return this.combineData(super.getDataFormat(), this.from, this.to);
    }
}
