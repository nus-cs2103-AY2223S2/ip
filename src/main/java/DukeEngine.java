import java.util.*;

public class DukeEngine {

    public static final String divisionLine = "________________________________________";
    public static final String logo =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String greetWord = "It's a pleasure to serve you!";
    public static final String byeWord = "Goodbye. Hope you have a nice day!";
    public static final String markDoneWord =
            "Nice! You have completed this task: ";
    public static final String markUnDoneWord =
            "Well, you have not finished this task yet: ";
    public static final String listWord = "Here are all of your tasks: ";

    public List<Task> taskList = new ArrayList<Task>();

    public void greet() {
        // String divisionLine = "________________________________________";
        System.out.println(divisionLine);
        System.out.println("Hello from\n" + logo);
        System.out.println(greetWord);
        System.out.println(divisionLine);
    }

    public void echo(String command) {
        System.out.println(divisionLine);
        System.out.println(command);
        System.out.println(divisionLine);
    }

    public void addTask(String command) {
        Task theTask = new Task(command);
        taskList.add(theTask);
        System.out.println("added: " + command);
    }

    public void listTask() {
        StringBuilder sb = new StringBuilder();
        sb.append(listWord).append("\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(i+1).append(". ");
            sb.append(taskList.get(i).toString()).append("\n");
        }
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        System.out.println(sb.toString());
    }

    public void markDone(int index) {
        // Here should later handle the exception of out of bounds
        Task theTask = taskList.get(index - 1);
        theTask.setDone();

        StringBuilder sb = new StringBuilder();
        sb.append(markDoneWord).append("\n").append(" ");
        sb.append(theTask.toString());
        System.out.println(sb.toString());
    }

    public void markUnDone(int index) {
        // Here should later handle the exception of out of bounds
        Task theTask = taskList.get(index - 1);
        theTask.setUnDone();

        StringBuilder sb = new StringBuilder();
        sb.append(markUnDoneWord).append("\n").append(" ");
        sb.append(theTask.toString());
        System.out.println(sb.toString());
    }

    public void goodbye() {
        System.out.println(divisionLine);
        System.out.println(byeWord);
        System.out.println(divisionLine);
    }

    public void printLine() {
        System.out.println(divisionLine);
    }

}
