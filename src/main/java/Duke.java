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
        EVENT
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
            } else if (action.equalsIgnoreCase("delete")) {
                Task task = commandList.remove(Integer.parseInt(strArray[1]) - 1);
                System.out.println(HORIZONTAL_LINE + "\n" +
                        "Noted. I've removed this task:" + "\n" +
                        task + "\n" + "Now you have " + commandList.size() +
                        " tasks in the list." + "\n" + HORIZONTAL_LINE);
            } else {
                try {
                    TaskTypes type = getTaskType(action);
                    Task task = getTask(type, strArray);
                    commandList.add(task);
                    String numberOfTask = Integer.toString(commandList.size());
                    System.out.println(HORIZONTAL_LINE + "\n" + "Got it. I've added this task:" +
                            "\n" + task + "\n" + "Now you have " + numberOfTask +
                            " tasks in the list." + "\n" + HORIZONTAL_LINE);
                } catch (InvalidTaskTypeException | EmptyCommandException | InvalidTimeException e) {
                    System.out.println(HORIZONTAL_LINE + "\n" + e.getMessage() + "\n" + HORIZONTAL_LINE);
                }
            }
        }
    }
    public TaskTypes getTaskType(String action) throws InvalidTaskTypeException {
        if (action.equalsIgnoreCase("todo")) {
            return TaskTypes.TODO;
        } else if (action.equalsIgnoreCase("deadline")) {
            return TaskTypes.DEADLINE;
        } else if (action.equalsIgnoreCase("event")) {
            return TaskTypes.EVENT;
        } else {
            throw new InvalidTaskTypeException();
        }
    }

    public Task getTask(TaskTypes type, String[] strArray) throws InvalidTaskTypeException,
            EmptyCommandException, InvalidTimeException {
        String command;

        if (strArray.length < 2 || strArray[1].trim().equals("")) {
            throw new EmptyCommandException(strArray[0]);
        }

        if (type.equals(TaskTypes.TODO)) {
            command = strArray[1];
            return new ToDo(command);
        } else if (type.equals(TaskTypes.DEADLINE)) {
            String[] temp = strArray[1].split("/by", 2);
            if (temp.length < 2 || temp[1].trim().equals("")) {
                throw new InvalidTimeException();
            }
            command = temp[0];
            String deadline = temp[1];
            return new Deadline(command, deadline);
        } else if (type.equals(TaskTypes.EVENT)) {
            String[] temp = strArray[1].split("/from", 2);
            if (temp.length < 2 || temp[1].trim().equals("")) {
                throw new InvalidTimeException();
            }
            String[] temp2 = temp[1].split("/to", 2);
            if (temp2.length < 2 || temp2[1].trim().equals("")) {
                throw new InvalidTimeException();
            }
            command = temp[0];
            String start = temp2[0];
            String end = temp2[1];
            return new Event(command, start, end);
        } else {
            throw new InvalidTaskTypeException();
        }
    }
}
