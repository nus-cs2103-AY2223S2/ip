import java.util.Scanner;
import java.util.regex.Pattern;

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
        // init TaskManager to manage all tasks from user
        TaskManager taskManager = new TaskManager();
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
            } else if (userInput.equals("list")) {  // list command shows current list
                String listMessage = taskManager.listTasks();
                if (listMessage.equals("")) {
                    botSays("eh are you stupid? \nyour list is currently empty!");
                } else {
                    botSays("here! your list:" + listMessage);
                }
            } else if (Pattern.matches("mark \\d+?", userInput)) {
                int taskNum = Integer.valueOf(userInput.split(" ")[1]);
                if (taskManager.markTask(taskNum)) { // marked successfully
                    botSays("nice work! i've taken note!: \n" +
                            "   [X] " + taskManager.getTaskName(taskNum)); // success message (marked)
                } else { // unable to mark, task at given index does not exist
                    botSays("uh hello?? can you check properly? \n " +
                            "task does not exist bro"); // failure to mark message
                };
            } else if (Pattern.matches("unmark \\d+?", userInput)) {
                int taskNum = Integer.valueOf(userInput.split(" ")[1]);
                if (taskManager.unmarkTask(taskNum)) { // unmarked successfully
                    botSays("uhh okay... i've unmarked your task: \n" +
                            "   [ ] " + taskManager.getTaskName(taskNum)); // success message (unmarked)
                } else { // unable to unmark, task at given index does not exist
                    botSays("uh hello?? can you check properly? \n " +
                            "task does not exist bro"); // failure to mark message
                };
            } else {
                // adds userInput to list
                taskManager.addTask(userInput);
                // notifies user that list has been updated
                botSays("ok! i've added \"" + userInput + "\" to your list!");
            }
        }
    }
}