package tasks;

import java.io.Serializable;
import storage.Storage;

public class TaskList implements Serializable {
    private static final long serialVersionUID = 8098680977751428278L;
    private int numTasks = 0;
    private Task[] tasksArray = new Task[100];

    public Task[] getTasks() {
        return this.tasksArray;
    }

    public int getNumTasks() {
        return this.numTasks;
    }

    public void printAll() {
        for (int i = 0; i < numTasks; i++) {
            String printedString = String.format("%d. %s ", i + 1, tasksArray[i].toString());
            System.out.println(printedString);
        }
    }

    public void addTask(Task task) {
        this.tasksArray[numTasks] = task;
        numTasks++;
        // new Storage().writeTaskList(this);
        Storage.writeTaskList(this);
    }

    public String markTask(int taskIndex) {
        this.tasksArray[taskIndex - 1].mark();
        // new Storage().writeTaskList(this);
        Storage.writeTaskList(this);
        return this.tasksArray[taskIndex - 1].toString();
    }

    public String unmarkTask(int taskIndex) {
        this.tasksArray[taskIndex - 1].unmark();
        // new Storage().writeTaskList(this);
        Storage.writeTaskList(this);
        return this.tasksArray[taskIndex - 1].toString();
    }
}
