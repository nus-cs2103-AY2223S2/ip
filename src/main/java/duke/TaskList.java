package duke;

import java.util.ArrayList;

/**
 * Class that defines a list to store task objects
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Default constructor for TaskList object
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds specified task to TaskList
     *
     * @param currentTask Task to be added to TaskList
     */
    public void addTask(Task currentTask) {
        this.taskList.add(currentTask);
    }

    /**
     * Deletes the task at a specified position in TaskList
     *
     * @param taskPosition Position of the task to be deleted
     * @return deleted Task
     */
    public Task deleteTask(int taskPosition) throws IndexOutOfBoundsException {
            return this.taskList.remove(taskPosition);
    }

    /**
     *  Returns the task at a specified position in TaskList
     * @param pos Position of the task to be retrieved
     * @return Task object
     */
    public Task getTask(int pos) {
        return this.taskList.get(pos);
    }

    /**
     * Returns the number of Tasks in the TaskList
     *
     * @return number of Tasks in the TaskList
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Finds all tasks in tasklist that contains keyword in task title
     * @param keyword String of matching text to be found
     * @return TaskList containing all tasks in tasklist that contain keyword in task title
     */
    public TaskList findTasks(String keyword) {
        TaskList resultList = new TaskList();

        for (Task currTask : this.taskList) {
            if (currTask.title.contains(keyword)) {
                resultList.addTask(currTask);
            }
        }

        return resultList;
    }

    /**
     * Prints the tasks in taskList in an indexed manner
     * @return String that describes the tasks in the taskList and their completion status
     */
    public String printList() {
        int pos = 0;
        StringBuilder result = new StringBuilder();

        while (pos < this.taskList.size()) {
            result.append(pos + 1).append(". ").append(this.taskList.get(pos).toString()).append("\n");
            pos++;
        }

        return result.append("End of list!\n").toString();
    }
}
