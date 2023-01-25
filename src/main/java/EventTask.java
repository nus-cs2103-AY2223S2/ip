public class EventTask extends Task {
    private final String period;
    static final String INDICATOR = "[Event]";
    static final String INPUT_PREFIX = "event ";
    static final String FORMAT_EXCEPTION_MESSAGE = "Invalid format for creating Event Task";
    static final String PERIOD_BEGIN_PREFIX = "/from ";
    static final String PERIOD_BEGIN_PREFIX_REPLACEMENT = "FROM: ";
    static final String PERIOD_END_PREFIX = "/to ";
    static final String PERIOD_END_PREFIX_REPLACEMENT = ",TO: ";
    EventTask(String name, String period) throws DukeException {
        super(name);
        this.period = period.replace(PERIOD_BEGIN_PREFIX, PERIOD_BEGIN_PREFIX_REPLACEMENT)
                .replace(PERIOD_END_PREFIX, PERIOD_END_PREFIX_REPLACEMENT);
    }

    private static String formattedPeriod (String period) {
        return String.format(Task.EXTRAS_FORMAT_TEMPLATE, period);
    }

    static EventTask createEvent(String text) throws DukeException {
        int firstSlashIndex = text.indexOf(PERIOD_BEGIN_PREFIX);
        int secondSlashIndex = text.indexOf(PERIOD_END_PREFIX);
        if (firstSlashIndex == -1 || secondSlashIndex == -1) {
            throw new DukeException(FORMAT_EXCEPTION_MESSAGE);
        }
        try {
            String taskName = text.substring(INPUT_PREFIX.length(), firstSlashIndex - 1);
            String period = text.substring(firstSlashIndex);
            return new EventTask(taskName, period);
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException(FORMAT_EXCEPTION_MESSAGE);
        }
    }


    @Override
    public String toString() {
        return INDICATOR + super.toString() + formattedPeriod(period);
    }
}
