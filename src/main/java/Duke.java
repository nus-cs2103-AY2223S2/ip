import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    /** 
     * Outputs given string with formatting.
     * 
     * @param string String to be outputted.
     */
    private static void output(String string) {
        System.out.println("____________________________________________________________\n" +
                            "  " + string + 
                            "____________________________________________________________\n");
    }

    // Outputs welcome message.
    private static void welcomeMsg() {
        output("Hello! I'm Duke\n  What can I do for you?\n");
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
        tasks.add(task);
        output("Got it. I've added this task:\n    " 
                + task + "\n  "
                + "Now you have " + tasks.size() + (tasks.size() == 1 ? " task " : " tasks ") + "in the list.\n");
    }

    /** 
     * Removes task from task list and outputs success message.
     * 
     * @param taskIndex Index of task to be removed.
     */
    private static void removeTask(int taskIndex) {
        Task task = tasks.remove(taskIndex);
        output("Noted. I've removed this task:\n    " 
                + task + "\n  "
                + "Now you have " + tasks.size() + (tasks.size() == 1 ? " task " : " tasks ") + "in the list.\n");
    }

    // Outputs all the tasks stored in task list.
    private static void listTasks() {
        String listOfTasks = "Here are the tasks in your list:\n";
        for(int idx = 0; idx < tasks.size(); idx++) {
            Task task = tasks.get(idx);
            listOfTasks = listOfTasks + "  " + (idx + 1) + "." + task + "\n";
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
        output("Nice! I've marked this task as done:\n    " + task + "\n");
    }

    /** 
     * Marks task as uncompleted and outputs success message.
     * 
     * @param task Task to be unmarked.
     */
    private static void unmarkTask(Task task) {
        task.unmark();
        output("OK, I've marked this task as not done yet:\n    " + task + "\n");
    }

    // Handles unknown input.
    private static void handleUnknownInput() {
        try {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        } catch (DukeException e) {
            output(e.getMessage());
        }
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
                    markTask(tasks.get(Integer.parseInt(words[1]) - 1));
                    break;
                case "unmark":
                    unmarkTask(tasks.get(Integer.parseInt(words[1]) - 1));
                    break;
                case "todo":
                    try {
                        if (words.length != 2) 
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
                        addTask(new Todo(words[1]));
                    } catch (DukeException e) {
                        output(e.getMessage());
                    }
                    break;
                case "deadline":
                    try {
                        String[] deadlineDescription = words[1].split("/by");
                        if (deadlineDescription.length != 2) 
                            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.\n");
                        addTask(new Deadline(deadlineDescription[0], deadlineDescription[1]));
                    } catch (DukeException e) {
                        output(e.getMessage());
                    }
                    break;
                case "event":
                    try {
                        String[] eventDescription = words[1].split("/from|/to");
                        if (eventDescription.length != 3) 
                            throw new DukeException("OOPS!!! The description of an event cannot be empty.\n");
                        addTask(new Event(eventDescription[0], eventDescription[1], eventDescription[2]));
                    } catch (DukeException e) {
                        output(e.getMessage());
                    }
                    break;
                case "delete":
                    removeTask(Integer.parseInt(words[1]) - 1);
                    break;
                case "bye":
                    exitMsg();
                    return;
                default:
                    handleUnknownInput();
            }
        }
    }
}