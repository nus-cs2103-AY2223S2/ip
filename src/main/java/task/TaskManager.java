package task;

import java.util.ArrayList;

import util.WriteToFile;

public class TaskManager {
    private ArrayList<Task> taskArr;
    public TaskManager() {
        this.taskArr = new ArrayList<>();
    }

    public void checkTask(int index) {
        Task task = taskArr.get(index);
        task.markAsDone();
        System.out.println("Great job! I have checked this task off the list:");
        System.out.println(task);
    }

    public void uncheckTask(int index) {
        Task task = taskArr.get(index);
        task.markAsUndone();
        System.out.println("No worries! I have unchecked this task in the list: ");
        System.out.println(task);
    }

    public void deleteTask(int index) {
        taskArr.remove(index);
        System.out.println("Okay! I have removed the task!");
        System.out.println("There are currently " + taskArr.size() + " task(s) in the list!");
    }

    public void addTaskToList(Task task) {
        taskArr.add(task);
        //WriteToFile.appendToFile(task.getStatusIcon() + task.getDescription());
        System.out.println("I have added: " + task + "!");
        System.out.println("There are currently " + taskArr.size() + " task(s) in the list!");
    }

    //display items in list method
    public void displayList() {
        if(taskArr.isEmpty()) {
            System.out.println("Your list is empty, please add a task!");
        }
        for(int i = 0; i < taskArr.size(); i++) {
            Task tsk = taskArr.get(i);
            System.out.println(i + 1 + ". " + tsk);
        }
    }

    public int getTaskArraySize() {
        return taskArr.size();
    }

    public ArrayList<Task> getTaskArr() {
        return taskArr;
    }

}
