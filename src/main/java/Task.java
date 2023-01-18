public class Task {
    public static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    public String description;
    public static int totalNumberOfTask = 0;

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
            System.out.println("     " + (i+1) + " " + taskList[i]);
        }
        Task.printHorizontalLine();
    }

}
