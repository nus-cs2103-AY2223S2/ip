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
    static void displayLogo() {
        Duke.display(LOGO);
    }
    static void displayLine() {
        Duke.display("____________________________________________________________");
    }

    static State detectState(String command) {
        // Suppress all upper case letters, gets only the first word
        String cmd = command.toLowerCase().split(" ")[0];

        if (cmd.compareTo("list") == 0)
            return State.LIST;
        else if (cmd.compareTo("add") == 0)
            return State.ADD;
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

        int taskIdx;
        Task selectedTask;
        String userCmd = "";
        Scanner sc = new Scanner(System.in);
        State currentState = State.UNKNOWN;
        ArrayList<Task> list = new ArrayList<Task>();
        // TODO: Initialise components

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
                case ADD:
                    String item = userCmd.substring(4).trim(); // exclude "add "
                    list.add(new Task(item));
                    Duke.display("I have added: " + item);
                    break;
                case LIST:
                    if (list.size() == 0)
                        Duke.display("Your list is empty.");
                    else {
                        Duke.display("Here's your list of tasks:");
                        for (int i = 0; i < list.size(); i++)
                            Duke.display((i + 1) + ". " + list.get(i));
                    }
                    break;
                // TODO: merge the 2 cases below
                case MARK:
                    taskIdx = Integer.valueOf(userCmd.split(" ")[1]) - 1; // FIXME: throws NumberFormatException for "mark  1"
                    //FIXME: need watch for index out of bound exception
                    selectedTask = list.get(taskIdx);
                    selectedTask.setDone(true);
                    Duke.display("Nice I've marked this task as done:");
                    Duke.display(selectedTask);
                    break;
                case UNMARK:
                    taskIdx = Integer.valueOf(userCmd.split(" ")[1]) - 1; // FIXME: throws NumberFormatException for "unmark  1"
                    //FIXME: need watch for index out of bound exception
                    selectedTask = list.get(taskIdx);
                    selectedTask.setDone(false);
                    Duke.display("OK, I've marked this task as not done yet:");
                    Duke.display(selectedTask);
                    break;
                case UNKNOWN:
                    Duke.display("Sorry, I don't understand your request :(");
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
