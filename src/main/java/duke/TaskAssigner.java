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


    /**
     * Creates a Task Assigner for Duke.
     * Responsible for creating either Deadline task, ToDo task or
     * Event task depending on the user's input.
     */
    public TaskAssigner() {}

    /**
     * Creates either Deadline task, ToDo task or
     * Event task depending on the user's input.
     *
     * @param command the user's command.
     * @throws DukeException catch inconsistencies and error in the user's input.
     */
    public Task assignTask(String command) throws DukeException {
        String[] seq = command.split(" ");
        String keyWord = seq[0];

        if (!task_t.contains(keyWord)) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }

        if (seq.length == 1) {
            throw new DukeException("OOPS!!! I'm sorry, but the description of a task cannot" +
                    " be empty \n");
        }

        if (keyWord.equals("todo")) {
            return this.assignToDo(command);

        } else if (keyWord.equals("event")) {
            return this.assignEvent(command);
        } else {
            return this.assignDeadline(command);
        }
    }

    /**
     * Creates a ToDo task.
     *
     * @param command the user's command.
     * @return a ToDo task.
     */
    public Task assignToDo(String command) {
        return new ToDos(command.substring(5));
    }

    /**
     * Creates a Deadline task
     *
     * @param command the user's command.
     * @return a Deadline task.
     * @throws DukeException if Date and Time formatting is incorrect
     */
    public Task assignDeadline(String command) throws DukeException {
        String[] timestampsAsString = command.split("/by ");
        if (timestampsAsString.length != 2) {
            throw new DukeException("Improper Deadline Format! deadline {desc} /by yyyy-mm-dd hhmm\n");
        }

        try {
            int dateIndex = command.indexOf("/by ") + 4;
            String deadline = command.substring(dateIndex);
            System.out.println(deadline);
            String updatedDeadline = TimeChecker.updateTime(deadline);
            String d_desc = command.substring(9, dateIndex - 4);
            return new Deadline(d_desc, updatedDeadline);
        } catch (DateTimeParseException e) {
            throw new DukeException("Improper Deadline Format! deadline {desc} /by yyyy-mm-dd hhmm\n");
        }
    }

    /**
     * Creates a Event task
     *
     * @param command the user's command.
     * @return a Event task.
     * @throws DukeException if Date and Time formatting is incorrect
     */
    public Task assignEvent(String command) throws DukeException {
        String[] timestampsAsString = command.split("/from | /to ");
        if (timestampsAsString.length != 3) {
            throw new DukeException("Improper Event Format! Follow:\n" +
                    "event {desc} /from yyyy-mm-dd hhmm /to yyyy-mm-dd hhmm\n");
        }
        try {
            int startIndex = command.indexOf("/from") + 6;
            int endIndex = command.indexOf("/to") + 4;
            String startDate = TimeChecker.updateTime(command.substring(startIndex, endIndex - 5));
            String endDate = TimeChecker.updateTime(command.substring(endIndex));
            String e_desc = command.substring(6, startIndex - 6);
            return new Events(e_desc, startDate, endDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("Improper Event Format! Follow:\n" +
                    "event {desc} /from yyyy-mm-dd hhmm /to yyyy-mm-dd hhmm\n");

        }
    }
}
