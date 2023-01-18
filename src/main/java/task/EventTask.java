package task;

import exception.MissingParameterException;

public class EventTask extends Task {
    private final String fromDateTime;
    private final String toDateTime;

    public EventTask(
            String description,
            String fromDateTime,
            String toDateTime
    ) throws MissingParameterException {
        this(description, fromDateTime, toDateTime, false);
    }

    public EventTask(
            String description,
            String fromDateTime,
            String toDateTime,
            boolean isDone
    ) throws MissingParameterException {
        super(description, isDone);
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
    public String serialize() {
        String[] data = {
                "E",
                String.valueOf(this.isDone()),
                this.getDescription(),
                this.fromDateTime,
                this.toDateTime
        };
        return String.join(" / ", data);
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
