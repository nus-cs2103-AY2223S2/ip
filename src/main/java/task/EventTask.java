package task;

import exception.MissingParameterException;

public class EventTask extends Task {
    private final String fromDateTime;
    private final String toDateTime;

    public EventTask(String description, String fromDateTime, String toDateTime) throws MissingParameterException {
        super(description);
        if (fromDateTime == null || toDateTime == null || fromDateTime.isBlank() || toDateTime.isBlank()) {
            throw new MissingParameterException(
                    "Missing fromDateTime or toDateTime",
                    "Start ('/from ...') and end ('/to ...') markers are needed."
            );
        }
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    @Override
    public String toString() {
        return String.format(
                "[E]%s (from: %s, to: %s)",
                super.toString(),
                this.fromDateTime,
                this.toDateTime
        );
    }
}
