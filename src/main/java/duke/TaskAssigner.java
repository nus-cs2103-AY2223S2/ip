package duke;

import duke.tasks.Deadline;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.ToDos;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;

public class TaskAssigner {

    public static ArrayList<String> task_t = new ArrayList<>(List.of("todo", "event", "deadline"));

    public TaskAssigner() {}

    public Task assignTask(String command) throws DukeException {
        String[] seq = command.split(" ");
        String ref = seq[0];

        if (!task_t.contains(ref)) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }

        if (seq.length == 1) {
            throw new DukeException("OOPS!!! I'm sorry, but the description of a task cannot" +
                    " be empty \n");
        }

        if (ref.equals("todo")) {
            return this.assignToDo(command);

        } else if (ref.equals("event")) {
            return this.assignEvent(command);
        } else {
            return this.assignDeadline(command);
        }
    }

    public Task assignToDo(String command) {
        return new ToDos(command.substring(5));
    }

    public Task assignDeadline(String command) throws DukeException {
        String[] splitTiming = command.split("/by ");
        if (splitTiming.length != 2) {
            throw new DukeException("Improper Deadline Format! deadline {desc} /by yyyy-mm-dd hhmm\n");
        }

        try {
            int d_index = command.indexOf("/by ") + 4;
            String deadline = command.substring(d_index);
            System.out.println(deadline);
            String updated_deadline = TimeChecker.updateTime(deadline);
            String d_desc = command.substring(9, d_index - 4);
            return new Deadline(d_desc, updated_deadline);
        } catch (DateTimeParseException e) {
            throw new DukeException("Improper Deadline Format! deadline {desc} /by yyyy-mm-dd hhmm\n");
        }
    }

    public Task assignEvent(String command) throws DukeException {
        String[] splitTimings = command.split("/from | /to ");
        if (splitTimings.length != 3) {
            throw new DukeException("Improper Event Format! Follow:\n" +
                    "event {desc} /from yyyy-mm-dd hhmm /to yyyy-mm-dd hhmm\n");
        }
        try {
            int s_index = command.indexOf("/from") + 6;
            int e_index = command.indexOf("/to") + 4;
            String start_date = TimeChecker.updateTime(command.substring(s_index, e_index - 5));
            String end_date = TimeChecker.updateTime(command.substring(e_index));
            String e_desc = command.substring(6, s_index - 6);
            return new Events(e_desc, start_date, end_date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Improper Event Format! Follow:\n" +
                    "event {desc} /from yyyy-mm-dd hhmm /to yyyy-mm-dd hhmm\n");

        }
    }
}
