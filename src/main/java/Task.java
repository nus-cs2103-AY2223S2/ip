import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;

    protected static ArrayList<Task> taskList = new ArrayList<>();

    protected static Integer listSize = 0; //or just increment accordingly

    public Integer index;

    //Constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.index = listSize + 1;
        //add to taskList
        taskList.add(this);
        listSize++;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public static String mark(Integer index, String markOption) {
        if (index > taskList.size() || index < 0) {
            return "No such item exists in list";
        }
        Task task = taskList.get(index - 1);
        if (markOption.equalsIgnoreCase("mark")) {
            task.markDone();
        } else if (markOption.equalsIgnoreCase("unmark")){
            task.markUndone();
        }
        return task.getTaskInline();
    }

    public static String delete(Integer index) {
        if (index > taskList.size() || index < 0 ) {
            return "No such item exists in list";
        }
        Task task = taskList.get(index - 1);
        Task.taskList.remove(index - 1);
        String deletedTaskDesc = task.getTaskInline();
        return deletedTaskDesc;
    }

    //Mark as done
    public void markDone() {

        this.isDone = true;
    }

    //Mark undone
    public void markUndone() {
        this.isDone = false;
    }

    //Get inline print of task description with specified index
    public String getTaskInline(Integer index) {

        return index.toString() + ". " + this.getTaskInline();
    }

    public String getTaskInline() {
        String statusIcon = this.getStatusIcon();
        return "[" + statusIcon + "] " + this.description;
    }

    public String toString() {
        return this.description;
    }

    /**Gets the size of the task list, in a String.
     *
     * @return String that details size of task list
     */
    public static String getTaskCount() {
        return "Now you have " + Task.listSize.toString() + " tasks in the list.";
    }

    /**
     * Prints all the Tasks in the list
     */
    public static void printList() {
        if (taskList.size() == 0) {
            System.out.println("Master, there seems to be nothing in your list! Goodness me! Has my circuitry failed me again?!");
        } else {
            System.out.println("Master, here are the items in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Integer val = i+1;
                String listInline = taskList.get(i).getTaskInline(val);

                System.out.println(listInline);
            }
        }

    }

}
