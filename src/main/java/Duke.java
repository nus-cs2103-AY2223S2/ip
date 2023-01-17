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
        String border = "_________________________________________\n";
        System.out.println(border + "Sup, Duke here.\nWhat do you want from me?\n" + border);

        // Initialise list of tasks
        ArrayList<Task> TaskList = new ArrayList<>();

        // while LoopEnd = true loop to accept user input
        boolean LoopEnd = false;
        while (!LoopEnd) {
            Scanner UserScan = new Scanner(System.in);
            // switch case for future commands
            switch (UserScan.next()) {

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
                    int MarkInput = UserScan.nextInt() - 1;
                    TaskList.get(MarkInput).MarkDone();
                    System.out.println(border + "Okay, the following task is marked as done!\n");
                    System.out.println((MarkInput+1 + ". ") + TaskList.get(MarkInput).toString() + "\n" + border);
                    break;
                // Duke allows user to mark tasks as NOT done when input is "unmark"
                case ("unmark"):
                    int UnmarkInput = UserScan.nextInt() - 1;
                    TaskList.get(UnmarkInput).MarkNotDone();
                    System.out.println(border + "Okay, the following task is marked as NOT done!\n");
                    System.out.println((UnmarkInput+1 + ". ") + TaskList.get(UnmarkInput).toString() + "\n" + border);
                    break;

                // Duke adds Deadline
                case ("deadline"):
                    String DeadlineSentence = UserScan.nextLine();
                    String DeadlineName = DeadlineSentence.substring(0, DeadlineSentence.indexOf(" /by"));
                    String DeadlineDate = DeadlineSentence.substring(DeadlineSentence.indexOf(" /by")+5);
                    Task DeadlineToAdd = new Deadline(DeadlineName, DeadlineDate);
                    TaskList.add(DeadlineToAdd);
                    System.out.println(border + "Task added:\n " + DeadlineToAdd + "\n"
                            + "There are now " + TaskList.size() + " task(s) in your list.\n" + border);
                    break;
                // Duke adds Event
                case ("event"):
                    String EventSentence = UserScan.nextLine();
                    String EventName = EventSentence.substring(0, EventSentence.indexOf(" /from"));
                    String FromDate = EventSentence.substring(EventSentence.indexOf(" /from")+7, EventSentence.indexOf(" /to"));
                    String ToDate = EventSentence.substring(EventSentence.indexOf(" /to")+5);
                    Task EventToAdd = new Event(EventName, FromDate, ToDate);
                    TaskList.add(EventToAdd);
                    System.out.println(border + "Task added:\n " + EventToAdd + "\n"
                            + "There are now " + TaskList.size() + " task(s) in your list.\n"+ border);
                    break;
                // Duke adds To-Do
                case ("todo"):
                    String ToDoName = UserScan.nextLine();
                    Task TaskToAdd = new ToDo(ToDoName);
                    TaskList.add(new ToDo(ToDoName));
                    System.out.println(border + "Task added:\n " + TaskToAdd + "\n"
                            + "There are now " + TaskList.size() + " task(s) in your list.\n"+ border);
                    break;

                // Duke does not understand any other commands (yet).
                default:
                    System.out.println(border + "Yeah, i'm sorry. I don't understand that.\n" + border);
            }
        }
    }
}
