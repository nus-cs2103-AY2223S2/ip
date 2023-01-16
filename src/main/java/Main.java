import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Duke duke = new Duke();

        // greeting
        duke.print_structured_string(duke.greeting());

        // read input
        Scanner scanner = new Scanner(System.in);

        // main loop
        String inMsg = null;
        while (true) {
            inMsg = scanner.nextLine();
            if (duke.isEnd(inMsg)) {
                break;
            }

            if (inMsg.equals("list")) {
                duke.print_structured_string(duke.listTasksMsg());
            } else if (inMsg.length() > 4 && inMsg.substring(0, 4).equals("mark")) {
                // String taskName = inMsg.substring(5);
                // duke.markTaskDone(taskName);
                int idx = Integer.parseInt(inMsg.substring(5)) - 1;
                duke.print_structured_string(duke.markTaskDone(idx));
            } else if (inMsg.length() > 6 && inMsg.substring(0, 6).equals("unmark")) {
                // String taskName = inMsg.substring(7);
                // duke.unmarkTaskDone(taskName);
                int idx = Integer.parseInt(inMsg.substring(7)) - 1;
                duke.print_structured_string(duke.unmarkTaskDone(idx));
            } else {
                Task task = new Task(inMsg);
                duke.addTask(task);
                duke.print_structured_string(duke.addMsg(task));
            }
        }

        // bye-bye message
        duke.print_structured_string(duke.endMsg());
    }
}
