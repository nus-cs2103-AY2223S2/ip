import java.util.ArrayList;
import java.util.Arrays;

public class Event extends Task{
    protected String start;
    protected String end;

    public Event(String taskName, String start, String end) {
        super(taskName);
        this.start = start;
        this.end = end;
        this.taskType = "E";
    }

    public static void createEvent(String command, TaskTracker t) throws DukeInputError{
        ArrayList<String> input = new ArrayList(Arrays.asList(command.split(" ")));
        if (input.size() <= 1) throw new DukeInputError("event");
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        String taskName = "";
        String start = "";
        String end = "";
        for (int i = 1; i < input.size(); i++) {
            if (i < fromIndex) {
                taskName += input.get(i);
                if (i < fromIndex - 1) {
                    taskName += " ";
                }
            } else if ((i > fromIndex) && (i < toIndex)) {
                start += input.get(i);
                if (i < toIndex) {
                    start += " ";
                }
            } else if (i > toIndex) {
                end += input.get(i);
                if (i < input.size() - 1) {
                    end += " ";
                }
            }
        }
        t.addTask(new Event(taskName, start, end));
    }

    @Override
    public String toString() {
        return String.format("  %s%s %s (from: %s to: %s)", displayType(), displayMark(),
                this.taskName, this.start, this.end);
    }
}
