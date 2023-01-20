import java.util.Scanner;

public class Fideline {


    public static void botSays(String message) {
        String line = "_________________________________________________________________";
        System.out.println(line + "\n" + message + "\n" + line + "\n");
    }

    // prints greeting message
    public static void greeting() {
        botSays("Hello! I'm Fideline, \nwhat do you want today?");
    }

    // prints farewell message
    public static void farewell() {
        botSays("get out of my sight!");
    }

    // starts running Fideline bot. Terminate with "bye"
    public void start() {
        // init Scanner to view user response
        Scanner sc = new Scanner(System.in);
        // print greeting message
        greeting();
        // running boolean checks if bot is still running
        boolean running = true;
        while(running) {
            String userInput = sc.nextLine();
            // execute command based on user input
            if (userInput.equals("bye")) {
                running = false;
                farewell();
            } else {
                // echos userInput
                botSays(userInput);
            }
        }
    }
}
