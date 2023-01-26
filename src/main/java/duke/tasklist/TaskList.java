package duke.tasklist;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> arrList;

    public ArrayList<Task> getList() {
        return this.arrList;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.arrList = tasks;
    }
    public TaskList() {
        this.arrList = new ArrayList<Task>();
    }
    public int size() {
        return this.arrList.size();
    }

    public Task delete(int taskNum) {
        return this.arrList.remove(taskNum - 1);
    }
    public void add(Task task) {
        this.arrList.add(task);
    }

    public void setToMark(int index) {
        Task toMark = arrList.get(index - 1);
        toMark.changeCompletion();
        this.arrList.set(index-1, toMark);
    }
    public void setToUnmark(int index) {
        Task toMark = arrList.get(index - 1);
        toMark.changeCompletion();
        this.arrList.set(index - 1, toMark);
    }

    public Task get(int index) {
        return arrList.get(index - 1);
    }

    public void printList() {
        int counter = 0;
        for (Task i : arrList) {
            counter++;
            System.out.println(counter + ". " + i.toString());
        }
    }

}
