import java.util.Scanner;

public class Duke {
    private static int numOfTasks = 0;
    private static String[] tasks = new String[100];

    /** 
     * Outputs given string with formatting.
     * 
     * @param string String to be outputted.
     */
    private static void output(String string) {
        System.out.println("____________________________________________________________\n" +
                            string + 
                            "____________________________________________________________\n");
    }

    // Outputs welcome message.
    private static void welcomeMsg() {
        output("Hello! I'm Duke\nWhat can I do for you?\n");
    }
    
    // Outputs exit message.
    private static void exitMsg() {
        output("Bye. Hope to see you again soon!\n");
    }

    /** 
     * Echoes the input given by the user.
     * 
     * @param input User input.
     */
    private static void echo(String input) {
        output(input);
    }

    /** 
     * Adds new task to task list and outputs success message.
     * 
     * @param task Task to be added to task list.
     */
    private static void addTask(String task) {
        tasks[numOfTasks] = task;
        numOfTasks++;
        output("added: " + task + "\n");
    }

    // Outputs all the tasks stored in task list.
    private static void listTasks() {
        String listOfTasks = "";
        for(int idx = 0; idx < numOfTasks; idx++)
            listOfTasks = listOfTasks + (idx + 1) + ". " + tasks[idx] + "\n";
        output(listOfTasks);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        welcomeMsg();

        while(true) {
            String input = sc.nextLine();
            switch(input) {
                case "list":
                    listTasks();
                    break;
                case "bye":
                    exitMsg();
                    return;
                default:
                    addTask(input);
            }
        }
    }
}