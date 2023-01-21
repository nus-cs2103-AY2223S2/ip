import java.util.Scanner;

/** Duke chat bot.
 * @author Hee Jia Yuan
 */
public class Duke {
    /**
     * Runs the main chat program.
     * @param args
     */
    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        TaskStorage taskStorage = new TaskStorage();

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            try {
                oneWordCommand(userInput, taskStorage);
            } catch (Exception e) {
                respond("I'm sorry, but to use this command, you must have a valid body message.");
            }
        }
    }


    public static void oneWordCommand(String userInput, TaskStorage taskStorage) throws Exception {
        String firstWord = userInput.split(" ", 2)[0];

        if (userInput.equals("bye")) {
            respond("Goodbye! Have a nice day ahead.\n");
            return;
        }

        if (userInput.equals("list")) {
            taskStorage.listTasks();
            return;
        }

        if (firstWord.equals("mark") || firstWord.equals("unmark")) {

            String secondWord = userInput.split(" ", 2)[1];
            int taskNumber = Integer.parseInt(secondWord);
            taskStorage.updateTask(taskNumber);
            respond("The status of your specified task has been updated!");
            return;

        }

        if (firstWord.equals("todo")) {
            String bodyMessage = userInput.split(" ", 2)[1];
            ToDo newTask = new ToDo(bodyMessage);
            taskStorage.storeTasks(newTask);
            respond("I have added this new task:\n" + newTask.provideDetails()
                    + "\nYou now currently have "
                    + taskStorage.getStorageCount() + " tasks.");
            return;


        } if (firstWord.equals("deadline")) {
            String bodyMessage = userInput.split(" ", 2)[1];
            Deadline newTask = new Deadline(bodyMessage);
            taskStorage.storeTasks(newTask);
            respond("I have added this new task:\n" + newTask.provideDetails()
                    +  "\nYou now currently have "
                    + taskStorage.getStorageCount() + " tasks.");
            return;


        }  if (firstWord.equals("event")) {
            String bodyMessage = userInput.split(" ", 2)[1];
            Event newTask = new Event(bodyMessage);
            taskStorage.storeTasks(newTask);
            respond("I have added this new task:\n" + newTask.provideDetails()
                    + "\nYou now currently have "
                    + taskStorage.getStorageCount() + " tasks.");

        } else {
            respond("OOPS!!! I'm sorry, but I don't know what that means :-(");

        }


    }



    /**
     * Greets the user.
     */
    public static void greet() {
        System.out.println("Hi, my name's Duke, how may I be of assistance today? :)");
    }

    /**
     * Responds to the user, given a message.
     * @param message A String which will be the bot's response message.
     */
    public static void respond(String message) {
        String topDivider = "~~~~~~~~~~~~~~~~o~~~~~~~~~~~~~~~~\n" + "Duke's Response: \n";
        String botDivider = "\n~~~~~~~~~~~~~~~~o~~~~~~~~~~~~~~~~";
        System.out.println(topDivider + "\n" + message + botDivider);
    }


}


