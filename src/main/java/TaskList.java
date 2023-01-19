import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {

    ArrayList<Task> taskList;

    // Level 1
    public TaskList() {
        taskList = new ArrayList<>();
    }

    static void parseResponse(String[] input) {
        for (int i = 0; i < input.length; i++) {

        }
    }

    void addTask(String[] input) {
        String taskType = input[0];
        input = Arrays.copyOfRange(input, 1, input.length);
        String task = String.join(" ", input);
        String[] tempArray;
        switch (taskType) {
            case "todo":
                taskList.add(new ToDo(task, "T"));
                break;
            case "deadline":
                tempArray = task.split(" /");
                String byTime = tempArray[1];
                byTime = byTime.substring(byTime.indexOf(" "), byTime.length());
                task = tempArray[0];
                taskList.add(new Deadline(tempArray[0], "D", byTime));
                break;
            case "event":
                tempArray = task.split(" /");
                String fromTime = tempArray[1];
                fromTime = fromTime.substring(fromTime.indexOf(" "), fromTime.length());
                String toTime = tempArray[2];
                toTime = toTime.substring(toTime.indexOf(" "), toTime.length());
                task = tempArray[0];
                taskList.add(new Event(tempArray[0], "E", fromTime, toTime));
                break;
        }
        System.out.println("added: " + task);
        printNewLine();
    }

    void returnList() {
        System.out.println("returning");
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(String.format("%d. %s", i, taskList.get(i-1)));
        }
        printNewLine();
    }

    void mark(String index) {
        int i = Integer.parseInt(index) - 1;
        Task tempTask = taskList.get(i);
        tempTask.mark();
        String output =  String.format("\tNice! I've marked this task as done:\n\t  %s", tempTask);
        System.out.println(output);
    }

    void unmark(String index) {
        int i = Integer.parseInt(index) - 1;
        Task tempTask = taskList.get(i);
        tempTask.unmark();
        String output =  String.format("\tNice! I've marked this task as not done yet:\n\t  %s", tempTask);
        System.out.println(output);
    }

    public static void printNewLine() {
        String newline = "\t____________________________________________________________";
        System.out.println(newline);
    }
}
