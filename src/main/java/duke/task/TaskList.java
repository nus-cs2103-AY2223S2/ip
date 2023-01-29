package duke.task;
import duke.Storage;
import java.util.ArrayList;

/**
 * This class represents the list of tasks submitted by the user.
 */
public class TaskList {

    private int taskCount = 0;
    private Storage storage = null;
    private ArrayList<Tasks> tasks = new ArrayList<>();

    /**
     * Construct a task list object from the local storage of previous instance.
     * Set the taskCount to reflect the correct number of tasks.
     * @param storage
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = storage.loadTasks();
        this.taskCount = this.tasks.size();
    }

    /**
     * This method adds a new task in to the task list, and increments the task count by 1.
     */
    public void addTask(Tasks task) {
        this.tasks.add(task);
        this.taskCount += 1;

    }

    /**
     * This method print out the list of tasks in the TaskList object.
     */
    //Credits: adapted from CHATGPT
    public void listTasks() {
        int i = 1;
        System.out.println("Here are the tasks in your list: \n");
        for (Tasks tasks : tasks) {
            System.out.println( i + "." + tasks);
            i++;
        }
    }

    /**
     * This method returns the current task count.
     * @return int
     */
    public int getTaskCount() {
        return taskCount;
    }

    /**
     * This method mark the task at the indicated index in the task list as the indicated state 'done'.
     * @param index
     * @param done
     */
    public void markTask(int index, boolean done){
        this.tasks.get(index).markTask(done);
        this.storage.saveTasks(this.tasks);
    }

    /**
     * This method deletes the task at the indicated index in the task list, and decrement the task count.
     * @param index
     * @return
     */
    public String deleteTask(int index) {
        String returnString = this.tasks.get(index).toString();
        this.tasks.remove(index);
        this.taskCount -= 1;
        System.out.println("Now you have " + this.taskCount + " tasks in the list.");
        this.storage.saveTasks(this.tasks);
        return returnString;
    }

    /**
     * This method prints out the string representation of the task state, whether done or not done.
     * @param index
     * @return
     */
    public String getTaskIcon(int index) {
        return this.tasks.get(index).getStatusIcon();
    }

    /**
     * This method returns the content of the task at the given index in the task list.
     * @param index
     * @return
     */
    public String getTaskContent(int index) {
        return this.tasks.get(index).seeTaskContent();
    }
}
