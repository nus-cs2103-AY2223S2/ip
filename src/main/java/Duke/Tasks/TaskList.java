
package Duke.Tasks;



import  java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> listOfTasks;

    /**
     * The contructor for ToDo Task
     *
     * @param listOfTasks
     *
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * method to add task to list of Tasks
     *
     */
    public void addTask(Task t) {
        listOfTasks.add(t);
    }

    /**
     * method to delete task from list of Tasks
     *
     */
    public Task removeTask(int i) {
        return listOfTasks.remove(i - 1);
    }

    /**
     * method to mark task as done in list of Tasks
     *
     */
    public void markDone(int i) {
        listOfTasks.get(i - 1).markDone();
    }

    /**
     * method to unmark task as done in list of Tasks
     *
     */
    public void markNotDone(int i) {
        listOfTasks.get(i - 1).markNotDone();
    }

    /**
     * getter method to get task  in list of Tasks
     *
     */
    public Task get(int i) {
        return listOfTasks.get(i - 1);
    }

    /**
     * method to get size of list of tasks
     *
     */
    public int size() {
        return listOfTasks.size();
    }


}
