import java.util.*;

public class Duke {
    private static boolean isRunning = true;
    private static List<String> tasks = new ArrayList<String>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";

        String separator = "____________________________________________________________";
        String duke = "Duke: ";
        String you = "You: ";
        Scanner sc = new Scanner(System.in);

        System.out.println(logo + "\n");
        System.out.println(separator);
        System.out.println(duke);
        System.out.println("Hello! I'm DUKE!\nWhat can I do for you?");
        System.out.println(separator);

        while (isRunning) {
            System.out.println(you);
            String command = sc.nextLine();
            System.out.println(separator);
            System.out.println(duke);
            switch (command) {
                case "bye":
                    isRunning = false;
                    System.out.println("Bye! Hope to see you again soon!");
                    break;
                case "list":
                    if (!tasks.isEmpty()) {
                        for (int i = 1; i <= tasks.size(); i++) {
                            System.out.println(String.format("%d. %s", i, tasks.get(i - 1)));
                        }
                    } else {
                        System.out.println("You currently have no items in your to-do list!");
                    }
                    break;
                default:
                    tasks.add(command);
                    System.out.println(String.format("Added: %s", command));
                    break;
            }
            System.out.println(separator);
        }
    }
}
