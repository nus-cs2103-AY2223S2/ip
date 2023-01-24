package task;

import java.util.ArrayList;

public class TaskManager {
    protected ArrayList<Task> taskArr;
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
        System.out.println("I have added: " + task + "!");
        System.out.println("There are currently " + taskArr.size() + " task(s) in the list!");
    }

    //credit: https://stackabuse.com/java-check-if-string-is-a-number/
    public boolean isNumeric(String string) {
        int intValue;

        if(string == null || string.equals("")) {
            System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            //System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
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

}
