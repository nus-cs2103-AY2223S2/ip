import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    public static final String GREETING_MESSAGE =
            HORIZONTAL_LINE + "\n" + "Hello! I'm Duke" +
                    "\n" + "What can I do for you?" + "\n" + HORIZONTAL_LINE;
    public static final String BYE_MESSAGE =
            HORIZONTAL_LINE + "\n" + "Bye. Hope to see you again soon!" +
                    "\n" + HORIZONTAL_LINE;
    private List<Task> commandList;
    public enum TaskTypes {
        TODO,
        DEADLINE,
        EVENT,
        OTHER
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public Duke() {
        commandList = new ArrayList<>();
    }
    public void run() {
        System.out.println(GREETING_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        boolean isOver = false;
        while (!isOver) {
            String userCommands = scanner.nextLine();
            String[] strArray = userCommands.split(" ", 2);
            String action = strArray[0];
            if (action.equalsIgnoreCase("bye")) {
                isOver = true;
                System.out.println(BYE_MESSAGE);
            } else if (action.equalsIgnoreCase("list")) {
                System.out.println(HORIZONTAL_LINE + "\n" +
                        "Here are the tasks in your list:");
                for (int i = 1; i <= commandList.size(); i++) {
                    System.out.println(i + "." + commandList.get(i - 1));
                }
                System.out.println(HORIZONTAL_LINE);
            } else if (action.equalsIgnoreCase("mark")) {
                Task task = commandList.get(Integer.parseInt(strArray[1]) - 1);
                task.mark();
                System.out.println(HORIZONTAL_LINE + "\n" +
                        "Nice! I've marked this task as done:" +
                        "\n" + task + "\n" + HORIZONTAL_LINE);
            } else if (action.equalsIgnoreCase("unmark")) {
                Task task = commandList.get(Integer.parseInt(strArray[1]) - 1);
                task.unmark();
                System.out.println(HORIZONTAL_LINE + "\n" +
                        "OK, I've marked this task as not done yet:" +
                        "\n" + task + "\n" + HORIZONTAL_LINE);
            } else {
                TaskTypes type = getTaskType(action);
                Task task = getTask(type, strArray);
                commandList.add(task);
                String numberOfTask = Integer.toString(commandList.size());
                System.out.println(HORIZONTAL_LINE + "\n" + "Got it. I've added this task:" +
                        "\n" + task + "\n" + "Now you have " + numberOfTask +
                        " tasks in the list." + "\n" + HORIZONTAL_LINE);
            }
        }
    }
    public TaskTypes getTaskType(String action){
        if (action.equalsIgnoreCase("todo")) {
            return TaskTypes.TODO;
        } else if (action.equalsIgnoreCase("deadline")) {
            return TaskTypes.DEADLINE;
        } else if (action.equalsIgnoreCase("event")) {
            return TaskTypes.EVENT;
        } else {
            return TaskTypes.OTHER;
        }
    }

    public Task getTask(TaskTypes type, String[] strArray) {
        String command;

        if (type.equals(TaskTypes.TODO)) {
            command = strArray[1];
            return new ToDo(command);
        } else if (type.equals(TaskTypes.DEADLINE)) {
            String[] temp = strArray[1].split("/by", 2);
            command = temp[0];
            String deadline = temp[1];
            return new Deadline(command, deadline);
        } else if (type.equals(TaskTypes.EVENT)) {
            String[] temp = strArray[1].split("/from", 2);
            String[] temp2 = temp[1].split("/to", 2);
            command = temp[0];
            String start = temp2[0];
            String end = temp2[1];
            return new Event(command, start, end);
        } else {
            command = strArray[0] + " " + strArray[1];
            return new Task(command);
        }
    }
}
