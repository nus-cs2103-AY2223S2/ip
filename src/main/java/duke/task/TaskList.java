package duke.task;

import duke.Storage;

import java.util.ArrayList;

public class TaskList {

    private int taskCount = 0;
    private Storage storage = null;
    private ArrayList<Tasks> tasks = new ArrayList<>();

    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = storage.loadTasks();
        this.taskCount = this.tasks.size();
    }

    public void addTask(Tasks task) {
        this.tasks.add(task);
        this.taskCount += 1;

    }

    //adopted from CHATGPT

    public void listTasks() {
        int i = 1;
        System.out.println("Here are the tasks in your list: \n");
        for (Tasks tasks : tasks) {
            System.out.println( i + "." + tasks);
            i++;
        }
    }
    public int getTaskCount() {
        return taskCount;
    }

    public void markTask(int index, boolean done){
        this.tasks.get(index).markTask(done);
        this.storage.saveTasks(this.tasks);
    }

    public String deleteTask(int index) {
        String returnString = this.tasks.get(index).toString();
        this.tasks.remove(index);
        this.taskCount -= 1;
        System.out.println("Now you have " + this.taskCount + " tasks in the list.");
        this.storage.saveTasks(this.tasks);
        return returnString;
    }

    public String getTaskIcon(int index) {
        return this.tasks.get(index).getStatusIcon();
    }

    public String getTaskContent(int index) {
        return this.tasks.get(index).seeTaskContent();
    }
}
