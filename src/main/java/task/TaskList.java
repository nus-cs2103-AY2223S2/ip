package task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> tasks;


    public TaskList() {
        this.tasks = new ArrayList<Task>(100);
    }

    public boolean isAllCompleted() {
        for (int i = 0; i < tasks.size(); i++) {
            if (this.tasks.get(i).isMarked()) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public int getNumTasks() {
        return tasks.size();
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public boolean doesTaskExist(int taskNum) {
        return taskNum > 0 && taskNum <= getNumTasks();
    }

    public void addTodo(String desc) {
        Todo t = new Todo(desc);
        tasks.add(t);
        System.out.println("    " + t);
        printNumTasks();
    }

    public void addDeadline(String date, String desc) {
        Deadline d = new Deadline(date, desc);
        tasks.add(d);
        System.out.println("    " + d);
        printNumTasks();
    }

    public void addEvent(String start, String end, String desc) {
        Event e = new Event(start, end, desc);
        tasks.add(e);
        System.out.println("    " + e);
        printNumTasks();
    }

    public void deleteTask(int taskNum) {
        System.out.println("    " + tasks.get(taskNum-1));
        tasks.remove(taskNum-1);
        printNumTasks();
    }

    public void printNumTasks() {
        if (getNumTasks() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            String str = String.format("Now you have %d tasks in the list.", getNumTasks());
            System.out.println(str);
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < getNumTasks(); i++) {
            if (i == getNumTasks()-1) {
                str += (i+1) + ". " + this.getTask(i);
            } else {
                str += (i+1) + ". " + this.getTask(i) + '\n';
            }
        }
        return str;
    }

}
