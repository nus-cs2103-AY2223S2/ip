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
        output("Got it. I've added this task:\n" 
                + "  " + task + "\n"
                + "Now you have " + numOfTasks + (numOfTasks == 1 ? " task " : " tasks ") + "in the list.\n");
    }

    // Outputs all the tasks stored in task list.
    private static void listTasks() {
        String listOfTasks = "Here are the tasks in your list:\n";
        for(int idx = 0; idx < numOfTasks; idx++) {
            Task task = tasks[idx];
            listOfTasks = listOfTasks + (idx + 1) + "." + task + "\n";
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
        output("Nice! I've marked this task as done:\n  " + task + "\n");
    }

    /** 
     * Marks task as uncompleted and outputs success message.
     * 
     * @param task Task to be unmarked.
     */
    private static void unmarkTask(Task task) {
        task.unmark();
        output("OK, I've marked this task as not done yet:\n   " + task + "\n");
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        welcomeMsg();

        while(true) {
            String input = sc.nextLine();
            String[] words = input.split(" ", 2);
            input = words[0];
            
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
                case "todo":
                    String[] todoDescription = words[1].split("/");
                    addTask(new Todo(todoDescription[0]));
                    break;
                case "deadline":
                    String[] deadlineDescription = words[1].split("/by");
                    addTask(new Deadline(deadlineDescription[0], deadlineDescription[1]));
                    break;
                case "event":
                    String[] eventDescription = words[1].split("/from|/to");
                    addTask(new Event(eventDescription[0], eventDescription[1], eventDescription[2]));
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