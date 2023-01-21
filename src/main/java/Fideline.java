import java.util.Scanner;
import java.util.regex.Pattern;

public class Fideline {

    public TaskManager taskManager;

    Fideline() {
        // init TaskManager to manage all tasks from user
        this.taskManager = new TaskManager();
    }
    public static void botSays(String message) {
        String line = "_________________________________________________________________";
        System.out.println(line + "\n" + message + "\n" + line);
    }

    // prints greeting message
    public void greeting() {
        botSays("hello! I'm fideline,\nwhat do you want today?");
    }

    // prints farewell message
    public void farewell() {
        botSays("get out of my sight!");
    }

    // prints add task message, is called when task is just added. Announces
    // the addition of the latest task
    public void addTaskMessage() {
        int taskCount = this.taskManager.getTaskCount();
        botSays("ok! i've added to your list:\n  "
                + this.taskManager.getTaskString(taskCount)
                + "\nwow! there " + (taskCount == 1 ? "is " : "are ") + taskCount
                + (taskCount == 1 ? " task " : " tasks ") + "in the list now! :0");
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
                // bye command stops the bot
                running = false;
                farewell();
            } else if (userInput.equals("list")) {  // list command shows current list
                String listMessage = this.taskManager.listTasks();
                if (listMessage.equals("")) {
                    botSays("eh are you stupid?\nyour list is currently empty!");
                } else {
                    botSays("here! your list:" + listMessage);
                }
            } else if (userInput.startsWith("todo ")) {
                // adds todo task to list
                this.taskManager.addTodo(userInput.substring(5));
                // notifies user that list has been updated
                addTaskMessage();
            } else if (userInput.startsWith("deadline ")) {
                // adds deadline task to list
                String[] deadlineInput = userInput.substring(9).split(" /by ");
                this.taskManager.addDeadline(deadlineInput[0], deadlineInput[1]);
                // notifies user that list has been updated
                addTaskMessage();
            } else if (userInput.startsWith("event ")) {
                // adds event task to list
                String[] eventInput = userInput.substring(6).split(" /from ");
                String[] eventTiming = eventInput[1].split(" /to ");
                this.taskManager.addEvent(eventInput[0], eventTiming[0], eventTiming[1]);
                // notifies user that list has been updated
                addTaskMessage();
            } else if (Pattern.matches("mark \\d+?", userInput)) {
                int taskNum = Integer.valueOf(userInput.split(" ")[1]);
                if (this.taskManager.markTask(taskNum)) { // marked successfully
                    botSays("nice work! i've taken note!:\n" +
                            this.taskManager.getTaskString(taskNum)); // success message (marked)
                } else { // unable to mark, task at given index does not exist
                    botSays("uh hello?? can you check properly?\n "
                            + "task does not exist bro"); // failure to mark message
                };
            } else if (Pattern.matches("unmark \\d+?", userInput)) {
                int taskNum = Integer.valueOf(userInput.split(" ")[1]);
                if (this.taskManager.unmarkTask(taskNum)) { // unmarked successfully
                    botSays("uhh okay... i've unmarked your task:\n"
                            + this.taskManager.getTaskString(taskNum)); // success message (unmarked)
                } else { // unable to unmark, task at given index does not exist
                    botSays("uh hello?? can you check properly?\n " +
                            "task does not exist bro"); // failure to mark message
                };
            } else {
                botSays(userInput);
            }
        }
    }
}