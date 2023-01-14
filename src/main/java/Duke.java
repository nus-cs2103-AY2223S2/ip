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

        // TODO: Detect other specific keywords

        // multiple exit keywords
        switch (command) {
            case "bye":
            case "goodbye":
            case "quit":
            case "quit()":
            case "exit":
            case "exit()":
                return State.EXIT;
            default:
                return State.ECHO;
            //return State.UNKNOWN; //TODO: for future levels
        }
    }

    public static void main(String[] args) {

        Duke.displayLogo();
        Duke.display("Developed by: " + AUTHOR);
        System.out.println("Initialising system . . .");

        String userCmd = "";
        Scanner sc = new Scanner(System.in);
        State currentState = State.UNKNOWN;
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
                case ECHO:
                    Duke.display(userCmd);
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
