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
     * This method returns the task based on the id.
     *
     * @param i - index of the id.
     * @return Task - Returns the task of given id.
     */
    public Task getTask(int i) {
        return this.tasklst.get(i);
    }


    /**
     * This method removes and returns the task based on the selected id.
     *
     * @param i - index of the id.
     * @return Task - Returns the task of the given id being removed.
     */
    public Task removeTask(int i) {
        return this.tasklst.remove(i);
    }


    /**
     * This method adds a new task to the current tasklist.
     *
     * @param t - The given task.
     */
    public void addTask(Task t) {
        this.tasklst.add(t);
    }


    /**
     * This method returns the size of tasks in the list.
     *
     * @return int - Returns the size of the tasklist.
     */
    public int getSize() {
        return this.tasklst.size();
    }


    /**
     * This method returns the tasklist in a string format.
     *
     * @return String - Returns the output of the list of tasks.
     */
    public String getTasksString() {
        String res = "";
        int counter = 1;
        for (Task tmp : this.tasklst) {
            assert tmp.toString() != "" : "Empty Task should not exist in the tasklst";
            res += counter++ + ". " + tmp.toString() + "\n";
        }
        return res;
    }


    /**
     * This method returns the ArrayList of tasklist.
     *
     * @return ArrayList<> - Returns the ArrayList of Tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasklst;
    }

}
