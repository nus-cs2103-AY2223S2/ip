package duke;
import java.time.LocalDateTime;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> arrayList;
    public TaskList(ArrayList<Task> arrayList) {
        this.arrayList = arrayList;
    }

    public Task mark(Integer index)  {

        arrayList.get(index - 1).markAsDone();
        return arrayList.get(index - 1);
    }

    public Task unmark(Integer index) {

        arrayList.get(index - 1).markAsUndone();
        return arrayList.get(index - 1 );
    }

    public Task delete(Integer index) {
        Task t = arrayList.get(index - 1 );
        arrayList.remove(index - 1);
        return t;
    }

    public Task addTodo(String description) {

        Task t = new Todo(description);
        arrayList.add(t);
        return t;
    }

    public Task addDeadline(String description, LocalDateTime by) {

        Task t = new Deadline(description, by);
        arrayList.add(t);
        return t;
    }

    public Task addEvent(String description, LocalDateTime from, LocalDateTime to) {

        Task t = new Event(description, from, to);
        arrayList.add(t);
        return t;
    }

    public void list() {
        for (int i = 1; i < arrayList.size() + 1; i++) {
            System.out.println(i + ". " + arrayList.get(i - 1));
        }
    }

    public Integer getLength() {
        return arrayList.size();
    }

    public Task get(Integer i) {
        return arrayList.get(i);
    }
    public String toString() {
        String str = "";
        for (int i = 1; i < arrayList.size() + 1; i++) {
            str = str + i + ". " + arrayList.get(i - 1) + "\n";
        }
        return str;
    }
}
