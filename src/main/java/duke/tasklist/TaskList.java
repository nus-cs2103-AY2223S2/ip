package duke.tasklist;

import java.util.ArrayList;

import duke.exceptions.DukeInvalidTaskNumberException;
import duke.exceptions.DukeMissingDeadlineException;
import duke.exceptions.DukeMissingDescriptionException;
import duke.exceptions.DukeMissingEventDateException;
import duke.exceptions.DukeTaskNumberOutOfRangeException;
import duke.exceptions.DukeUnknownCommandException;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.ToDos;

/**
 * Represents the list containing the Tasks objects.
 * @author pzhengze
 */
public class TaskList {
    /** Reference to the ArrayList object that contains the Task objects. */
    private final ArrayList<Task> tasks;

    /**
     * Constructor for empty TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for a TaskList object that contains existing Task objects.
     * @param tasks The existing list of Task objects.
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null;
        this.tasks = tasks;
    }

    /**
     * Adds a new Task into the list.
     * Task added depends on the type inputted and the input string.
     * <p>
     * If TaskType = Todos: A Todos Task is created.
     * A Todos Task requires a description in input String s
     * <p>
     * If TaskType = Deadlines: A Deadlines Task is created.
     * A Deadlines Task requires a description and a deadline(/by) in input String s
     * <p>
     * If TaskType = Events: A Events Task is created.
     * An Events Task requires a descriptions, a duration(/from and /to) in input String s
     * <p>
     * @param type The type of Task to be created
     * @param s The data required to create the new Task
     * @return Message to user informing about successfully creation and adding the new Task into the list.
     * @throws DukeMissingDescriptionException If s does not contain a description for Task.
     * @throws DukeMissingDeadlineException If s does not contain /by which signifies the due date.
     * @throws DukeMissingEventDateException If s does not contain /from or /to which signifies the duration.
     * @throws DukeUnknownCommandException If s does not fit any of the 3 tasks.
     */
    public String add(TaskType type, String s) throws DukeMissingDescriptionException,
            DukeMissingDeadlineException, DukeMissingEventDateException, DukeUnknownCommandException {
        String output = "Got it. I've added this task:\n";

        switch (type) { // Interprets input string differently depending on the input type.
        case ToDos:
            output += addTodo(s);
            break;
        case Deadlines: {
            output += addDeadline(s);
            break;
        }
        case Events: {
            output += addEvent(s);
            break;
        }
        default:
            throw new DukeUnknownCommandException();
        }
        return String.format("%s\nNow you have %d tasks in the list.", output, tasks.size());
    }

    /**
     * Marks the Task at the specified index as done.
     * @param s The index of the Task in 1-index form.
     * @return Message informing user of successful execution.
     * @throws DukeInvalidTaskNumberException when index is not an integer.
     * @throws DukeTaskNumberOutOfRangeException when index is out of range.
     */
    public String mark(String s) throws DukeInvalidTaskNumberException, DukeTaskNumberOutOfRangeException {
        int num = TaskList.stringToInt(s);

        if (num < -1 || num > tasks.size()) {
            throw new DukeTaskNumberOutOfRangeException();
        }
        return tasks.get(num - 1).mark();
    }

    /**
     * Marks the Task at the specified index as not done.
     * @param s The index of the Task in 1-index form.
     * @return Message informing user of successful execution.
     * @throws DukeInvalidTaskNumberException when index is not an integer.
     * @throws DukeTaskNumberOutOfRangeException when index is out of range.
     */
    public String unMark(String s) throws DukeInvalidTaskNumberException, DukeTaskNumberOutOfRangeException {
        int num = TaskList.stringToInt(s);

        if (num < -1 || num > tasks.size()) {
            throw new DukeTaskNumberOutOfRangeException();
        }
        return tasks.get(num - 1).unMark();
    }

    /**
     * Removes the Task at the specified index from the list.
     * @param s The index of the Task in 1-index form.
     * @return Message informing user of successful execution.
     * @throws DukeInvalidTaskNumberException when index is not an integer.
     * @throws DukeTaskNumberOutOfRangeException when index is out of range.
     */
    public String delete(String s) throws DukeInvalidTaskNumberException, DukeTaskNumberOutOfRangeException {
        int num = TaskList.stringToInt(s);

        String output = "Noted. I've removed this task:\n";
        if (num < -1 || num > tasks.size()) {
            throw new DukeTaskNumberOutOfRangeException();
        }
        Task removed = tasks.remove(num - 1);
        return String.format("%s   %s\n Now you have %d tasks in the list.", output, removed, tasks.size());
    }

    /**
     * Returns the list in String form for user to view.
     * @return The list in String form.
     */
    public String list() {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:\n");
        int index = 1;
        for (Task task : tasks) {
            output.append(String.format("%d.%s\n", index, task));
            index++;
        }

        return output.substring(0, output.length() - 1);
    }

