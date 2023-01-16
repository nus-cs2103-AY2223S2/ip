import java.lang.StringBuilder;

public class TaskManager {
    private static int size = 100;
    private int counter = 0;
    private String[] data = new String[TaskManager.size];
    private static String starting = "    ____________________________________________________________\n";
    private static String ending = "    ____________________________________________________________\n";
    private static String spacing = "     ";

    public TaskManager() {}

    public String addTask(String task) {
        this.data[this.counter] = task;
        this.counter++;
        String s = TaskManager.starting + TaskManager.spacing + "added: " + task + "\n" + TaskManager.ending;
        return s.toString();
    }

    public String getAllTaskStr() {
        StringBuilder s = new StringBuilder();
        s.append(TaskManager.starting);
        for (int i = 0; i < this.counter; i++) {
            s.append(TaskManager.spacing).append(String.format("%d. ", i+1)).append(this.data[i]).append("\n");
        }
        s.append(TaskManager.ending);
        return s.toString();
    }
}
