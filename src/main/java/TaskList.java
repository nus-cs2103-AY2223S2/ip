public class TaskList {
    private String[] taskList = new String[100];
    private static int index = -1;

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
    public void addTask(String task) {
        this.taskList[index] = task;
        index++;
    }

    /**
     * Returns names of tasks in the list in the top-down list format with numbered indexes
     * starting from 1 added in chronological order whereas returns empty list string if task list is empty.
     * @return string representation of the task list containing name of tasks.
     */
    @Override
    public String toString() {
        if (index == 0) {
            return "Empty, this list is !";
        } else {
            String out = "";
            for (int i = 0; i < index - 1; i++) {
                out += String.format("%d. %s\n", i + 1, taskList[i]);
            }
            return out + String.format("%d. %s", index, taskList[index - 1]);
        }
    }
}
