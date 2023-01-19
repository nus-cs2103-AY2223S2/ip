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
        Task[] data = new Task[100];
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
                    reply.list(data,count);
                    break;
                case "mark":
                    reply.mark(data);
                    break;
                case "unmark":
                    reply.mark(data);
                    break;
                case "todo":
                    reply.todo(data,count);
                    count++;
                    break;
                case "event":
                    reply.event(data, count);
                    count++;
                    break;
                case "deadline":
                    reply.deadline(data, count);
                    count++;
                    break;
                default:
                    reply.def();
            }
        }
        sc.close();
    }
}
