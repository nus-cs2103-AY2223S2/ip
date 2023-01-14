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
    public static final String addWord = "This task is added to your list: ";

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

    public void handleToDo(String command) {
        // Later should catch empty todo
        String[] splited = command.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < splited.length; i++) {
            sb.append(splited[i]).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        String taskName = sb.toString();

        Task theTask = new ToDo(taskName);
        taskList.add(theTask);

        System.out.println(addWord);
        System.out.println(theTask.toString());
        System.out.println("Now you have " + taskList.size() +
                " tasks in the list.");
    }

    public void handleDeadLine(String command) {
        // Later should catch empty deadline
        String[] splited = command.split(" ");
        StringBuilder sb = new StringBuilder();
        StringBuilder time = new StringBuilder();
        boolean isTime = false;
        for (int i = 1; i < splited.length; i++) {
            if (splited[i].equals("/by")) {
                isTime = true;
            } else if (!isTime) {
                sb.append(splited[i]).append(" ");
            } else {
                time.append(splited[i]).append(" ");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        time.deleteCharAt(time.length()-1);
        String taskName = sb.toString();
        String deadline = time.toString();

        Task theTask = new DeadLine(taskName, deadline);
        taskList.add(theTask);

        System.out.println(addWord);
        System.out.println(theTask.toString());
        System.out.println("Now you have " + taskList.size() +
                " tasks in the list.");
    }

    public void handleEvent(String command) {
        // Later should catch empty event
        String[] splited = command.split(" ");
        StringBuilder sb = new StringBuilder();
        StringBuilder start = new StringBuilder();
        StringBuilder end = new StringBuilder();
        boolean isStart = false;
        boolean isEnd = false;
        for (int i = 1; i < splited.length; i++) {
            if (splited[i].equals("/from")) {
                isStart = true;
            } else if (splited[i].equals("/to")) {
                isStart = false;
                isEnd = true;
            } else if (!isStart && !isEnd) {
                sb.append(splited[i]).append(" ");
            } else if (isStart) {
                start.append(splited[i]).append(" ");
            } else {
                end.append(splited[i]).append(" ");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        start.deleteCharAt(start.length()-1);
        end.deleteCharAt(end.length()-1);
        String taskName = sb.toString();
        String startTime = start.toString();
        String endTime = end.toString();

        Task theTask = new Event(taskName, startTime, endTime);
        taskList.add(theTask);

        System.out.println(addWord);
        System.out.println(theTask.toString());
        System.out.println("Now you have " + taskList.size() +
                " tasks in the list.");
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
