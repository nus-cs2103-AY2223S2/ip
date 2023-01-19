import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        chat();
    }

    private static void chat() {
        int count = 0;
        List<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String echo = sc.nextLine();
            String[] command = echo.split(" ");
            Reply reply = new Reply(command);

            switch (command[0]) {
                case "bye":
                    reply.bye();
                    return;
                case "list":
                    reply.list(tasks,count);
                    break;
                case "mark":
                    reply.mark(tasks);
                    break;
                case "unmark":
                    reply.mark(tasks);
                    break;
                case "todo":
                    reply.todo(tasks,count);
                    count++;
                    break;
                case "event":
                    reply.event(tasks, count);
                    count++;
                    break;
                case "deadline":
                    reply.deadline(tasks, count);
                    count++;
                    break;
                case "delete":
                    reply.delete(tasks, count);
                    count--;
                    break;
                default:
                    reply.def();
            }
        }
        sc.close();
    }
}
