package duke.task;


import java.util.ArrayList;

/**
 * Class of Tasklist that adds the task into a list format.
 */
public class TaskList {

    private ArrayList<Task> tasklst;

    public TaskList() {
        this.tasklst = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasklst) {
        this.tasklst = tasklst;
    }


    /**
     * Returns the task based on the id.
     *
     * @param i index of the id.
     * @return Task returns the task of given id.
     */
    public Task getTask(int i) {
        return this.tasklst.get(i);
    }


    /**
     * Removes and returns the task based on the selected id.
     *
     * @param i index of the id.
     * @return Task returns the task of the given id being removed.
     */
    public Task removeTask(int i) {
        return this.tasklst.remove(i);
    }


    /**
     * Adds a new task to the current tasklist.
     *
     * @param task the given task.
     */
    public void addTask(Task task) {
        this.tasklst.add(task);
    }


    /**
     * Returns the size of tasks in the list.
     *
     * @return int returns the size of the tasklist.
     */
    public int getSize() {
        return this.tasklst.size();
    }


    /**
     * Returns the result of tasklist in a string format.
     *
     * @return String returns the output of the list of tasks.
     */
    public String getTasksString() {
        String res = "";
        int counter = 1;
        for (Task tmp : this.tasklst) {
            assert tmp.toString() != "" : "Empty Task should not exist in the tasklst";
            res += counter++ + ". " + tmp + "\n";
        }
        return res;
    }

    /**
     * Checks if the task is a duplicate.
     *
     * @param task the task that the user wants to add.
     * @return boolean returns if the task had already been added to the list.
     */
    public boolean isTaskDuplicate(Task task) {
        int indexOfDescription = 7;
        for (Task t : this.tasklst) {
            if (t.toString().substring(indexOfDescription).equals(task.toString().substring(indexOfDescription))) {
                return true;
            }
        }
        return false;
    }


    /**
     * This method returns the ArrayList of tasklist.
     *
     * @return ArrayList<> returns the ArrayList of Tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasklst;
    }

}
