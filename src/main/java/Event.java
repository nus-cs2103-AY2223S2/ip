import java.util.Scanner;

public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    public static Event parseEventCommand(Scanner stringStream) throws DukeException {
        String taskDesc = "";
        String from = "";
        String to = "";

        boolean foundFrom = false;
        boolean foundTo = false;

        while (stringStream.hasNext()) {
            String temp = stringStream.next();

            if (temp.equalsIgnoreCase("/from")) {
                foundFrom = true;
                continue;
            } else if (temp.equalsIgnoreCase("/to")) {
                foundTo = true;
                continue;
            }

            if (foundTo) {
                to += temp + " ";
            } else if (foundFrom) {
                from += temp + " ";
            } else {
                taskDesc += temp + " ";
            }
        }

        if (taskDesc.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }

        if (!foundFrom || !foundTo || from.isEmpty() || to.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Event tasks require a /from and /to.");
        }

        Event newTask = new Event(taskDesc.trim(), from.trim(), to.trim());
        return newTask;
    }
}
