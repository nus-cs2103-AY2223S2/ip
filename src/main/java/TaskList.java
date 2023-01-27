import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }
    public void storeTask(Task t) {
        this.taskList.add(t);
    }
    public Task addToDoFromUser(String i) {
        ToDo t = new ToDo(i.replace("todo ", ""));
        storeTask(t);
        return t;
    }
    public Task addToDoFromFile(String sf) {
        ToDo t = new ToDo(sf.substring(7));
        storeTask(t);
        return t;
    }
    public Task addDeadlineFromUser(String i) {
        String[] contents = i.split(" /by ");
        Deadline d = new Deadline(contents[0].replace("deadline ", ""), contents[1]);
        storeTask(d);
        return d;
    }
    public Task addDeadlineFromFile(String sf) {
        String[] contents = sf.substring(7).split(" \\| ");
        String deadlineDesc = contents[0];
        String deadlineBy = contents[1];
        Deadline d = new Deadline(deadlineDesc, deadlineBy);
        storeTask(d);
        return d;
    }
    public Task addEventFromUser(String i) {
        String[] contents = i.split(" /from ");
        String[] fromTo = contents[1].split(" /to ");
        Event e = new Event(contents[0].replace("event ", ""), fromTo[0], fromTo[1]);
        storeTask(e);
        return e;
    }
    public Task addEventFromFile(String sf) {
        String[] contents = sf.substring(7).split(" \\| ");
        String eventDesc = contents[0];
        String[] eventFromTo = contents[1].split(" - ");
        String eventFrom = eventFromTo[0];
        String eventTo = eventFromTo[1];
        Event e = new Event(eventDesc, eventFrom, eventTo);
        return e;
    }
    public void deleteTask(int index) {
        taskList.remove(index - 1);
    }
    public ArrayList<Task> getTasks() {
        return this.taskList;
    }
}
