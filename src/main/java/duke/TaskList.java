package duke;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskArr;
    private Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;

        // Load list from storage
        taskArr = storage.loadList();
    }

    public void saveList() {
        storage.storeList(taskArr);
    }

    public Task getTask(int i) {
        return taskArr.get(i);
    }

    public int getCount() {
        return taskArr.size();
    }

    public Task addTodo(String descrip) {
        Task newTodo = new Task(descrip);
        taskArr.add(newTodo);
        return newTodo;
    }

    public Deadline addDeadline(String descrip, String by) {
        Deadline newDeadline = new Deadline(descrip, by);
        taskArr.add(newDeadline);
        return newDeadline;
    }

    public Event addEvent(String descrip, String from, String to) {
        Event newEvent = new Event(descrip, from, to);
        taskArr.add(newEvent);
        return newEvent;
    }

    public void removeTask(int index) {
        taskArr.remove(index);
    }

}
