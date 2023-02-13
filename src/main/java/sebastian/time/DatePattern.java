package sebastian.time;

/**
 * Enum class specifying different date time patterns for uses under different conditions
 */
public enum DatePattern {
    PRESENTATION_FORMAT("dd MMM yyyy HH:mm"),
    USER_INPUT_FORMAT("yyyy-MM-dd HHmm"),
    TASK_ON_DATE_FORMAT("yyyy-MM-dd");

    private final String format;

    DatePattern(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return this.format;
    }
}
