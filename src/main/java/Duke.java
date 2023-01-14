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



    public static void main(String[] args) {

        System.out.println(LOGO);
        System.out.println("Developed by: " + AUTHOR);
        System.out.println("Version Level: Level-" + V_LEVEL);

        // System.out.println("Initialising duke . . .");
        Scanner sc = new Scanner(System.in);
        String userCmd = "";
        // TODO: Initialise components
        State currentState = State.INTRO;
        // System.out.println("Duke is ready!");

        // Program Intro
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke! :D");
        System.out.println("What can I do for you?");
        currentState = State.ECHO;
        System.out.println("____________________________________________________________");

        // Program Loop
        while(true) {
            System.out.println();
            userCmd = sc.nextLine();

            if (userCmd.compareTo("bye") == 0)
                currentState = State.EXIT;

            System.out.println("____________________________________________________________");

            switch(currentState) {
                /*case INTRO:
                    System.out.println("Hello! I'm Duke! :D");
                    System.out.println("What can I do for you?");

                    break;*/
                case ECHO:
                    System.out.println(userCmd);
                    break;
                case EXIT:
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                default:
                    // can throw error here
                    break;
            }

            System.out.println("____________________________________________________________");
        }
    }
}
