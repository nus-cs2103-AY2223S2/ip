/**
 * Represents an event task.
 */
public class Events extends Task {
    private String begin;
    private String end;

    protected Events(String[] content) throws DukeException {
        super(content[0]);
        if (content[0].isEmpty() || content.length != 3) {
            throw new DukeException("OOPS!!! Command should be in the format 'event [M] /from [M] /to [M]'\n" +
                    "The description, [M] cannot be empty.");
        }
        String[] begins = content[1].split(" ", 2);
        String[] ends = content[2].split(" ", 2);
        if (!begins[0].equals("from") || begins.length == 1 || !ends[0].equals("to") || ends.length == 1) {
            throw new DukeException("OOPS!!! Command should be in the format 'event [M] /from [M] /to [M]'\n" +
                    "The description, [M] cannot be empty.");
        }
        this.begin = begins[1];
        this.end = ends[1];
    }

    @Override
    protected String getTypeIcon() {
        return "E";
    }

    /**
     * @return String representation of the event task
     */
    @Override
    public String toString() {
        return super.toString() + "(from: " + begin + "to: " + end + ")";
    }
}
