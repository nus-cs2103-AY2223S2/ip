import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class DukeList {

    public ArrayList<Task> list = new ArrayList<>();

    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Using the chat of the user, creates a new ToDo object and adds it to the list
     * @param chat (String inputted by user)
     * @return newToDo
     * @throws Exception (Task description cannot be empty)
     */
    public ToDo addToDo(String chat) throws DukeException {
        Function<String, HashMap<String, String>> parser = todoParser();
        HashMap<String, String> parsed = parser.apply(chat);
        ToDo newToDo = new ToDo(parsed);
        list.add(newToDo);
        return newToDo;
    }

    /**
     * Using the chat of the user, creates a new Deadline object and adds it to the list
     * @param chat (String inputted by user)
     * @return newDeadline
     * @throws Exception (Task description cannot be empty)
     */
    public Deadline addDeadline(String chat) throws DukeException {
        Function<String, HashMap<String, String>> parser = deadlineParser();
        HashMap<String, String> parsed = parser.apply(chat);
        Deadline newDeadline = new Deadline(parsed);
        list.add(newDeadline);
        return newDeadline;
    }

    /**
     * Using the chat of the user, creates a new Event object and adds it to the list
     * @param chat (String inputted by user)
     * @return newEvent
     * @throws Exception (Task description cannot be empty)
     */
    public Event addEvent(String chat) throws DukeException {
        Function<String, HashMap<String, String>> parser = eventParser();
        HashMap<String, String> parsed = parser.apply(chat);
        Event newEvent = new Event(parsed);
        list.add(newEvent);
        return newEvent;
    }

    /**
     * Creates a parser that is able to take in a String and parse it into components.
     * The components are defined by the keywords.
     * @param keywords (Defines the components to parse)
     * @return Function<String, HashMap<String, String>>
     */
    private Function<String, HashMap<String, String>> createParser(ArrayList<String> keywords) {
        return (String chat) -> {
            int waitingForKeyword = 0;
            String currentKey = "";
            String currentValue = "";
            HashMap<String, String> parsed = new HashMap<>();
            for (String word : chat.split(" ")) {
                if (waitingForKeyword < keywords.size() && word.equals(keywords.get(waitingForKeyword))) {
                    parsed.put(currentKey, currentValue);
                    currentKey = keywords.get(waitingForKeyword++);
                    currentValue = "";
                } else {
                    currentValue += word + " ";
                }
            }
            parsed.put(currentKey, currentValue);
            return parsed;
        };
    }

    /**
     * Creates a parser that converts a user inputted String into a HashMap containing: "ToDo"
     * @return created parser
     */
    private Function<String, HashMap<String, String>> todoParser() {
        ArrayList<String> toParse = new ArrayList<>(Arrays.asList("todo"));
        return createParser(toParse);
    }

    /**
     * Creates a parser that converts a user inputted String into a HashMap containing: "deadline", "/by"
     * @return created parser
     */
    private Function<String, HashMap<String, String>> deadlineParser() {
        ArrayList<String> toParse = new ArrayList<>(Arrays.asList("deadline", "/by"));
        return createParser(toParse);
    }

    /**
     * Creates a parser that converts a user inputted String into a HashMap containing: "event", "/from", "/to"
     * @return created parser
     */
    private Function<String, HashMap<String, String>> eventParser() {
        ArrayList<String> toParse = new ArrayList<>(Arrays.asList("event", "/from", "/to"));
        return createParser(toParse);
    }

    public int getSize() {
        return list.size();
    }

    /**
     * Marks the task corresponding to taskNumberString as complete
     * @param taskNumberString
     * @return marked task
     */
    public Task mark(String taskNumberString) {
        Integer taskNumber = Integer.valueOf(taskNumberString) - 1;
        Task task = list.get(taskNumber);
        task.mark();
        return task;
    }

    /**
     * Marks the task corresponding to taskNumberString as not complete
     * @param taskNumberString
     * @return unmarked task
     */
    public Task unmark(String taskNumberString) {
        Integer taskNumber = Integer.valueOf(taskNumberString) - 1;
        Task task = list.get(taskNumber);
        task.unmark();
        return task;
    }

    /**
     * Deletes the task corresponding to taskNumberString
     * @param taskNumberString
     * @return deleted task
     */
    public Task delete(String taskNumberString) {
        int taskNumber = Integer.valueOf(taskNumberString) - 1;
        Task task = list.remove(taskNumber);
        return task;
    }
}
