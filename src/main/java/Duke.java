import java.util.Scanner;
import java.time.LocalDate;

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
            oneWordCommand(userInput, taskStorage);
        }
    }

    public static void oneWordCommand(String userInput, TaskStorage taskStorage) {
        String firstWord = userInput.split(" ", 2)[0];

        try {
            if (userInput.equals("bye")) {
                respond("Goodbye! Have a nice day ahead.\n");
                return;
            } else if (userInput.equals("list")) {
                taskStorage.listTasks();
                return;
            } else if (firstWord.equals("mark")) {


                String secondWord = userInput.split(" ", 2)[1];
                int taskNumber = Integer.parseInt(secondWord);
                taskStorage.updateTask(taskNumber);
                Task target = taskStorage.getTask(taskNumber);
                respond("I have marked this task as done! \n" + target.provideDetails());
                return;

            } else if (firstWord.equals("unmark")) {

                String secondWord = userInput.split(" ", 2)[1];
                int taskNumber = Integer.parseInt(secondWord);
                taskStorage.updateTask(taskNumber);
                Task target = taskStorage.getTask(taskNumber);
                respond("I have marked this task as undone! \n" + target.provideDetails());
                return;

            } else if (firstWord.equals("todo")) {
                String bodyMessage = userInput.split(" ", 2)[1];
                ToDo newTask = new ToDo(bodyMessage);
                taskStorage.storeTasks(newTask);
                respond("I have added this new task:\n" + newTask.provideDetails()
                        + "\nYou now currently have "
                        + taskStorage.getStorageCount() + " tasks.");
                return;


            } else if (firstWord.equals("deadline")) {

                String bodyMessage = userInput.split(" ", 2)[1];
                DeadLine newTask = new DeadLine(bodyMessage);
                taskStorage.storeTasks(newTask);
                respond("I have added this new task:\n" + newTask.provideDetails()
                        + "\nYou now currently have "
                        + taskStorage.getStorageCount() + " tasks.");
                return;


            } else if (firstWord.equals("event")) {
                String bodyMessage = userInput.split(" ", 2)[1];
                Event newTask = new Event(bodyMessage);
                taskStorage.storeTasks(newTask);
                respond("I have added this new task:\n" + newTask.provideDetails()
                        + "\nYou now currently have "
                        + taskStorage.getStorageCount() + " tasks.");

            } else if (firstWord.equals("delete")) {
                String bodyMessage = userInput.split(" ", 2)[1];
                int taskNumber = Integer.parseInt(bodyMessage);
                Task toDelete = taskStorage.getTask(taskNumber);
                taskStorage.deleteTask(taskNumber);


                respond("We have removed this task: " + toDelete.provideDetails() + "\nYou now have "
                        + taskStorage.getStorageCount() + " tasks remaining");


            } else {
                respond("OOPS!!! I'm sorry, but I don't know what that means :-(");

            }
        }
        catch (Exception e) {
            e.printStackTrace();
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


