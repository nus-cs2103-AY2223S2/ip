import java.util.Scanner;

public class Duke {
    
    final static String LOGO = " ____        _        \n"
                            + "|  _ \\ _   _| | _____ \n"
                            + "| | | | | | | |/ / _ \\\n"
                            + "| |_| | |_| |   <  __/\n"
                            + "|____/ \\__,_|_|\\_\\___|\n";
    final static String WELCOME_MSG = "Greetings!\n" + LOGO + "this is\n" + "For you, What can I do?";
    final static String BANNER = "____________________________________________________________";
    final static String BYE_MSG = "Be Gone, You Must. May the Force be with You!";

    public static void main(String[] args) {
        Duke.displayWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        String response = "";

        TaskList taskList = new TaskList();

        while (!response.equals("bye")) {
            response = scanner.nextLine();
            switch (response) {
                case "list":
                    Duke.respond(taskList);
                    break;
                case "bye":
                    break;
                default:
                    Duke.respond(taskList, response);
                    break;
            }
        }
        Duke.respond(BYE_MSG, true);
    }

    /**
     * Displays the welcome message when launched.
     */
    public static void displayWelcomeMessage() {
        System.out.println(String.format("%s\n%s\n%s\n", BANNER, WELCOME_MSG, BANNER));
    }

    /**
     * Responds to the command given by standard input with the appropriate formatting.
     * @param command the input that is retrieved from standard input
     * @param nextLine provides necessary indent should requested
     */
    public static void respond(String command, boolean nextLine) {
        String answer = "";
        if (nextLine) {
            answer += BANNER;
        }
        answer += "\n" + command + "\n" + BANNER;
        System.out.println(answer);
    }

    /**
     * Prints the string representation of the task list added in chronological order.
     * @param taskList the list of names of tasks
     */
    public static void respond(TaskList taskList) {
        System.out.println(String.format("%s\n%s\n%s\n", BANNER, taskList, BANNER));
    }

    /**
     * Adds the task into task list and prints the added task message.
     * @param taskList list of tasks
     * @param taskName name of newly-added task
     */
    public static void respond(TaskList taskList, String taskName) {
        taskList.addTask(taskName);
        System.out.println(String.format("%s\nadded: %s\n%s\n", BANNER, taskName, BANNER));
    }
}
