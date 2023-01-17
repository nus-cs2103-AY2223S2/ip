import java.util.Scanner;

public class Duke {

    /** 
     * Outputs given string with formatting.
     * 
     * @param string String to be outputted.
     */
    private static void output(String string) {
        System.out.println("____________________________________________________________\n" +
                            string + "\n" +
                            "____________________________________________________________\n");
    }

    // Outputs welcome message.
    private static void welcomeMsg() {
        output("Hello! I'm Duke\nWhat can I do for you?");
    }
    
    // Outputs exit message.
    private static void exitMsg() {
        output("Bye. Hope to see you again soon!");
    }

    /** 
     * Echoes the input given by the user.
     * 
     * @param input User input.
     */
    private static void echo(String input) {
        output(input);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        welcomeMsg();

        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye"))
                break;
            echo(input);
        }

        exitMsg();
    }
}