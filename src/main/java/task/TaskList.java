package task;
import java.util.ArrayList;

/**
 * Tasklist class is used for handling a given task list,
 * enabling operations such as addition and deletion of tasks in the list.
 *
 * @author      Tseng Chen-Yu
 * @version     %I%, %G%
 * @since       1.0
 */
public class TaskList {
    public static ArrayList<Task> userTasks;
    public TaskList(ArrayList<Task> tasks){
        userTasks = tasks;
    }

    public static ArrayList<Task> getUserTasks(){
        return userTasks;
    }
}
