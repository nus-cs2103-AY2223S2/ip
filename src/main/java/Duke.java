import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static final String AUTHOR = "lhy-hoyin";
    static final String LOGO
            = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";


    static void display(Object obj) {
        System.out.println(obj);
    }
    static void display(String message) {
        System.out.println(message);
    }
    static void warn(String message) {
        System.out.println("OOPS! " + message);
    }
    static void displayLogo() {
        Duke.display(LOGO);
    }
    static void displayLine() {
        Duke.display("____________________________________________________________");
    }
    static void displayTaskCount(ArrayList<Task> taskList) {
        if (taskList == null)
            return;

        if (taskList.isEmpty())
            Duke.display("You do not have any task!");
        else
            Duke.display("Now you have " + taskList.size() + " task(s) in the list.");
    }
    static void displayTaskList(ArrayList<Task> taskList) {
        if (taskList == null)
            return;

        if (taskList.size() == 0)
            Duke.display("Your list is empty.");
        else {
            Duke.display("Here's your list of tasks:");
            for (int i = 0; i < taskList.size(); i++)
                Duke.display((i + 1) + ". " + taskList.get(i));
        }
    }
    static void addNewTask(ArrayList<Task> taskList, Task task) {
        taskList.add(task);
        Duke.display("Got it. I've added this task:");
        Duke.display("\t" + task.toString());
        Duke.displayTaskCount(taskList);
    }
    static State detectState(String command) {
        // Suppress all upper case letters, gets only the first word
        String cmd = command.toLowerCase().split(" ")[0];

        if (cmd.compareTo("list") == 0)
            return State.LIST;
        else if (cmd.compareTo("todo") == 0)
            return State.TODO;
        else if (cmd.compareTo("deadline") == 0)
            return State.DEADLINE;
        else if (cmd.compareTo("event") == 0)
            return State.EVENT;
        else if (cmd.compareTo("mark") == 0)
            return State.MARK;
        else if (cmd.compareTo("unmark") == 0)
            return State.UNMARK;
        // TODO: Detect other specific keywords

        // multiple exit keywords
        switch (cmd) {
            case "bye":
            case "goodbye":
            case "quit":
            case "quit()":
            case "exit":
            case "exit()":
                return State.EXIT;
            default:
                return State.UNKNOWN;
        }
    }

    public static void main(String[] args) {

        Duke.displayLogo();
        Duke.display("Developed by: " + AUTHOR);
        System.out.println("Initialising system . . .");

        // TODO: Initialise components, variables
        int taskIdx, descIdx;
        Task activeTask;
        String userCmd, item;
        State currentState = State.UNKNOWN;
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();


        System.out.println("System is ready!");
        Duke.display("\n\n");
        Duke.displayLine();

        // Program Intro
        Duke.display("Hello! I'm Duke! :D");
        Duke.display("What can I do for you today?");

        // Program Loop
        while(currentState != State.EXIT) {
            System.out.print("\n > ");
            userCmd = sc.nextLine();

            // Command detection
            currentState = Duke.detectState(userCmd);

            // State handling
            switch(currentState) {
                case TODO:
                    item = userCmd.substring(4).trim(); // exclude "todo" keyword
                    activeTask = new Todo(item);
                    Duke.addNewTask(taskList, activeTask);
                    break;
                case DEADLINE:
                    if (!userCmd.contains(" /by ")) {
                        Duke.warn("Missing due date!");
                        break;
                    }
                    descIdx = userCmd.indexOf("deadline "); // 9
                    int dueIdx = userCmd.indexOf(" /by "); // 5
                    item = userCmd.substring(descIdx + 9, dueIdx);
                    String duedate = userCmd.substring(dueIdx + 5);
                    Duke.addNewTask(taskList, new Deadline(item, duedate));
                    break;
                case EVENT:
                    if (!userCmd.contains(" /from ")) {
                        Duke.warn("Missing start date/time!");
                        break;
                    }
                    else if (!userCmd.contains(" /to ")) {
                        Duke.warn("Missing end date/time!");
                        break;
                    }
                    descIdx = userCmd.indexOf("event "); // 6
                    int fromIdx = userCmd.indexOf(" /from "); // 7
                    int toIdx = userCmd.indexOf(" /to "); // 5
                    item = userCmd.substring(descIdx + 6, fromIdx);
                    String start = userCmd.substring(fromIdx + 7, toIdx);
                    String end = userCmd.substring(toIdx + 5);
                    Duke.addNewTask(taskList, new Event(item, start, end));
                    break;
                case LIST:
                    Duke.displayTaskList(taskList);
                    break;
                case MARK:
                case UNMARK:
                    taskIdx = Integer.parseInt(userCmd.split(" ")[1]) - 1;
                    // FIXME: throws NumberFormatException for "mark  1" (typo of additional space)
                    // FIXME: watch for index out of bound exception (no number given)
                    activeTask = taskList.get(taskIdx);
                    // FIXME: watch for index out of bound exception (ie. index of non-existing task)
                    activeTask.setDone(currentState == State.MARK); // False means unmark
                    if (currentState == State.MARK)
                        Duke.display("Nice I've marked this task as done:");
                    else Duke.display("OK, I've marked this task as not done yet:");
                    Duke.display(activeTask);
                    break;
                case UNKNOWN:
                    Duke.warn("Sorry, I don't understand your request :(");
                    Duke.display("Did you spell something wrongly?");
                    //Duke.display("Why not try rephrasing?"); // When chatbot is smarter
                    break;
                case EXIT:
                    Duke.display("Goodbye!");
                    Duke.displayLine();
                    Duke.displayLogo();
                    break;
                default:
                    // can throw error here
                    break;
            }
        }
    }
}
