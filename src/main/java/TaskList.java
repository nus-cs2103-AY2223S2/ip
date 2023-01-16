public class TaskList {
    private Task[] taskList = new Task[100];
    private static int index = -1;
    private final static String ADDTASKMSG = "Got it. Added this task, I have:";

    /**
     * Constructor for Task List.
     */
    public TaskList() {
        index++;
    }

    /**
     * Adds task to the task list String array.
     * @param task name of the task
     */
    public void addTask(Task task) {
        System.out.println(String.format("%s\n %s", ADDTASKMSG, task.toString()));
        this.taskList[index] = task;
        System.out.println(String.format("%d tasks in the list, you have now.", index + 1));
        index++;
    }

    /**
     * Marks a task by its index in the array of Tasks.
     * @param index index of interest to mark the task as done
     */
    public void markTask(String index) {
        int idx;
        try {
            idx = Integer.parseInt(index) - 1;
            taskList[idx].mark();
        } catch (Exception e) {
            System.out.println("Error index");
        }
    }

    /**
     * Unmarks a task by its index in the array of Tasks.
     * @param index index of interest to unmark the task as undone.
     */
    public void unmarkTask(String index) {
        int idx;
        try {
            idx = Integer.parseInt(index) - 1;
            taskList[idx].unmark();
        } catch (Exception e) {
            System.out.println("Error index");
        }
    }

    /**
     * Prints the names of tasks in the list in the top-down list format with numbered indexes
     * starting from 1 added in chronological order whereas returns empty list string if task list is empty.
     */
    public void listItems() {
        if (index == 0) {
            System.out.println("Empty, this list is !");
        } else {
            String out = "";
            for (int i = 0; i < index - 1; i++) {
                out += String.format("%d.%s\n", i + 1, taskList[i].toString());
            }
            System.out.println(out + String.format("%d.%s", index, taskList[index - 1].toString()));
        }
    }
}
