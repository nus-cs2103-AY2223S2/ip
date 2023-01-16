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
            String[] temp = userCommands.split(" ", 2);
            String action = temp[0];
            if (action.equalsIgnoreCase("bye")) {
                isOver = true;
                System.out.println(BYE_MESSAGE);
            } else if (action.equalsIgnoreCase("list")) {
                System.out.println(HORIZONTAL_LINE + "\n" +
                        "Here are the tasks in your list:");
                for (int i = 1; i <= commandList.size(); i++) {
                    System.out.println(i + "." + commandList.get(i - 1));
                }
                System.out.println(HORIZONTAL_LINE + "\n");
            } else if (action.equalsIgnoreCase("mark")) {
                Task task = commandList.get(Integer.parseInt(temp[1]) - 1);
                task.mark();
                System.out.println(HORIZONTAL_LINE + "\n" +
                        "Nice! I've marked this task as done:" +
                        "\n" + task);
            } else if (action.equalsIgnoreCase("unmark")) {
                Task task = commandList.get(Integer.parseInt(temp[1]) - 1);
                task.unmark();
                System.out.println(HORIZONTAL_LINE + "\n" +
                        "OK, I've marked this task as not done yet:" +
                        "\n" + task);
            } else {
                Task task = new Task(userCommands);
                commandList.add(task);
                System.out.println(HORIZONTAL_LINE + "\n" + "added: " + userCommands + "\n" + HORIZONTAL_LINE);
            }
        }
    }
}
