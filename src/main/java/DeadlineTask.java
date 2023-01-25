
public class DeadlineTask extends Task {
    private final String deadline;
    static final String INDICATOR = "[Deadline]";
    static final String INPUT_PREFIX = "deadline ";
    static final String FORMAT_EXCEPTION_MESSAGE = "Invalid format for creating Deadline Task";
    static final String DEADLINE_PREFIX = "/by ";
    static final String DEADLINE_PREFIX_REPLACEMENT = "BY: ";

    DeadlineTask(String name, String deadline) throws DukeException {
        super(name);
        this.deadline = deadline.replace(DEADLINE_PREFIX, DEADLINE_PREFIX_REPLACEMENT);
    }


    static DeadlineTask createDeadline(String text) throws DukeException {
        int firstSlashIndex = text.indexOf(DEADLINE_PREFIX);
        if (firstSlashIndex == -1) {
            throw new DukeException(FORMAT_EXCEPTION_MESSAGE);
        }
        try {
            String taskName = text.substring(INPUT_PREFIX.length(), firstSlashIndex - 1);
            String deadline = text.substring(firstSlashIndex);
            return new DeadlineTask(taskName, deadline);
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException(FORMAT_EXCEPTION_MESSAGE);
        }
    }


    private static String formattedDeadline (String deadline) {
        return String.format(Task.EXTRAS_FORMAT_TEMPLATE, deadline);
    }
    @Override
    public String toString() {
        return INDICATOR + super.toString() + formattedDeadline(deadline);
    }
}
