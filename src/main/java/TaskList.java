import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> taskList;

    private Integer taskCount = 0;

    /**
     * Creates new instance of Task List, with non-empty input.
     * @param taskListInput
     */
    public TaskList(ArrayList<Task> taskListInput) {
        this.taskList = new ArrayList<>();
        this.taskList.addAll(taskListInput);
        this.taskCount = taskList.size();
    }

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Decreases count of tasks
     */
    private void decrementTaskCount() {
        taskCount -= 1;
    }

    /**
     * Adds task to the taskList
     * @param task is a task object
     */
    public void addItem(Task task) {
        taskList.add(task);
        taskCount += 1;
    }


    /**
     * Returns item at specified position
     * @param index is position specified - 1
     * @return
     */
    public Task getItem(Integer index) {
        return taskList.get(index - 1);
    }

    /**
     * Deletes an item from the task list with actual index = paramindex - 1
     * @param pos an integer >= 1
     * @return
     */
    public boolean deleteItem(Integer pos) {
        int index = pos - 1;
        if (index > taskList.size() || index < 0 ) {
            return false;
        }
        taskList.remove(index);
        decrementTaskCount();
        return true;
    }


    /**
     * Returns String of all tasks, line-by-line formatted
     * for writing to file
     * @return
     */
    public String getListFileFormat() {
        StringBuilder build = new StringBuilder();
        for (Task task: taskList) {
            build.append(task.getTaskFileFormat());
            build.append("\n");
        }
        return build.toString();
    }

    /**
     * Marks the task as done or undone
     * @param index specifies which task to toggle mark
     * @param markOption specifies to unmark or mark task
     * @return
     */
    public boolean mark(Integer index, String markOption) {
        if (index > taskList.size() || index < 0) {
            return false;
        }
        Task task = taskList.get(index - 1);
        if (markOption.equalsIgnoreCase("mark")) {
            task.markDone();
        } else if (markOption.equalsIgnoreCase("unmark")){
            task.markUndone();
        }
        return true;
    }



    /**Gets the size of the task list, in a String.
     * @return String that details size of task list
     */
    public String getTaskCount() {
        return "Now you have " + taskCount.toString() + " tasks in the list.";
    }

    /**
     * Prints all the Tasks in the list
     */
    public void printList() {
        if (taskList.size() == 0) {
            Ui.showNothingInList();
        } else {
            Ui.showListPre();
            int i = 1;
            for (Task task: taskList) {
                String listInline = task.getTaskInline(i);
                i++;
                System.out.println(listInline);
            }
        }
    }
}
