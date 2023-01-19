import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        System.out.println("Hewwo?!?1 I'm Duke\nWhat c-can I do fow you?!?!");
        System.out.println(line);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        ArrayList<String> tasks = new ArrayList<String>();
        while (running) {
            String input = scanner.nextLine();
            switch (input) {
                case "list":
                    System.out.println(line);
                    for (int i = 0; i < tasks.size(); i++) {
                        int current = i + 1;
                        System.out.println(current + ". " + tasks.get(i));
                    }
                    System.out.println(line);
                    break;
                case "bye":
                    System.out.println(line);
                    System.out.println("bye ;;w;; see you again soon :3");
                    System.out.println(line);
                    running = false;
                    break;
                default:
                    System.out.println(line);
                    tasks.add(input);
                    System.out.println("added: " + input);
                    System.out.println(line);
            }
        }

    }
}
