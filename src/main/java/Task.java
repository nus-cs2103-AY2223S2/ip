public class Task {
    public static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    public String description;
    public int id;
    public static int totalNumberOfTask = 0;

    public Task(String description) {
        this.description = description;
        totalNumberOfTask ++;
        this.id = totalNumberOfTask;
    }

    public static int getTotalNumberOfTask() {
        return totalNumberOfTask;
    }

    public static void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printTaskList(Task[] taskList) {
        Task.printHorizontalLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < Task.getTotalNumberOfTask(); i++) {
            System.out.println("     " + taskList[i].id + " " + taskList[i]);
        }
        Task.printHorizontalLine();
    }

}
