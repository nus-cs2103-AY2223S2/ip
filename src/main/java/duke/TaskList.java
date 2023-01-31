package duke;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> allTasks;

    /**
     * constructor of TaskList
     * Initialises allTasks too so that tasks can be stored
     */
    public TaskList(){
        allTasks =  new ArrayList<Task>();
    }

    /**
     * gets the number of tasks
     * @return the number of task in the allTasks
     */

    public int getNumberOfTask() {
        return allTasks.size();
    }

    /**
     * get the task of in the list and concat them into a string
     * @return the entire task in string form
     */
    public String printList() {
        String printedList = "";
        for (int i = 0; i < allTasks.size(); i++) {
            int index = i + 1;
            printedList = printedList.concat(index + ". "+ allTasks.get(i).toString()) + "\n";
        }

        if (allTasks.size() == 0) {
            return "Your list is currently empty, :)";
        }

        return printedList;
    }

    /**
     * adds a task into the list
     * @param task is a Task object to be added into the list
     */
    public void addTask(Task task) {
        allTasks.add(task);
    }

    /**
     * goes to the index where the task is and calls mark() to mark the task
     * @param index of task to be mark
     */
    public void markTask(int index) {
        allTasks.get(index - 1).mark();
    }

    /**
     * goes to the index where the task is and calls unmark() to unmark the task
     * @param index of task to be mark
     */
    public void unmarkTask(int index) {
        allTasks.get(index - 1).unmark();
    }

    /**
     * goes to the index and remove task
     * @param index of the task to be deleted
     */
    public void deleteTask(int index, Ui ui) {
        ui.printText(" " + allTasks.get(index-1).toString());
        allTasks.remove(index-1);
    }

    /**
     * get the allTasks variable
     * @return allTasks
     */
    public ArrayList<Task> getTasks() {
        return allTasks;
    }
}
