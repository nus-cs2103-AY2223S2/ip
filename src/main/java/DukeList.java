public class DukeList {
    private String[] taskList;
    private int taskCount;
    DukeList() {
        taskCount = 0;
        taskList = new String[100];
    }

    public void addTask(String task) {
        taskList[taskCount] = task;
        taskCount++;
        System.out.println(DukeIO.wrapContent("added: " + task));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i=0; i < taskCount; i++) {
            result.append(i + 1).append(". ").append(taskList[i]).append("\n");
        }
        return result.toString();
    }
}
