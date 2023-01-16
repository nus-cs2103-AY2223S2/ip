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
    private List<String> commandList;

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
            if (userCommands.equalsIgnoreCase("bye")) {
                isOver = true;
                System.out.println(BYE_MESSAGE);
            } else if (userCommands.equalsIgnoreCase("list")) {
                for (int i = 1; i <= commandList.size(); i++) {
                    System.out.println(i + ". " + commandList.get(i - 1) + "\n");
                }
                System.out.println(HORIZONTAL_LINE + "\n");
            } else {
                    commandList.add(userCommands);
                    System.out.println(HORIZONTAL_LINE + "\n" + "added: " + userCommands + "\n" + HORIZONTAL_LINE);
            }
        }
    }
}
