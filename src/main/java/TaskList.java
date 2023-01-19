import java.util.ArrayList;

public class TaskList {

    static ArrayList<Task> taskList;

    // Level 1
    public TaskList() {
        taskList = new ArrayList<>();
    }

    static void addTask(String[] input) {
        String task = String.join(" ", input);
        taskList.add(new Task(task));
        System.out.println("added: " + task);
        Joe.printNewLine();
    }

    public static void returnList() {
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(String.format("%d. %s", i, taskList.get(i-1)));
        }
        Joe.printNewLine();
    }

    public static void mark(String index) {
        int i = Integer.parseInt(index) - 1;
        Task tempTask = taskList.get(i);
        tempTask.mark();
        String output =  String.format("\tNice! I've marked this task as done:\n\t  %s", tempTask);
        System.out.println(output);
    }

    public static void unmark(String index) {
        int i = Integer.parseInt(index) - 1;
        Task tempTask = taskList.get(i);
        tempTask.mark();
        String output =  String.format("\tNice! I've marked this task as not done yet:\n\t  %s", tempTask);
        System.out.println(output);
    }
}
