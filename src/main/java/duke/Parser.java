package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

enum Query { LIST, FIND, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE }
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
            case FIND: 
                find(tasks, tokens[1]);
                break; 
            case MARK:
                mark(true, tasks, tokens[1]);
                break;
            case UNMARK:
                mark(false, tasks, tokens[1]);
                break;
            case TODO:
                addTodo(tasks, tokens[1]);
                break;
            case DEADLINE:
                addDeadline(tasks, tokens[1]);
                break;
            case EVENT:
                addEvent(tasks, tokens[1]);
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
     * Find task containing search string. 
     * 
     * @param tasks
     * @param searchString
     */
    private static void find(TaskList tasks, String searchString) {
        List<Task> listTask = tasks.get().stream().filter(task -> task.toString().contains(searchString)).collect(Collectors.toList());
        Ui.find(new ArrayList<Task>(listTask));
        
    }

    /**
     * Mark or unmark a task based on input.
     * 
     * @param isMark
     * @param tasks
     * @param numString
     */
    private static void mark(boolean isMark ,TaskList tasks, String numString) {
        try {
            int num = Integer.parseInt(numString);
            Task task = tasks.mark(isMark, num - 1);
            Ui.mark(isMark, task);
        } catch (NumberFormatException exception) {
            Ui.notANumber();
        } catch (IndexOutOfBoundsException exception) {
            Ui.numberOutOfBounds();
        }
    }
    /**
     * Adds a todo.
     * @param tasks
     * @param nameString 
     */
    private static void addTodo(TaskList tasks, String nameString) {
        Todo task = new Todo(nameString);
        tasks.add(task);
        Ui.addTask("todo", task);
    }

    /**
     * Adds a deadline.
     * @param tasks
     * @param paramString 
     */
    private static void addDeadline(TaskList tasks, String paramString) {
        try {
            String[] tokens = paramString.split(" /by ");
            String name = tokens[0];
            String by = tokens[1];
            Deadline task = new Deadline(name, parseDate(by));            
            tasks.add(task);
            Ui.addTask("deadline", task);
        } catch (IndexOutOfBoundsException exception) {
            Ui.missingOptions("/by");
        } catch (DateTimeParseException exception) {
            Ui.wrongDateFormat();
        }
    }

    /**
     * Adds an event.
     * @param tasks
     * @param paramString
     */
    private static void addEvent(TaskList tasks, String paramString) {
        try {
            String[] tokens = paramString.split(" /from ");
            String name = tokens[0];
            String tmpToken = tokens[1];
            String[] options = tmpToken.split(" /to ");
            String from = options[0];
            String to = options[1];
            Event task = new Event(name, parseDate(from), parseDate(to));            
            tasks.add(task);
            Ui.addTask("event", task);
        } catch (IndexOutOfBoundsException exception) { 
            Ui.missingOptions("/from /to");
        } catch (DateTimeParseException exception) {
            Ui.missingOptions("/by");
        }    
    }

    /**
     * Deletes a task.
     * @param tasks
     * @param numString
     */
    private static void delete(TaskList tasks, String numString) {
        try {
            int num = Integer.parseInt(numString);
            Task task = tasks.delete(num - 1);
            Ui.delete(task);
        } catch (NumberFormatException exception) {
            Ui.notANumber();
        } catch (IndexOutOfBoundsException exception) {
            Ui.numberOutOfBounds();
        }
    }

    /**
     * Parses dateString to a given format.
     * @param dateString
     * @return parsed LocalDate object from given string
     * @throws DateTimeParseException
     */
    private static LocalDate parseDate(String dateString) throws DateTimeParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, format);
    }
}