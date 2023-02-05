package task;

import java.util.ArrayList;

/**
 * Manages all Task related operations such as updating
 * the completion status of a task, inserting and deleting
 * the task from the list, and displaying the task.
 */
public class TaskManager {

    /**
     * Main ArrayList strong the tasks.
     */
    private ArrayList<Task> taskArr;

    /**
     * Initialises the ArrayList.
     */
    public TaskManager() {
        this.taskArr = new ArrayList<>();
    }

    /**
     * Marks the task as completed.
     * @param index
     */
    public void checkTask(int index) {
        Task task = taskArr.get(index);
        task.markAsDone();
//        System.out.println("Great job! I have checked this task off the list:");
//        System.out.println(task);
    }

    /**
     * Marks the task as not completed.
     *
     * @param index
     */
    public void uncheckTask(int index) {
        Task task = taskArr.get(index);
        task.markAsUndone();
//        System.out.println("No worries! I have unchecked this task in the list: ");
//        System.out.println(task);
    }

    /**
     * Removes the task from the list.
     *
     * @param index
     */
    public void deleteTask(int index) {
        taskArr.remove(index);
        //System.out.println("Okay! I have removed the task!");
        //System.out.println("There are currently " + taskArr.size() + " task(s) in the list!");
    }

    /**
     * Adds a task to the list.
     *
     * @param task
     */
    public void addTaskToList(Task task) {
        taskArr.add(task);
//        System.out.println("I have added: " + task + "!");
//        System.out.println("There are currently " + taskArr.size() + " task(s) in the list!");
    }

    public Task getTaskFromList(int index) {
        return taskArr.get(index);
    }

    public String printTask(int index) {
        String str = taskArr.get(index).toString();
        return str;
    }

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
