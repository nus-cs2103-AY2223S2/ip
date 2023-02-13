package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

enum Query { LIST, FIND, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, HELP }
public class Parser {
    /**
     * Get input string, parses it and run corresponding functions.
     * some corresponding function modifies TaskList object
     * @param input raw input from user
     * @param tasks TaskList object
     * @throws IllegalArgumentException command is not in enums
     * @throws IndexOutOfBoundsException commands missing corresponding tokens
     */
    public static String parseRawString(String input, TaskList tasks) throws IllegalArgumentException,
            IndexOutOfBoundsException {
        String[] tokens = input.split(" ", 2);
        Query query = Query.valueOf(tokens[0].toUpperCase());
        switch (query) {
        case LIST:
            return list(tasks);
        case FIND:
            return find(tasks, tokens[1]);
        case MARK:
            return mark(true, tasks, tokens[1]);
        case UNMARK:
            return mark(false, tasks, tokens[1]);
        case TODO:
            return addTodo(tasks, tokens[1]);
        case DEADLINE:
            return addDeadline(tasks, tokens[1]);
        case EVENT:
            return addEvent(tasks, tokens[1]);
        case DELETE:
            return delete(tasks, tokens[1]);
        case HELP:
            return help();
        default:
            return "";
        }
    }
    /**
     * Pass ArrayList of Task to UI.
     * @param tasks TaskList Object
     */
    private static String list(TaskList tasks) {
        return Ui.list(tasks.get());
    }

    /**
     * Find task containing search string.
     * @param tasks TaskList object
     * @param searchString string to find task
     */
    private static String find(TaskList tasks, String searchString) {
        List<Task> listTask = tasks
                .get()
                .stream()
                .filter(task -> task.toString().contains(searchString))
                .collect(Collectors.toList());
        return Ui.find(new ArrayList<>(listTask));
    }

    /**
     * Mark or unmark a task based on input.
     * @param isMark to mark or unmark
     * @param tasks TaskList object
     * @param numString task number
     */
    private static String mark(boolean isMark, TaskList tasks, String numString) {
        try {
            int num = Integer.parseInt(numString);
            Task task = tasks.mark(isMark, num - 1);
            assert num - 1 < tasks.get().size();
            return Ui.mark(isMark, task);
        } catch (NumberFormatException exception) {
            return Ui.notANumber();
        } catch (IndexOutOfBoundsException exception) {
            return Ui.numberOutOfBounds();
        }
    }
    /**
     * Adds a todo.
     * @param tasks TaskList object
     * @param nameString name of todo
     */
    private static String addTodo(TaskList tasks, String nameString) {
        Todo task = new Todo(nameString);
        tasks.add(task);
        return Ui.addTask("todo", task);
    }

    /**
     * Adds a deadline.
     * @param tasks TaskList object
     * @param paramString by option
     */
    private static String addDeadline(TaskList tasks, String paramString) {
        try {
            String[] tokens = paramString.split(" /by ");
            String name = tokens[0];
            String by = tokens[1];
            Deadline task = new Deadline(name, parseDate(by));
            tasks.add(task);
            return Ui.addTask("deadline", task);
        } catch (IndexOutOfBoundsException exception) {
            return Ui.missingOptions("/by");
        } catch (DateTimeParseException exception) {
            return Ui.wrongDateFormat();
        }
    }

    /**
     * Adds an event.
     * @param tasks TaskList object
     * @param paramString to and from option
     */
    private static String addEvent(TaskList tasks, String paramString) {
        try {
            String[] tokens = paramString.split(" /from ");
            String name = tokens[0];
            String tmpToken = tokens[1];
            String[] options = tmpToken.split(" /to ");
            String from = options[0];
            String to = options[1];
            LocalDate fromDate = parseDate(from);
            LocalDate toDate = parseDate(to);
            if (fromDate.isAfter(toDate)) {
                return Ui.wrongDateRange();
            }
            Event task = new Event(name, parseDate(from), parseDate(to));
            tasks.add(task);
            return Ui.addTask("event", task);
        } catch (IndexOutOfBoundsException exception) {
            return Ui.missingOptions("/from /to");
        } catch (DateTimeParseException exception) {
            return Ui.missingOptions("/by");
        }
    }

    /**
     * Deletes a task.
     * @param tasks TaskList object
     * @param numString task number to delete
     */
    private static String delete(TaskList tasks, String numString) {
        try {
            int num = Integer.parseInt(numString);
            Task task = tasks.delete(num - 1);
            return Ui.delete(task);
        } catch (NumberFormatException exception) {
            return Ui.notANumber();
        } catch (IndexOutOfBoundsException exception) {
            return Ui.numberOutOfBounds();
        }
    }

    /**
     * Parses dateString to a given format.
     * @param dateString date in String format to be parsed
     * @return parsed LocalDate object from given string
     * @throws DateTimeParseException date string wrong format
     */
    private static LocalDate parseDate(String dateString) throws DateTimeParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, format);
    }

    private static String help() {
        return Ui.help();
    }
}
