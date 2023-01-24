package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

enum Query { LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE }
class Parser {
    /**
     * Get input string, parses it and run corresponding functions.
     * 
     * some corresponding function modifies TaskList object
     *  
     * @param input
     * @param tasks
     * @throws IllegalArgumentException
     * @throws IndexOutOfBoundsException
     */
    public static void parseRawString(String input, TaskList tasks) throws IllegalArgumentException, IndexOutOfBoundsException {
        String[] tokens = input.split(" ",2);
        Query query = Query.valueOf(tokens[0].toUpperCase());
        switch (query) {
            case LIST: 
                list(tasks);
                break; 
            case MARK:
                mark(true, tasks, tokens[1]);
                break;
            case UNMARK:
                mark(false, tasks, tokens[1]);
                break;
            case TODO:
                todo(tasks, tokens[1]);
                break;
            case DEADLINE:
                deadline(tasks, tokens[1]);
                break;
            case EVENT:
                event(tasks, tokens[1]);
                break;
            case DELETE:
                delete(tasks, tokens[1]);
                break;
        }
    }
    /**
     * Pass ArrayList of Task to UI.
     * 
     * @param tasks
     */
    private static void list(TaskList tasks) {
        Ui.list(tasks.get());
    }

    /**
     * Mark or unmark a task based on input.
     * 
     * @param isMark
     * @param tasks
     * @param s
     */
    private static void mark(boolean isMark ,TaskList tasks, String s) {
        try {
            int num = Integer.parseInt(s);
            Task task = tasks.mark(isMark, num - 1);
            Ui.mark(isMark, task);
        } catch (NumberFormatException e) {
            Ui.notANumber();
        } catch (IndexOutOfBoundsException e) {
            Ui.numberOutOfBounds();
        }
    }
    /**
     * Adds a todo.
     * @param tasks
     * @param s
     */
    private static void todo(TaskList tasks, String s) {
        Todo task = new Todo(s);
        tasks.add(task);
        Ui.addTask("todo", task);
    }

    /**
     * Adds a deadline.
     * @param tasks
     * @param s
     */
    private static void deadline(TaskList tasks, String s) {
        try {
            String[] tokens = s.split(" /by ");
            String name = tokens[0];
            String by = tokens[1];
            Deadline task = new Deadline(name, parseDate(by));            
            tasks.add(task);
            Ui.addTask("deadline", task);
        } catch (IndexOutOfBoundsException e) {
            Ui.missingOptions("/by");
        } catch (DateTimeParseException e) {
            Ui.wrongDateFormat();
        }
    }

    /**
     * Adds an event.
     * @param tasks
     * @param s
     */
    private static void event(TaskList tasks, String s) {
        try {
            String[] tokens = s.split(" /from ");
            String name = tokens[0];
            String tmptoken = tokens[1];
            String[] options = tmptoken.split(" /to ");
            String from = options[0];
            String to = options[1];
            Event task = new Event(name, parseDate(from), parseDate(to));            
            tasks.add(task);
            Ui.addTask("event", task);
        } catch (IndexOutOfBoundsException e) { 
            Ui.missingOptions("/from /to");
        } catch (DateTimeParseException e) {
            Ui.missingOptions("/by");
        }    
    }

    /**
     * Deletes a task.
     * @param tasks
     * @param s
     */
    private static void delete(TaskList tasks, String s) {
        try {
            int num = Integer.parseInt(s);
            Task task = tasks.delete(num - 1);
            Ui.delete(task);
        } catch (NumberFormatException e) {
            Ui.notANumber();
        } catch (IndexOutOfBoundsException e) {
            Ui.numberOutOfBounds();
        }
    }

    /**
     * Parses dateString to a given format.
     * @param date
     * @return
     * @throws DateTimeParseException
     */
    private static LocalDate parseDate(String date) throws DateTimeParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, format);
    }
}