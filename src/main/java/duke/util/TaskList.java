package duke.util;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> toDoList = new ArrayList<>();

    /**
     * Mark a Task as done, and output the task for the user to see.
     *
     * @param ind int the index of the task to be marked.
     */
    public void markDone(int ind){
        toDoList.get(ind).markDone();
    }

    /**
     * Mark a Task as undone, and output the task for the user to see.
     *
     * @param ind int the index of the task to be marked.
     */
    public void markUndone(int ind){
        toDoList.get(ind).markUndone();
    }

    /**
     * Add a Task to the list.
     *
     * @param task Task the task to be added.
     */
    public void add(Task task){
        toDoList.add(task);
    }

    /**
     * Remove a Task from the list.
     *
     * @param ind int the index of the task to be removed.
     */
    public void remove(int ind){
        toDoList.remove(ind);
    }

    /**
     * Get the Task.
     *
     * @param ind int the index of the task to be extracted.
     */
    public Task get(int ind){
        return toDoList.get(ind);
    }

    /**
     * Returns the size of the list.
     *
     * @return int size of list.
     */
    public int size(){
        return toDoList.size();
    }

    /**
     * Find tasks with name that contain subS.
     *
     * @param subS the substring to be found.
     * @return String all tasks that contain subS.
     */
    public String find(String subS){
        TaskList foundList = new TaskList();
        for (int i = 0; i < toDoList.size(); i++){
            if (toDoList.get(i).contains(subS)) {
                foundList.add(toDoList.get(i));
            }
        }
        return "Here ya go :)\n" + foundList.toString();
    }

    @Override
    public String toString() {
        if (toDoList.size() == 0) {
            return "Your list is currently empty. Enter using one of the commands :)";
        }
        String s = "";
        for (int i = 0; i < toDoList.size(); i++){
            s += Integer.toString(i+1) + ". " + toDoList.get(i).toString() +"\n";
        }
        return s;
    }
}
