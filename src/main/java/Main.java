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
            } else if (checkCommand(inMsg,"mark")) {
                int idx = Integer.parseInt(inMsg.substring(5)) - 1;
                duke.print_structured_string(duke.markTaskDone(idx));
            } else if (checkCommand(inMsg,"unmark")) {
                int idx = Integer.parseInt(inMsg.substring(7)) - 1;
                duke.print_structured_string(duke.unmarkTaskDone(idx));
            } else if (checkCommand(inMsg, "todo")){
                String todoName = getCommandContent(inMsg, "todo");
                ToDo todo = new ToDo(todoName);
                duke.print_structured_string(duke.addTask(todo));
            } else if (checkCommand(inMsg, "deadline")) {
                String deadlineContent = getCommandContent(inMsg, "deadline");
                Deadline ddl = new Deadline(deadlineContent);
                duke.print_structured_string(duke.addTask(ddl));
            } else if (checkCommand(inMsg, "event")) {
                String eventContent = getCommandContent(inMsg, "event");
                Event event = new Event(eventContent);
                duke.print_structured_string(duke.addTask(event));
            }
        }

        // bye-bye message
        duke.print_structured_string(duke.endMsg());
    }

    public static boolean checkCommand(String s, String command) {
        return s.startsWith(command);
    }

    public static String getCommandContent(String s, String command) {
        return s.substring(s.indexOf(command) + command.length() + " ".length());
    }
}