    /**
     * Searches through list for Tasks whose description contains s.
     * @param s The String to be searched for.
     * @return Message to user informing about the tasks that match.
     */
    public String find(String s) {
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list.\n");
        if (s.isBlank()) {
            return output.substring(0, output.length() - 1);
        }
        int index = 1;
        for (Task task : tasks) {
            if (task.contain(s.toLowerCase())) {
                output.append(String.format("%d.%s\n", index, task));
            }
            index++;
        }

        return output.substring(0, output.length() - 1);
    }

    /**
     * Returns the list.
     * @return The Arraylist containing the Task objects.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Converts s into an integer.
     * @param s The String to be converted.
     * @return The resultant integer.
     * @throws DukeInvalidTaskNumberException if s is not an integer in String form.
     */
    private static int stringToInt(String s) throws DukeInvalidTaskNumberException {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new DukeInvalidTaskNumberException();
        }
    }

    /**
     * Creates a Todos Task and adds it into the TaskList.
     * @param s The data required to create an Event.
     * @return The Todos in String format.
     * @throws DukeMissingDescriptionException if the description of the Todos is missing.
     */
    private String addTodo(String s) throws DukeMissingDescriptionException {
        if (s.isBlank()) { // Checks if s contains a description.
            throw new DukeMissingDescriptionException();
        }

        // Creates a new Todos object and add it to the list.
        ToDos todo = new ToDos(s, false);
        tasks.add(todo);

        // Add object into the output String.
        return "  " + todo;
    }

    /**
     * Creates a Deadline Task and adds it into the TaskList.
     * @param s The data required to create a Deadline.
     * @return The Deadline in String format.
     * @throws DukeMissingDescriptionException if the description of the deadline is missing.
     * @throws DukeMissingDeadlineException if the due date of the deadline is missing.
     */
    private String addDeadline(String s) throws DukeMissingDescriptionException, DukeMissingDeadlineException {
        if (s.isBlank()) { // Checks if s is empty.
            throw new DukeMissingDescriptionException();
        }

        // Checks for presence of due date.
        // Throws DukeMissingDeadlineException if not found
        int index = s.indexOf(" /by ");
        if (index == -1 || s.substring(index + 5).isBlank()) {
            throw new DukeMissingDeadlineException();
        }

        // Gets the description and due date from s.
        String desc = s.substring(0, index).strip();
        String by = s.substring(index + 5).strip();

        // Check if s contains a description.
        if (index == 0 || desc.isEmpty()) {
            throw new DukeMissingDescriptionException();
        }

        Deadlines dueDate = Deadlines.createDeadlines(desc, false, by);
        tasks.add(dueDate);
        return "  " + dueDate;
    }

    /**
     * Creates an Event Task and adds it into the TaskList.
     * @param s The data required to create an Event.
     * @return The Event in String format.
     */
    private String addEvent(String s) throws DukeMissingDescriptionException, DukeMissingEventDateException {
        checkValidEvent(s);

        // Gets duration and description from s.
        int fromIndex = s.indexOf(" /from ");
        int toIndex = s.indexOf(" /to ");
        String startTime = s.substring(fromIndex + 7, toIndex).strip();
        String endTime = s.substring(toIndex + 5).strip();
        String desc = s.substring(0, fromIndex).strip();

        Events event = Events.createEvents(desc, false, startTime, endTime);
        tasks.add(event);
        return "  " + event;
    }

    /**
     * Checks if the Event data is valid.
     * @param s The data.
     * @throws DukeMissingDescriptionException if the description of the event is missing.
     * @throws DukeMissingEventDateException if the duration of the event is missing.
     */
    private void checkValidEvent(String s) throws DukeMissingDescriptionException, DukeMissingEventDateException {
        if (s.isBlank()) { // Checks is s is empty.
            throw new DukeMissingDescriptionException();
        }

        // Checks from presence of duration.
        // Throws DukeMissingEventDateException if not found.
        int fromIndex = s.indexOf(" /from ");
        int toIndex = s.indexOf(" /to ");
        boolean hasHeaders = fromIndex != -1 && toIndex != -1;
        boolean hasFrom = toIndex >= fromIndex + 7 && !s.substring(fromIndex + 7, toIndex).isBlank();
        boolean hasTo = !s.substring(toIndex + 5).isBlank();
        if (!hasHeaders || !hasFrom || !hasTo) {
            throw new DukeMissingEventDateException();
        }

        // Gets description from s.
        String desc = s.substring(0, fromIndex).strip();

        // Checks if description is empty,
        if (fromIndex == 0 || desc.isEmpty()) {
            throw new DukeMissingDescriptionException();
        }
    }
}
