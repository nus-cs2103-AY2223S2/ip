package lulu;

import lulu.task.Deadline;
import lulu.task.Event;
import lulu.task.Task;
import lulu.task.Todo;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(Task t) {
        this.list.add(t);
    }

    public void remove(int taskNumber) {
        list.remove(taskNumber - 1);
    }

    public int getSize() {
        return this.list.size();
    }

    public Task get(int i) {
        return this.list.get(i);
    }

    public String getRecentTaskDescription() {
        return this.list.get(list.size() - 1).toString();
    }

    public String getTaskDescription(int taskNumber) {
        return this.list.get(taskNumber - 1).toString();
    }

    public void markTask(int taskNumber) {
        this.list.get(taskNumber - 1).markAsDone();
    }

    public void unmarkTask(int taskNumber) {
        list.get(taskNumber - 1).markAsUndone();
    }

    /**
     * this method may be in the wrong spot but will be left here for now
     */
    public void load(String s) {
        String[] command = s.split("`");
        switch (command[0]) {
        case "D":
            this.add(new Deadline(command[2], command[3]));
            if (Integer.valueOf(command[1]) == 1) {
                list.get(list.size() - 1).markAsDone();
            }
            break;
        case "E":
            this.add(new Event(command[2], command[3], command[4]));
            if (Integer.valueOf(command[1]) == 1) {
                list.get(list.size() - 1).markAsDone();
            }
            break;
        case "T":
            this.add(new Todo(command[2]));
            if (Integer.valueOf(command[1]) == 1) {
                list.get(list.size() - 1).markAsDone();
            }
            break;
        }
    }
}
