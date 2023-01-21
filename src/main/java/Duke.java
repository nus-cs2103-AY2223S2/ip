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
            String firstWord = userInput.split(" ", 2)[0];

            if (userInput.equals("bye")) {
                respond("Goodbye! Have a nice day ahead.\n");
                break;

            }
            if (userInput.equals("list")) {
                taskStorage.listTasks();
                continue;

            }
            if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                String secondWord = userInput.split(" ", 3)[1];
                int taskNumber = Integer.parseInt(secondWord);
                taskStorage.updateTask(taskNumber);
                respond("The status of your specified task has been updated!");
                continue;


            }
            if (firstWord.equals("todo")) {
                String bodyMessage = userInput.split(" ", 2)[1];
                ToDo newTask = new ToDo(bodyMessage);
                taskStorage.storeTasks(newTask);
                respond("I have added this new task:\n" + newTask.provideDetails()
                        + "\nYou now currently have "
                        + taskStorage.getStorageCount() + " tasks.");
                continue;

            } if (firstWord.equals("deadline")) {
                String bodyMessage = userInput.split(" ", 2)[1];
                Deadline newTask = new Deadline(bodyMessage);
                taskStorage.storeTasks(newTask);
                respond("I have added this new task:\n" + newTask.provideDetails()
                        +  "\nYou now currently have "
                        + taskStorage.getStorageCount() + " tasks.");
                continue;


            }  if (firstWord.equals("event")) {
                String bodyMessage = userInput.split(" ", 2)[1];
                Event newTask = new Event(bodyMessage);
                taskStorage.storeTasks(newTask);
                respond("I have added this new task:\n" + newTask.provideDetails()
                        + "\nYou now currently have "
                        + taskStorage.getStorageCount() + " tasks.");


            } else {
                respond("Added: " + userInput);
                taskStorage.storeTasks(new Task(userInput));
            }
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


