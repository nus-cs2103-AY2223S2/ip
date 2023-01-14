import java.util.Scanner;

public class Duke {

    static final String AUTHOR = "lhy-hoyin";
    static final String LOGO
            = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";


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
        if (command.compareTo("bye") == 0)
            return State.EXIT;
        return State.ECHO;
        //return State.UNKNOWN;
    }

    public static void main(String[] args) {

        Duke.displayLogo();
        Duke.display("Developed by: " + AUTHOR);

        System.out.println("Initialising system . . .");
        State currentState;
        String userCmd = "";
        Scanner sc = new Scanner(System.in);
        // TODO: Initialise components
        System.out.println("System is ready!");
        Duke.display("\n\n");
        Duke.displayLine();

        // Program Intro
        Duke.display("Hello! I'm Duke! :D");
        Duke.display("What can I do for you today?");

        // Program Loop
        while(true) {
            System.out.print("\n > ");
            userCmd = sc.nextLine();

            // Command detection
            currentState = Duke.detectState(userCmd);

            // State handling
            switch(currentState) {
                case ECHO:
                    Duke.display(userCmd);
                    break;
                case EXIT:
                    Duke.display("Goodbye!");
                    break;
                case UNKNOWN:
                    Duke.display("Sorry, I don't understand your request :(");
                    Duke.display("Did you spell something wrongly?");
                    //Duke.display("Why not try rephrasing?"); // When chatbot is smarter
                    break;
                default:
                    // can throw error here
                    break;
            }

            // Exit condition
            if (currentState == State.EXIT) {
                Duke.displayLine();
                Duke.displayLogo();
                break;
            }

        }
    }
}
