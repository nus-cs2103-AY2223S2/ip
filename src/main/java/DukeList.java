public class DukeList {
    private Task[] taskList;
    private int taskCount;
    DukeList() {
        taskCount = 0;
        taskList = new Task[100];
    }

    public void addTask(String task) {
        taskList[taskCount] = new Task(task);
        taskCount++;
        System.out.println(DukeIO.wrapContent("added: " + task));
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
            result.append("\n").append(i + 1).append(". ").append(taskList[i].stringWithStatus());
        }
        return result.toString();
    }
}
