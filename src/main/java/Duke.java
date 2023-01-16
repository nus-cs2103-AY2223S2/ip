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
                        ToPrint += ((i + 1 + ". ") + TaskList.get(i).toString() + "\n");
                    }
                    System.out.println(border + "Here are your tasks:\n" + ToPrint + border);
                    break;

                // Duke allows user to mark tasks as done when input is "mark"
                case ("mark"):
                    System.out.println(border + "You want to mark a task as done?\nWhich one?\n" + border);
                    Scanner MarkScan = new Scanner(System.in);
                    int MarkInput = MarkScan.nextInt() - 1;
                    TaskList.get(MarkInput).MarkDone();
                    System.out.println(border + "Okay, the following task is marked as done!\n");
                    System.out.println((MarkInput+1 + ". ") + TaskList.get(MarkInput).toString() + "\n" + border);
                    break;
                // Duke allows user to mark tasks as NOT done when input is "unmark"
                case ("unmark"):
                    System.out.println(border + "You want to mark a task as NOT done?\nWhich one?\n" + border);
                    Scanner UnmarkScan = new Scanner(System.in);
                    int UnmarkInput = UnmarkScan.nextInt() - 1;
                    TaskList.get(UnmarkInput).MarkNotDone();
                    System.out.println(border + "Okay, the following task is marked as NOT done!\n");
                    System.out.println((UnmarkInput+1 + ". ") + TaskList.get(UnmarkInput).toString() + "\n" + border);
                    break;
                // Duke adds task by default
                default:
                    TaskList.add(new Task(UserInput));
                    System.out.println(border + "Task added: " + UserInput + "\n" + border);
            }
        }
    }
}
