import java.util.Scanner;

public class Duke {

    static final int V_LEVEL = 1;
    static final String AUTHOR = "lhy-hoyin";
    static final String LOGO
            = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    static void display(String message) {
        System.out.println(message);
    }
    static void displayLogo() {
        Duke.display(LOGO);
    }
    static void displayLine() {
        Duke.display("____________________________________________________________");
    }

    public static void main(String[] args) {

        Duke.displayLogo();
        Duke.display("Developed by: " + AUTHOR);
        Duke.display("Version Level: Level-" + V_LEVEL);

        System.out.println("Initialising system . . .");
        Scanner sc = new Scanner(System.in);
        String userCmd = "";
        // TODO: Initialise components
        State currentState;// = State.ECHO;
        System.out.println("System is ready!");
        Duke.display("\n\n");
        Duke.displayLine();


        // Program Intro
        Duke.display("Hello! I'm Duke! :D");
        Duke.display("What can I do for you today?");

        // Program Loop
        while(true) {
            currentState = State.ECHO;
            System.out.print("\n > ");
            userCmd = sc.nextLine();

            // Command detection
            if (userCmd.compareTo("bye") == 0)
                currentState = State.EXIT;


            switch(currentState) {
                case ECHO:
                    Duke.display(userCmd);
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
