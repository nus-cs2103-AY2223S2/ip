import java.util.ArrayList;
import java.util.Scanner;

public class Fideline {

    public static void botSays(String message) {
        String line = "_________________________________________________________________";
        System.out.println(line + "\n" + message + "\n" + line + "\n");
    }

    // prints greeting message
    public static void greeting() {
        botSays("hello! I'm fideline, \nwhat do you want today?");
    }

    // prints farewell message
    public static void farewell() {
        botSays("get out of my sight!");
    }

    // starts running Fideline bot. Terminate with "bye"
    public void start() {
        // init Scanner to view user response
        Scanner sc = new Scanner(System.in);
        // init list to store user input
        ArrayList<String> list = new ArrayList<String>();
        // print greeting message
        greeting();
        // running boolean checks if bot is still running
        boolean running = true;
        while(running) {
            String userInput = sc.nextLine();
            // execute command based on user input
            if (userInput.equals("bye")) {
                // bye command stops the bot
                running = false;
                farewell();
            } else if (userInput.equals("list")) {
                // list command shows current list
                // tells user if list is empty
                if (list.size() == 0) {
                    botSays("eh are you stupid? \nyour list is currently empty!");
                } else {
                    // compiles and formats list objects into listMessage
                    String listMessage = "";
                    for (int i = 0; i < list.size(); i++) {
                        listMessage += (i + 1) + ". " + list.get(i) + "\n";
                    }
                    botSays(listMessage);
                }
            }
            else {
                // adds userInput to list
                list.add(userInput);
                // notifies user that list has been updated
                botSays("ok! i've added \"" + userInput + "\" to your list!");
            }
        }
    }
}