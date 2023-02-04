package berry.task;

import java.util.ArrayList;

/**
 * Contains the task list.
 */
public class TaskList {

    private static ArrayList<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public ArrayList<Task> getList() {
        return listOfTasks;
    }

    /**
     * Adds a new task into the task list.
     *
     * @param task is the task to be added
     */
    public void addTask(Task task) {
        listOfTasks.add(task);
        System.out.println(task.toString());
    }

    /**
     * Deletes an existing task.
     *
     * @param index is the number identifying the task given by the user as seen from the task list
     */
    public void deleteTask(int index){
        System.out.println(listOfTasks.get(index - 1).toString());
        listOfTasks.remove(index - 1);
        printNumberOfTasks();
    }

    /**
     * Marks the task as done.
     *
     * @param index is the number identifying the task given by the user as seen from the task list
     */
    public void markDone(int index) {
        Task t = listOfTasks.get(index - 1);
        t.markAsDone();
    }

    /**
     * Marks the task as not done.
     *
     * @param index is the number identifying the task given by the user as seen from the task list
     */
    public void markNotDone(int index) {
        Task t = listOfTasks.get(index - 1);
        t.markAsNotDone();
    }

    /**
     * Prints tasks which contains the given keyword by the user.
     *
     * @param keywords is the keyword to be searched against the task descriptions
     */
    public void findTaskIndexWithKeyword(String ... keywords) {
        for (Task t : listOfTasks) {
            for (String wordToMatch : keywords) {
                if (t.hasKeyword(wordToMatch)) {
                    System.out.println((listOfTasks.indexOf(t) + 1) + ". " + t.toString());
                    break;
                }
            }
        }
    }

    /**
     * Checks if the index given is within range to be handled by commands.
     *
     * @param index is the number identifying the task given by the user as seen from the task list
     * @return true if the index is within range
     */
    public boolean isIndexWithinRange(int index) {
        if (index > 0 && index <= listOfTasks.size()) {
            return true;
        }
        return false;
    }

    /**
     * Prints the number of tasks in the task list.
     */
    public void printNumberOfTasks() {
        System.out.println("You now have " + listOfTasks.size() + " tasks in the list ૮꒰ˊᗜˋ* ꒱ა");
    }
}