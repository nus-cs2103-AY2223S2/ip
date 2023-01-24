import java.util.Scanner;

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
            InputParser parsedInput;
            try {
                parsedInput = new InputParser(userInput); // parses input
            } catch (InputError ie) {
                parsedInput = new InputParser(ie);
            }
            switch (parsedInput.getCommandType()) {// execute command based on user input
                case BYE: // bye command stops the bot
                    running = false;
                    farewell();
                    break;
                case LIST: // list command shows current list
                    String listMessage = this.taskManager.listTasks();
                    if (listMessage.equals("")) {
                        botSays("eh are you stupid?\nyour list is currently empty!");
                    } else {
                        botSays("here! your list:" + listMessage);
                    }
                    break;
                case TODO: // adds todo task to list
                    this.taskManager.addTodo(parsedInput.getArguments()[0]);
                    addTaskMessage(); // notifies user that list has been updated
                    break;
                case DEADLINE: // adds deadline task to list
                    this.taskManager.addDeadline(parsedInput.getArguments()[0],
                            parsedInput.getArguments()[1]);
                    addTaskMessage(); // notifies user that list has been updated
                    break;
                case EVENT: // adds event task to list
                    this.taskManager.addEvent(parsedInput.getArguments()[0],
                            parsedInput.getArguments()[1], parsedInput.getArguments()[2]);
                    addTaskMessage(); // notifies user that list has been updated
                    break;
                case MARK: // marks given task as done
                    int taskNumMark = Integer.valueOf(parsedInput.getArguments()[0]);
                    if (this.taskManager.markTask(taskNumMark)) { // marked successfully
                        botSays("nice work! i've taken note!:\n" +
                                this.taskManager.getTaskString(taskNumMark)); // success message (marked)
                    } else { // unable to mark, task at given index does not exist
                        botSays("uh hello?? can you check properly?\n "
                                + "task does not exist bro"); // failure to mark message
                    };
                    break;
                case UNMARK: // registers given task as not done
                    int taskNumUnmark = Integer.valueOf(parsedInput.getArguments()[0]);
                    if (this.taskManager.unmarkTask(taskNumUnmark)) { // unmarked successfully
                        botSays("uhh okay... i've unmarked your task:\n"
                                + this.taskManager.getTaskString(taskNumUnmark)); // success message (unmarked)
                    } else { // unable to unmark, task at given index does not exist
                        botSays("uh hello?? can you check properly?\n " +
                                "task does not exist bro"); // failure to mark message
                    };
                    break;
                case ERROR: // alerts user of error in their input
                    botSays("hold up! " + parsedInput.getArguments()[0]);
            }
        }
    }
}