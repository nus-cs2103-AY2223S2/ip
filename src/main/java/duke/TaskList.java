package duke;

import tasks.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList){
        this.taskList = taskList;
    }

    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    public int size(){
        return taskList.size();
    }

    public void add(Task task){
        taskList.add(task);
    }

    public void markTask(int index){
        taskList.get(index - 1).setAsMarked();
    }

    // check whether it's i -1 for this one
    public void unmarkTask(int index){
        taskList.get(index - 1).setAsUnmarked();
    }

    public Task get(int index){
        return taskList.get(index);
    }

    public void remove(int index){
        taskList.remove(index);
    }
}
