import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;

public class DukeList {

    public ArrayList<Task> list = new ArrayList<>();

    public ArrayList<Task> getList() {
        return list;
    }

    public ToDo addToDo(String chat) {
        Function<String, HashMap<String, String>> parser = todoParser();
        HashMap<String, String> parsed = parser.apply(chat);
        ToDo newToDo = new ToDo(parsed);
        list.add(newToDo);
        return newToDo;
    }

    public Deadline addDeadline(String chat) {
        Function<String, HashMap<String, String>> parser = deadlineParser();
        HashMap<String, String> parsed = parser.apply(chat);
        System.out.println(parsed);
        Deadline newDeadline = new Deadline(parsed);
        list.add(newDeadline);
        return newDeadline;
    }

    public Event addEvent(String chat) {
        Function<String, HashMap<String, String>> parser = eventParser();
        HashMap<String, String> parsed = parser.apply(chat);
        Event newEvent = new Event(parsed);
        list.add(newEvent);
        return newEvent;
    }

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

    private Function<String, HashMap<String, String>> todoParser() {
        ArrayList<String> toParse = new ArrayList<>(Arrays.asList("todo"));
        return createParser(toParse);
    }

    private Function<String, HashMap<String, String>> deadlineParser() {
        ArrayList<String> toParse = new ArrayList<>(Arrays.asList("deadline", "/by"));
        return createParser(toParse);
    }

    private Function<String, HashMap<String, String>> eventParser() {
        ArrayList<String> toParse = new ArrayList<>(Arrays.asList("event", "/from", "/to"));
        return createParser(toParse);
    }

    public int getSize() {
        return list.size();
    }

    public Task mark(String taskNumberString) {
        Integer taskNumber = Integer.valueOf(taskNumberString) - 1;
        Task task = list.get(taskNumber);
        task.mark();
        return task;
    }

    public Task unmark(String taskNumberString) {
        Integer taskNumber = Integer.valueOf(taskNumberString) - 1;
        Task task = list.get(taskNumber);
        task.unmark();
        return task;
    }
}
