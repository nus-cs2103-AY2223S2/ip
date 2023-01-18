import java.util.Scanner;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public static Deadline parseDeadlineCommand(Scanner stringStream) throws DukeException {
        String taskDesc = "";
        String by = "";
        boolean foundBy = false;
        while (stringStream.hasNext()) {
            String temp = stringStream.next();
            if (temp.equalsIgnoreCase("/by")) {
                foundBy = true;
                continue;
            }

            if (foundBy) {
                by += temp + " ";
            } else {
                taskDesc += temp + " ";
            }
        }

        if (taskDesc.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        if (!foundBy || by.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Deadline tasks require a /by.");
        }

        Deadline newTask = new Deadline(taskDesc.trim(), by.trim());
        return newTask;
    }
}
