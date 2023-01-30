import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;

/** Duke chat bot.
 * @author Hee Jia Yuan
 */
public class Duke {
    /**
     * Runs the main Duke chat-bot program.
     * @param args
     */
    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        TaskStorage taskStorage = new TaskStorage();

        //If Hard Drive did not crete file, an existing file exists.
        //Proceed to load data from hard drive.
        if (!DriveStorageOOP.createFile()) {
            DriveStorageOOP.loadTasks(taskStorage);
        }

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            oneWordCommand(userInput, taskStorage);
        }
    }


    /**
     *
     * @param userInput User Input String.
     * @param taskStorage A storage that keeps track of Tasks.
     * @throws Exception Throws exception when user gives a wrong input command.
     */
    public static void oneWordCommand(String userInput, TaskStorage taskStorage) {
        try {
            if (userInput.equals("bye")) {
                respond("Goodbye! Have a nice day ahead.\n");
                return;
            } else if (userInput.equals("list")) {
                taskStorage.listTasks();
                return;
            }

            String firstWord = userInput.split(" ", 2)[0];

            if (firstWord.equals("mark")) {
                //Second word should be an integer dictating which Task to mark.
                String secondWord = userInput.split(" ", 2)[1];
                int taskNumber = Integer.parseInt(secondWord);
                Task task = taskStorage.getTask(taskNumber);
                DriveStorageOOP.changeTaskStatus(task.getStorageLine());
                taskStorage.mark(taskNumber);
                respond("I have marked this task as done! \n" + task.provideDetails());
                return;
            }

            if (firstWord.equals("unmark")) {
                //Second word should be an integer dictating which Task to unmark.
                String secondWord = userInput.split(" ", 2)[1];
                int taskNumber = Integer.parseInt(secondWord);
                Task task = taskStorage.getTask(taskNumber);
                DriveStorageOOP.changeTaskStatus(task.getStorageLine());
                taskStorage.unmark(taskNumber);
                respond("I have marked this task as undone! \n" + task.provideDetails());
                return;
            }

            if (firstWord.equals("todo")) {
                //Rest of message describes the Task.
                String body = userInput.split(" ", 2)[1];
                ToDo task = new ToDo("todo", body, false);
                taskStorage.addTask(task);
                DriveStorageOOP.addTask(task.getStorageLine());

                respond("I have added this new task:\n" + task.provideDetails()
                        + "\nYou now currently have "
                        + taskStorage.getStorageCount() + " tasks.");
                return;
            }

            if (firstWord.equals("deadline")) {
                //Rest of message describes the Task.
                String body = userInput.split(" ", 2)[1];
                DeadLine newTask = new DeadLine("deadline", body, false);
                taskStorage.addTask(newTask);
                DriveStorageOOP.addTask(newTask.getStorageLine());
                respond("I have added this new task:\n" + newTask.provideDetails()
                        + "\nYou now currently have "
                        + taskStorage.getStorageCount() + " tasks.");
                return;
            }
            if (firstWord.equals("event")) {
                //Rest of message describes the Task.
                String body = userInput.split(" ", 2)[1];
                Event newTask = new Event("event", body, false);
                taskStorage.addTask(newTask);
                DriveStorageOOP.addTask(newTask.getStorageLine());

                respond("I have added this new task:\n" + newTask.provideDetails()
                        + "\nYou now currently have "
                        + taskStorage.getStorageCount() + " tasks.");


            }
            if (firstWord.equals("delete")) {
                //second word should be an integer
                String secondWord = userInput.split(" ", 2)[1];
                int taskNumber = Integer.parseInt(secondWord);
                Task task = taskStorage.getTask(taskNumber);
                taskStorage.deleteTask(taskNumber);
                DriveStorageOOP.deleteTask(task.getStorageLine());

                respond("We have removed this task: " + task.provideDetails() + "\nYou now have "
                        + taskStorage.getStorageCount() + " tasks remaining");

            }

        } catch (Exception e) {
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


