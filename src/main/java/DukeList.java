public class DukeList {
    private Task[] taskList;
    private int taskCount;
    DukeList() {
        taskCount = 0;
        taskList = new Task[100];
    }

    public void addTask(String input) throws Exception {
        String taskName = "";
        if (input.startsWith("todo ")){
            taskName = input.substring(5);
            taskList[taskCount] = new ToDoTask(taskName);
        } else if (input.startsWith("deadline ")) {
            int firstSlashIndex = input.indexOf("/");
            taskName = input.substring(9, firstSlashIndex - 1);
            String deadline =input.substring(firstSlashIndex);
            taskList[taskCount] = new DeadlineTask(taskName, deadline);
        } else if (input.startsWith("event ")) {
            int firstSlashIndex = input.indexOf("/");
            taskName = input.substring(6, firstSlashIndex - 1);
            String period =input.substring(firstSlashIndex);
            taskList[taskCount] = new EventTask(taskName, period);
        } else {
            throw new Exception("Invalid task type");
        }
        int count = taskCount + 1;
        System.out.println(DukeIO.wrapContent("Task added: \n\t\t" + taskList[taskCount] + "\n\tNow you have " + count + " tasks in the list."));
        taskCount++;
    }

    public void markTask(int taskIndex) throws Exception{
        if (taskIndex > taskCount || taskIndex < 0) {
            throw new Exception("Invalid task index specified");
        }
        taskList[taskIndex - 1].markTask();
    }

    public void unmarkTask(int taskIndex) throws Exception{
        if (taskIndex > taskCount || taskIndex < 0) {
            throw new Exception("Invalid task index specified");
        }
        taskList[taskIndex - 1].unmarkTask();
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Current tasks in list: ");
        for (int i=0; i < taskCount; i++) {
            result.append("\n\t").append(i + 1).append(". ").append(taskList[i]);
        }
        return result.toString();
    }
}
