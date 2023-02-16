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
        int indexToDelete = taskNum - 1;
        assert indexToDelete < this.arrList.size() : "Invalid index";
        return this.arrList.remove(taskNum - 1);
    }
    public void add(Task task) {
        int taskSize = this.arrList.size();
        this.arrList.add(task);
        assert taskSize + 1 == this.arrList.size() : "Addition undefined behaviour";
    }

    public void setToMark(int index) {
        int taskSize = this.arrList.size();
        Task toMark = arrList.get(index - 1);
        toMark.changeCompletion();
        this.arrList.set(index-1, toMark);
        assert taskSize == this.arrList.size() : "Setting to mark resulted in undefined behaviour";
    }
    public void setToUnmark(int index) {
        int taskSize = this.arrList.size();
        Task toMark = arrList.get(index - 1);
        toMark.changeCompletion();
        this.arrList.set(index - 1, toMark);
        assert taskSize == this.arrList.size() : "Unmarking resulted in undefined behaviour";
    }

    public Task get(int index) {
        return arrList.get(index - 1);
    }

    public TaskList search(String toFind) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for(Task tasks : arrList) {
            if(tasks.description.contains(toFind)) {
                matchingTasks.add(tasks);
            }
        }
        TaskList toReturn = new TaskList(matchingTasks);
        assert toReturn.size() <= this.arrList.size() : "Search operation resulted in undefined behaviour!";
        return toReturn;
    }

    public String printList() {
        int counter = 0;
        StringBuilder toReturn = new StringBuilder("Here are the tasks in your list my premier:").append(System.getProperty("line.separator"));
        for (Task i : arrList) {
            counter++;
            toReturn.append(counter).append(". ").append(i.toString()).append(System.getProperty("line.separator"));
        }
        return toReturn.toString();
    }

}
