import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Default welcome message and message border
        String border = "_______________________________\n";
        System.out.println(border + "Sup, Duke here.\nWhat do you want from me?\n" + border);

        // Initialise list of tasks
        ArrayList<Task> TaskList = new ArrayList<Task>();

        // while LoopEnd = true loop to accept user input
        boolean LoopEnd = false;
        while (LoopEnd == false) {
            Scanner UserScan = new Scanner(System.in);
            String UserInput = UserScan.nextLine();

            // switch case for future commands
            switch (UserInput) {

                // loop breaks, ending program if input is "bye"
                case ("bye"):
                    System.out.println(border + "Goodbye, then!\n" + border);
                    LoopEnd = true;
                    break;
                // Duke lists out all Task names in TaskList when input is "list"
                case ("list"):
                    String ToPrint = "";
                    for (int i = 0; i < TaskList.size(); i++) {
                        ToPrint += ((i + 1 + ". ") + TaskList.get(i).getName() + "\n");
                    }
                    System.out.println(border + "Here are your tasks:\n" + ToPrint + border);
                    break;
                // Duke repeats input by default
                default:
                    TaskList.add(new Task(UserInput));
                    System.out.println(border + "Task added: " + UserInput + "\n" + border);
            }
        }
    }
}
