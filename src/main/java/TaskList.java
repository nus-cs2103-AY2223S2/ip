public class TaskList {
    private Task[] tasks = new Task[100];
    private int taskCount = 0;

    /**
     * Adds Task to the List , and returns the success Message
     * @param input
     * @return
     */
    public String addTasks(String input,String formatSpace) {
        tasks[taskCount] = new Task(input);
        taskCount++;
        return formatSpace + tasks[taskCount - 1].getRepresentation();
    }

    /**
     *
     * @param input
     * @param formatSpace
     * @return return formatted task representation.
     */
    public String toggleTask(String input, String formatSpace) {
        String[] parseInput = input.trim().split(" ",2);
        int index =  Integer.parseInt(parseInput[1]) - 1;
         if (parseInput[0].toLowerCase().contains("unmark")) {
            tasks[index].unmark();
            return formatSpace + tasks[index].getRepresentation();
        } else if (parseInput[0].toLowerCase().contains("mark")) {
            tasks[index].mark();
            return formatSpace + tasks[index].getRepresentation();
        }else {
            // Uknown Command
            return "Command not Found.";
        }
    }

    /**
     * Retreives Tasks from the list and formats according to UI specifications.
     * @param formatSpace
     * @return formatted tasks from taskList
     */
    public String formatTasks(String formatSpace) {
        String  res = "";
        for (int i = 0; i < taskCount; i++){
                res +=  formatSpace + i + ". " + tasks[i].getRepresentation() + "\n";
        }
        return res;
    }

}
