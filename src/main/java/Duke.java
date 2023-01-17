import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static String LOGO = "______     ______     __     __    \n" +
            "/\\  __ \\   /\\  == \\   /\\ \\   /\\ \\   \n" +
            "\\ \\  __ \\  \\ \\  __<   \\ \\ \\  \\ \\ \\  \n" +
            " \\ \\_\\ \\_\\  \\ \\_\\ \\_\\  \\ \\_\\  \\ \\_\\ \n" +
            "  \\/_/\\/_/   \\/_/ /_/   \\/_/   \\/_/ \n";

    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Accepts a string that represents the user command, returns a boolean to
     * determine if the program should terminate immediately
     * @param cmd user command
     * @return true if program should exit
     */
    public boolean handleCommand(String cmd) {
        String firstCmd = cmd.split(" ")[0];

        switch(firstCmd) {
            case "bye":
                System.out.println("Till next time...");
                return true;

            case "list":
                System.out.println("Arii has retrieved your current tasks...");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, tasks.get(i));
                }
                break;

            case "mark":
                int markTaskIndex = Integer.parseInt(cmd.split(" ")[1]) - 1;
                tasks.get(markTaskIndex).setIsDone(true);

                System.out.println("This task is now done, what's next?");
                System.out.println(tasks.get(markTaskIndex));
                break;

            case "unmark":
                int unmarkTaskIndex = Integer.parseInt(cmd.split(" ")[1]) - 1;
                tasks.get(unmarkTaskIndex).setIsDone(false);

                System.out.println("This task is now not done, how disappointing...");
                System.out.println(tasks.get(unmarkTaskIndex));
                break;

            default:
                Task newTask = new Task(cmd);
                tasks.add(newTask);
                System.out.println("Hey new task added!");
                System.out.println(newTask);
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();

        System.out.println("Hello, I am \n" + Duke.LOGO);
        System.out.println("How shall I assist you today?");

        boolean toExit = false;
        while (!toExit) {
            System.out.print("\n:> ");
            String cmd = scanner.nextLine();
            toExit = duke.handleCommand(cmd);
        }
    }
}
