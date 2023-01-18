public class TaskList {
    private Task[] tasks = new Task[100];
    private int taskCount = 0;

    /**
     * Adds Task to the List , and returns the success Message
     * @param input
     * @return
     */
    public String addTasks(String input) {
        tasks[taskCount] = new Task(input);
        taskCount++;
        return "added: " + input;
    }

    /**
     * Retreives Tasks from the list and formats according to UI specifications.
     * @param formatSpace
     * @return
     */
    public String formatTasks(String formatSpace) {
        String  res = "";
        for (int i = 0; i < taskCount; i++){
            if (i == 0) {
                res +=  i + ". " + tasks[i].getMessage() + "\n";
            } else {
                res +=  formatSpace + i + ". " + tasks[i].getMessage() + "\n";
            }
        }
        return res;
    }

}
