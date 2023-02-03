package duke;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private static List<Task> toDoList = new ArrayList<>();

    /**
     * Mark a Task as done, and output the task for the user to see.
     *
     * @param ind int the index of the task to be marked.
     */
    public static void markDone(int ind){
        toDoList.get(ind).markDone();
    }

    /**
     * Mark a Task as undone, and output the task for the user to see.
     *
     * @param ind int the index of the task to be marked.
     */
    public static void markUndone(int ind){
        toDoList.get(ind).markUndone();
    }

    /**
     * Add a Task to the list.
     *
     * @param task Task the task to be added.
     */
    public static void add(Task task){
        toDoList.add(task);
    }

    /**
     * Remove a Task from the list.
     *
     * @param ind int the index of the task to be removed.
     */
    public static void remove(int ind){
        toDoList.remove(ind);
    }

    /**
     * Get the Task.
     *
     * @param ind int the index of the task to be extracted.
     */
    public static Task get(int ind){
        return toDoList.get(ind);
    }

    /**
     * Output all the Tasks for user to see.
     */
    public static void print(){
        for (int i = 0; i < toDoList.size(); i++){
            System.out.print(Integer.toString(i+1) + ". ");
            toDoList.get(i).printTask();
        }
    }

    /**
     * Returns the size of the list.
     *
     * @return int size of list.
     */
    public static int size(){
        return toDoList.size();
    }
}
