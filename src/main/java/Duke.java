import java.util.Scanner;

public class Duke {
    private static int numOfTasks = 0;
    private static Task[] tasks = new Task[100];

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
    private static void addTask(Task task) {
        tasks[numOfTasks] = task;
        numOfTasks++;
        output("added: " + task + "\n");
    }

    // Outputs all the tasks stored in task list.
    private static void listTasks() {
        String listOfTasks = "";
        for(int idx = 0; idx < numOfTasks; idx++) {
            Task task = tasks[idx];
            listOfTasks = listOfTasks + (idx + 1) + ". [" + task.getStatusIcon() + "] " + task + "\n";
        }
        output(listOfTasks);
    }

    /** 
     * Marks task as completed and outputs success message.
     * 
     * @param task Task to be marked.
     */
    private static void markTask(Task task) {
        task.mark();
        output("Nice! I've marked this task as done:\n  [X] " + task + "\n");
    }

    /** 
     * Marks task as uncompleted and outputs success message.
     * 
     * @param task Task to be unmarked.
     */
    private static void unmarkTask(Task task) {
        task.unmark();
        output("OK, I've marked this task as not done yet:\n  [ ] " + task + "\n");
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] words = new String[2];
        welcomeMsg();

        while(true) {
            String input = sc.nextLine();

            if(input.contains("mark")) {
                words = input.split(" ");
                input = words[0];
            }
            
            switch(input) {
                case "list":
                    listTasks();
                    break;
                case "mark":
                    markTask(tasks[Integer.parseInt(words[1]) - 1]);
                    break;
                case "unmark":
                    unmarkTask(tasks[Integer.parseInt(words[1]) - 1]);
                    break;
                case "bye":
                    exitMsg();
                    return;
                default:
                    addTask(new Task(input));
            }
        }
    }
}