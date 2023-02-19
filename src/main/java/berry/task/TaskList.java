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
    public String addTask(Task task) {
        assert task != null : "Task should not be null";
        listOfTasks.add(task);
        return task.toString();
    }

    /**
     * Deletes an existing task.
     *
     * @param index is the number identifying the task given by the user as seen from the task list
     */
    public String deleteTask(int index) {
        assert index > 0 : "Task index should be bigger than 0";
        String output = "\t" + listOfTasks.get(index - 1).toString();
        listOfTasks.remove(index - 1);
        output += "\n" + getNumberOfTasks();
        return output;
    }

    /**
     * Marks the task as done.
     *
     * @param index is the number identifying the task given by the user as seen from the task list
     */
    public String markTaskDone(int index) {
        assert index > 0 : "Task index should be bigger than 0";
        Task t = listOfTasks.get(index - 1);
        return t.markTaskAsDone();
    }

    /**
     * Marks the task as not done.
     *
     * @param index is the number identifying the task given by the user as seen from the task list
     */
    public String markTaskNotDone(int index) {
        assert index > 0 : "Task index should be bigger than 0";
        Task t = listOfTasks.get(index - 1);
        return t.markTaskAsNotDone();
    }

    /**
     * Prints tasks which contains the given keyword by the user.
     *
     * @param keywords is the keyword to be searched against the task descriptions
     */

    public String findTaskIndexWithKeyword(String ... keywords) {
        String listTasks = "";
        for (Task t : listOfTasks) {
            for (String wordToMatch : keywords) {
                if (t.hasKeyword(wordToMatch)) {
                    listTasks += (listOfTasks.indexOf(t) + 1) + ". " + t.toString() + "\n";
                    break;
                }
            }
        }
        return listTasks;
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
    public String getNumberOfTasks() {
        return "You now have " + listOfTasks.size() + " task(s) in the list";
    }
}
