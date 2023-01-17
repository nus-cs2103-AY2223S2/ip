import java.io.*;
import java.util.*;
public class Duke {
    private static final String logo = " |          ______    ______   \n"
                                     + " | ____    |      |  |      |  \n"
                                     + " |      |  |      |  |      |  \n"
                                     + " | ____ |  |______|  |______|  \n";
    private static final String straightLine = "_______________________________________________________________________________________________";


    public static void main(String[] args) {
        //Stores user input
        ArrayList<Task> taskStorage = new ArrayList<Task>();

        printIntroductoryMessage();

        //Prepare input and output sources
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        //Keep taking in user input line by line
        while (true) {
            String input;
            try {
                input = br.readLine();
            }
            catch (IOException ie) {
                pw.println("Error encountered in receiving input.");
                pw.println("The error message is: ");
                pw.flush();
                ie.printStackTrace();
                break;
            }

            //Determine if user types in a single word or multiple words
            String[] inputArray = input.split(" ");


            //User typed in "bye".
            if (input.equals("bye")) {
                break;
            }
            //User typed in "list"
            else if (input.equals("list")) {
                printUserTasks(taskStorage);
            }
            //User typed in "mark":
            else if (inputArray[0].equals("mark")) {
                int indexOfTask = Integer.parseInt(inputArray[1]) - 1;
                markAsDone(taskStorage.get(indexOfTask));
            }
            //User typed in "unmark":
            else if (inputArray[0].equals("unmark")) {
                int indexOfTask = Integer.parseInt(inputArray[1]) - 1;
                markAsUndone(taskStorage.get(indexOfTask));
            }
            //User did not type in "bye" or "list". Store the text as a task.
            else {
                addTask(input, taskStorage);
            }
        }

        //Exits the bot with an exit message
        printExitMessage();
        pw.close();
    }


    /**
     * Prints the introductory message.
     */
    public static void printIntroductoryMessage() {
        System.out.println(logo);
        System.out.println(straightLine);
        System.out.println("Boo! Nice to meet you.");
        System.out.println("I am here to scare all your problems away by keeping track of your tasks.");
        System.out.println("What can I help you with today?");
        System.out.println(straightLine);
    }

    /**
     * Prints the exit message.
     */
    public static void printExitMessage() {
        System.out.println(straightLine);
        System.out.println("Goodbye. Hope that I have managed to scare all your problems away.");
        System.out.println("Have a great day! :)");
        System.out.println(straightLine);
    }

    /**
     * Prints out all the user tasks that have been entered by the user thus far.
     * @param taskStorage The ArrayList that stores the user tasks to be printed out.
     */
    public static void printUserTasks(ArrayList<Task> taskStorage) {
        System.out.println(straightLine);
        int numberOfTasks= taskStorage.size();
        //Process each task in the storage
        for (int i = 0; i < numberOfTasks; i = i + 1) {
            String numbering = Integer.toString(i + 1) + ". ";
            String output = numbering + taskStorage.get(i).getStatusOfTaskInString();
            System.out.println(output);
        }
        System.out.println(straightLine);
    }

    /**
     * Adds user task into storage and informs the user.
     * @param taskName The task to be added to storage.
     * @param taskStorage The ArrayList that stores the tasks.
     */
    public static void addTask(String taskName, ArrayList<Task> taskStorage) {
        Task newTask = new Task(taskName);
        taskStorage.add(newTask);
        System.out.println(straightLine);
        System.out.println("Added task: " + taskName);
        System.out.println(straightLine);
    }

    /**
     * Marks a task as done and informs the user.
     * @param currentTask The task to be marked as done.
     */
   public static void markAsDone(Task currentTask) {
       currentTask.setDoneStatus();
       System.out.println(straightLine);
       System.out.println("Poof! One less worry. The following task is now marked as done:");
       System.out.println(currentTask.getStatusOfTaskInString());
       System.out.println(straightLine);
   }

    /**
     * Marks a task as undone and informs the user.
     * @param currentTask The task to be marked as undone.
     */
    public static void markAsUndone(Task currentTask) {
        currentTask.setUndoneStatus();
        System.out.println(straightLine);
        System.out.println("Alright! The following task is now marked as undone. I will help you keep an eye on it.");
        System.out.println(currentTask.getStatusOfTaskInString());
        System.out.println(straightLine);
    }
}


