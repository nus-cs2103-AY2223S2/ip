public class Task {
    public static final String HORIZONTAL_LINE = "    ____________________________________________________________";

    public String description;
    public int id;
    public boolean isDone;
    public static int totalNumberOfTask = 0;

    public Task(String description) {
        this.description = description;
        totalNumberOfTask++;
        this.id = totalNumberOfTask;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
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
            System.out.println("     " + taskList[i].id + ". " + taskList[i].description);
        }
        Task.printHorizontalLine();
    }

    public void printMark() {
        Task.printHorizontalLine();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + this);
        Task.printHorizontalLine();
    }

    public void printUnmark() {
        Task.printHorizontalLine();
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + this);
        Task.printHorizontalLine();
    }

    public void printUpdatedTask() {
        printHorizontalLine();
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + this);
        System.out.println("     Now you have " + Task.getTotalNumberOfTask() + " tasks in the list.");
        printHorizontalLine();
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

}
