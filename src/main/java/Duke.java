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
        List<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Storage st = new Storage("duke.txt");
        while (sc.hasNext()) {
            String echo = sc.nextLine();
            String[] command = echo.split(" ");
            Reply reply = new Reply(command);

            switch (command[0]) {
            case "bye":
                reply.bye();
                return;
            case "list":
                reply.list(tasks);
                break;
            case "mark":
                reply.mark(tasks);
                st.write(tasks);
                break;
            case "unmark":
                reply.mark(tasks);
                st.write(tasks);
                break;
            case "todo":
                reply.todo(tasks);
                break;
            case "event":
                reply.event(tasks);
                break;
            case "deadline":
                reply.deadline(tasks);
                break;
            case "delete":
                reply.delete(tasks);
                break;
            default:
                reply.def();
            }
        }
        sc.close();
    }
}
