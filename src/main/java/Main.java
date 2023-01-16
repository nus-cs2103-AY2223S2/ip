import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Duke duke = new Duke();

        // greeting
        duke.print_structured_string(duke.greeting());

        // read input
        Scanner scanner = new Scanner(System.in);

        String inMsg = null;
        while(true) {
            inMsg = scanner.nextLine();
            if (duke.isEnd(inMsg)) {
                break;
            }

            switch (inMsg) {
                case "list":
                    duke.print_structured_string(duke.listTasksMsg());
                    break;
                default:
                    Task task = new Task(inMsg);
                    duke.addTask(task);
                    duke.print_structured_string(duke.addMsg(task));
            }
        }

        // bye-bye message
        duke.print_structured_string(duke.endMsg());
    }
}
