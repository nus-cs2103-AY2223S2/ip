import java.util.Scanner;

public class Duke {

    final static String LOGO = " ____        _        \n"
                            + "|  _ \\ _   _| | _____ \n"
                            + "| | | | | | | |/ / _ \\\n"
                            + "| |_| | |_| |   <  __/\n"
                            + "|____/ \\__,_|_|\\_\\___|\n";
    final static String WELCOME_MSG = "Greetings! JEDI GRANDMASTER YODA here\n" + "For you, What can I do?";
    final static String BANNER = "____________________________________________________________";
    final static String BYE_MSG = "Be Gone, You Must. May the Force be with You!";

    public static void main(String[] args) {
        Duke.displayWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        String response = "";

        TaskList taskList = new TaskList();
        TaskInfoParser parser = new TaskInfoParser();

        while (true) {
            response = scanner.nextLine();
            if (response.equals("bye")) {
                break;
            }
            String[] commands = response.split(" ");
            System.out.println(BANNER);
            switch(commands[0]) {
                case "list" :
                    taskList.listItems();
                    break;
                case "mark" :
                    taskList.markTask(commands[1]);
                    break;
                case "unmark" :
                    taskList.unmarkTask(commands[1]);
                    break;
                default :
                    Task newTask = parser.obtainTask(response);
                    taskList.addTask(newTask);
                    break;
            }
            System.out.println(BANNER);
        }
        Duke.respond(BYE_MSG, true);
    }


    /**
     * Displays the welcome message when launched.
     */
    public static void displayWelcomeMessage() {
        System.out.println(String.format("%s\n%s\n%s", BANNER, WELCOME_MSG, BANNER));
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

}
